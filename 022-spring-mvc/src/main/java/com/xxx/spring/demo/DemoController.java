package com.xxx.spring.demo;


import com.xxx.spring.framework.annotation.Autowired;
import com.xxx.spring.framework.annotation.Controller;
import com.xxx.spring.framework.annotation.RequestMapping;
import com.xxx.spring.framework.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/query.json")
    public void query(HttpServletRequest req,
                      HttpServletResponse resp,
                      @RequestParam("name") String name) {
        String result = demoService.get(name);
        System.out.println(result);
//		try {
//			resp.getWriter().write(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
    }

}
