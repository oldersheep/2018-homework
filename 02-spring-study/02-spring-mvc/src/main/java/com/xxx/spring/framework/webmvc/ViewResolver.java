package com.xxx.spring.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1、将静态文件变为动态文件
 * 2、根据用户传入的参数的不同，做出不同的结果
 */
public class ViewResolver {

    private String viewName;
    private File templateFile;

    public ViewResolver(String viewName, File templateFile) {
        this.viewName = viewName;
        this.templateFile = templateFile;
    }

    public String viewResolver(ModelAndView mv) throws Exception {
        StringBuffer sb = new StringBuffer();

        RandomAccessFile ra = new RandomAccessFile(templateFile, "r");
        String line = null;
        while ((line = ra.readLine()) != null) {
            Matcher matcher = matcher(line);
            while (matcher.find()) {
                // 这里万分注意，i从1开始，但是matcher.groupCount()也只有一个
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    String paramName = matcher.group(i);
                    Object paramValue = mv.getModel().get(paramName);
                    if (paramValue == null) {
                        continue;
                    }
                    line = line.replaceAll("@\\{" + paramName + "\\}", paramValue.toString());
                }
            }
            sb.append(line);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private Matcher matcher(String str) {
        // 这里刚开始犯蠢，$代表一个字符，如果使用$，则需要转义
        Pattern pattern = Pattern.compile("@\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(str);
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }
}
