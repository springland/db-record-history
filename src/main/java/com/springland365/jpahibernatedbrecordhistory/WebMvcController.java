package com.springland365.jpahibernatedbrecordhistory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebMvcController {

    @GetMapping("/")
    public String home(){
        return "index";
    }
}
