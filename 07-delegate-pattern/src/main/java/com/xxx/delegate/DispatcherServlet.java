package com.xxx.delegate;

import com.xxx.delegate.controller.MemberController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {

    public List<Handler> handlerMapping = new ArrayList<>();

    public DispatcherServlet() {
        try {
            Class<?> memberControllerClazz = MemberController.class;

            handlerMapping.add(new Handler().setController(memberControllerClazz.newInstance())
                    .setMethod(memberControllerClazz.getMethod("getMemberById", new Class[]{String.class}))
                    .setUrl("/web/getMemberById.do"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void doService(HttpServletRequest request, HttpServletResponse response) {
        doDispatch(request, response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {

        try {
            // 1、获取用户请求的URL
            String uri = request.getRequestURI();

            // 2、Servlet拿到URL后，进行权衡选择

            // 3、通过URL去handlerMapping中匹配
            Handler handler = null;
            Object controller = null;
            for (Handler h : handlerMapping) {
                if (uri.equals(h.getUrl())) {
                    handler = h;
                    break;
                }
            }

            // 4、通过反射，去调用相应的方法
            handler.getMethod().invoke(handler.getController(), request.getParameter("mid"));

            // 5、获取到Method执行结果，通过response返回
            response.getWriter().write("hello");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class Handler {
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
