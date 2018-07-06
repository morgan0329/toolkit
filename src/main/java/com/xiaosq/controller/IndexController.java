package com.xiaosq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/exception")
    public String testException() {

        throw new NullPointerException("ddde");


        //return "Hello battcn";
    }

    @GetMapping("/demo1")
    public String demo1() {

        return "Hello battcn";
    }


}
