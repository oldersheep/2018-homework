package com.xxx.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CachedRestController {

    @RequestMapping("/cache")
    public ResponseEntity<String> cache(@RequestParam(required = false, defaultValue = "false") boolean chached) {
        if (chached) {
            return ResponseEntity.ok("hello word");
        } else {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
    }

}
