package com.xxx.spring.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

public class HandlerAdapter {

    private Map<String, Integer> paramMapping;


    public HandlerAdapter(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handler) throws Exception {

        // 根据用户的请求参数信息，跟method的动态参数进行匹配
        // response的作用只是将用户传进行来的response，对其进行赋值

        // 1、准备好方法的参数列表
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();

        // 2、拿到自定义命名的参数所在的位置
        Map<String, String[]> reqParameterMap = req.getParameterMap();

        // 3、构造实参列表
        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> param : reqParameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]", "")
                    .replaceAll("\\s", "");
            if (!this.paramMapping.containsKey(param.getKey())){continue;}

            Integer index = this.paramMapping.get(param.getKey());
            // 这里要针对传入的参数进行类型转换
            paramValues[index] = caseStringValue(value, parameterTypes[index]);
        }

        Integer reqIndex = this.paramMapping.get(HttpServletRequest.class.getName());
        if (reqIndex != null) {paramValues[reqIndex] = req;}
        Integer respIndex = this.paramMapping.get(HttpServletResponse.class.getName());
        if (respIndex != null) {paramValues[respIndex] = resp;}

        // 4、从handler中获取Controller、Method，利用反射调用
        Object result = handler.getMethod().invoke(handler.getController(), paramValues);
        if (result == null) {
            return null;
        }
        if (handler.getMethod().getReturnType() == ModelAndView.class) {
            return (ModelAndView)result;
        }

        return null;
    }

    private Object caseStringValue(String value, Class<?> clazz) {
        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        }else {
            return null;
        }
    }
}
