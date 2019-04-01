package com.xxx.spring.demo.temp;

import java.util.Map;

public class ModelAndView {

    private String page;
    private Map<String, Object> model;

    public ModelAndView() {
    }

    public ModelAndView(String page, Map<String, Object> model) {
        this.page = page;
        this.model = model;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
