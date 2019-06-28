package com.backgom.odyssey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {

    @Autowired
    private HelloTestService service;


    @RequestMapping("hello")
    public String hello() {
        return service.hello();
    }
}
