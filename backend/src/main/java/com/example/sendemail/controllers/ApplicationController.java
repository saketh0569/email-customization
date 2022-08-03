package com.example.sendemail.controllers;

import com.example.sendemail.model.EmailMessage;
import com.example.sendemail.model.Org;
import com.example.sendemail.service.OrgService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@CrossOrigin
public class ApplicationController {
    @Autowired
    private OrgService orgService;

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

    @RequestMapping(value = "/org/add", method = RequestMethod.POST)
    public String addOrgTemplate(@RequestBody Org org) throws TemplateException, IOException {
        return orgService.addTemplate(org);
    }
}
