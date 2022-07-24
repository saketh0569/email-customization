package com.example.sendemail.controllers;

import com.example.sendemail.model.EmailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class ApplicationController {

    @GetMapping("/greeting")
    public ModelAndView greeting(@RequestBody EmailMessage msg) {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", msg);
        model.setViewName("index");
        return model;
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
