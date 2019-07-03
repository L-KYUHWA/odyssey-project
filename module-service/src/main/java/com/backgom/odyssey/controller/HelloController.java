package com.backgom.odyssey.controller;

import com.backgom.odyssey.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //Server health Check API
    @RequestMapping("hello")
    public String hello() {
        return "Hello Odyssey Service";
    }

	@RequestMapping("aaa")
	public String aaa() {
		throw new BusinessException();
	}

}
