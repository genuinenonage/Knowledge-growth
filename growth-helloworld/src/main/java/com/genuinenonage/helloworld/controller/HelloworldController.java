package com.genuinenonage.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloworldController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "Hello World!";
    }
}
