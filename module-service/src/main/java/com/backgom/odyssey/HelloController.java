package com.backgom.odyssey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/vue")
    public String index() {
        return "index";
    }

}
