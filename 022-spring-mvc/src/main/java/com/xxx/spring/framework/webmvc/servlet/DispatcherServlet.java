package com.xxx.spring.framework.webmvc.servlet;

import com.xxx.spring.framework.annotation.Controller;
import com.xxx.spring.framework.annotation.RequestMapping;
import com.xxx.spring.framework.annotation.RequestParam;
import com.xxx.spring.framework.context.ApplicationContext;
import com.xxx.spring.framework.webmvc.HandlerAdapter;
import com.xxx.spring.framework.webmvc.HandlerMapping;
import com.xxx.spring.framework.webmvc.ModelAndView;
import com.xxx.spring.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DispatcherServlet extends HttpServlet {

    private final ClassLoader classLoader = this.getClass().getClassLoader();
    private static final String TEMPLATE_LOCATION = "templateRoot";
    private static final String CONFIG_LOCATION = "contextConfigLocation";

    //
    private List<HandlerMapping> handlerMappings = new ArrayList<>();

    private Map<HandlerMapping, HandlerAdapter> handlerAdapters = new HashMap<>();

    private List<ViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception, Details:\r\n" +
                    Arrays.toString(e.getStackTrace())
                            .replaceAll("\\[|\\]", "")
                            .replaceAll("\\s", "\r\n"));
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 这里初始化IOC容器
        ApplicationContext context = new ApplicationContext(config.getInitParameter(CONFIG_LOCATION));

        onRefresh(context);
    }

    protected void onRefresh(ApplicationContext context) {
        initStrategies(context);
    }

    protected void initStrategies(ApplicationContext context) {
        initMultipartResolver(context);
        initLocaleResolver(context);
        initThemeResolver(context);
        // 用来保存 URL与Controller中的Method的对应关系
        initHandlerMappings(context);
        // 获取适配器
        initHandlerAdapters(context);
        initHandlerExceptionResolvers(context);
        initRequestToViewNameTranslator(context);
        // 自定义模板
        initViewResolvers(context);
        initFlashMapManager(context);
    }

    // 将URL与Controller中的Method的对应
    private void initHandlerMappings(ApplicationContext context) {

        // 从容器中取到所有的实例
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            // 这里取出所有注册的bean，而后只取Controller
            Object controller = context.getBean(beanName);

            Class<?> clazz = controller.getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) {
                continue;
            }

            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 扫描所有的方法
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(RequestMapping.class)) {
                    continue;
                }

                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String regex = ("/" + baseUrl + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                this.handlerMappings.add(new HandlerMapping(controller, method, pattern));
                System.out.println("Mapping: " + regex + "," + method);
            }
        }

    }

    private void initHandlerAdapters(ApplicationContext context) {
        // 在初始化阶段，将参数的名字或者类型 按一定的顺序 保存下来
        // 后面调用反射的时候，传入的形参只是一个数组
        // 通过记录这些参数的位置，挨个从数组中填值，保证与参数的顺序无关
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            // 每一个方法有一个参数列表，这里保存的是形参列表
            Map<String, Integer> paramMapping = new HashMap<>();

            // 这里只处理被RequestParam修饰的参数
            Annotation[][] parameterAnnotations = handlerMapping.getMethod().getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof RequestParam) {
                        String paramName = ((RequestParam) annotation).value();
                        if (!"".equals(paramName.trim())) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            // 这里只处理Request和Response
            Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if (parameterType == HttpServletResponse.class || parameterType == HttpServletRequest.class) {
                    paramMapping.put(parameterType.getName(), i);
                }
            }

            this.handlerAdapters.put(handlerMapping, new HandlerAdapter(paramMapping));
        }
    }

    private void initViewResolvers(ApplicationContext context) {

        // 解决页面名字与模板文件关联的问题
        String templateRoot = context.getConfig().getProperty(TEMPLATE_LOCATION);

        String templateRootPath = classLoader.getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            this.viewResolvers.add(new ViewResolver(template.getName(), template));
        }
    }


    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping mappedHandler = getHandler(req);
        if (mappedHandler == null) {
            resp.getWriter().write("404 Exception");
            return;
        }

        //3. 获取处理request的处理器适配器handler adapter
        HandlerAdapter ha = getHandlerAdapter(mappedHandler);

        // 4.实际的处理器处理请求,返回结果视图对象
        ModelAndView mv = ha.handle(req, resp, mappedHandler);
        // 输出
        processDispatchResult(resp, mappedHandler, mv);
    }

    private HandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {return null;}

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");

        for (HandlerMapping handlerMapping : handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);

            if (!matcher.matches()) {
                continue;
            }

            return handlerMapping;
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(HandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {return null;}
        return this.handlerAdapters.get(handler);
    }

    private void processDispatchResult(HttpServletResponse resp, HandlerMapping mappedHandler, ModelAndView mv) throws Exception {
        if (mv == null) {return;}

        if (this.viewResolvers.isEmpty()) {return;}

        for (ViewResolver viewResolver : this.viewResolvers) {
            if (!mv.getViewName().equals(viewResolver.getViewName())) {
                continue;
            }

            String out = viewResolver.viewResolver(mv);
            if (out != null) {
                resp.getWriter().write(out);
                break;
            }
        }
    }

    // ======= 方法不实现区
    private void initMultipartResolver(ApplicationContext context) {}
    private void initLocaleResolver(ApplicationContext context) {}
    private void initThemeResolver(ApplicationContext context) {}
    private void initHandlerExceptionResolvers(ApplicationContext context) {}
    private void initRequestToViewNameTranslator(ApplicationContext context) {}
    private void initFlashMapManager(ApplicationContext context) {}
    // ======= END
}
