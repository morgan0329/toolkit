package com.xiaosq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/exception")
    public String testException() {

        throw new NullPointerException("ddde");


        //return "Hello battcn";
    }

    @GetMapping("/demo")
    public String demo1() {

        return "Hello battcn";
    }


}
