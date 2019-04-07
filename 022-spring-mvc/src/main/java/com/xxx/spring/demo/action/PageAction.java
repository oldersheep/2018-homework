package com.xxx.spring.demo.action;

import com.xxx.spring.demo.service.IQueryService;
import com.xxx.spring.framework.webmvc.ModelAndView;
import com.xxx.spring.framework.annotation.Autowired;
import com.xxx.spring.framework.annotation.Controller;
import com.xxx.spring.framework.annotation.RequestMapping;
import com.xxx.spring.framework.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PageAction {

    @Autowired
    private IQueryService queryService;

    @RequestMapping("/first.html")
    public ModelAndView query(@RequestParam("teacher") String teacher) {
        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new ModelAndView("first.html", model);
    }

}
