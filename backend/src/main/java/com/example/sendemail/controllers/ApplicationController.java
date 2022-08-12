package com.example.sendemail.controllers;

import com.example.sendemail.model.EmailMessage;
import com.example.sendemail.model.Org;
import com.example.sendemail.service.OrgService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/org/update", method = RequestMethod.PUT)
    public String updateTemplate(@RequestBody Org org) throws TemplateException, IOException {
        return orgService.updateTemplate(org);
    }

    @RequestMapping(value = "/org/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteTemplate(@RequestParam("id") String id) throws IOException {
        orgService.deleteTemplate(id);
        return ResponseEntity.ok("Success");
    }

    @RequestMapping(value = "/org/preview", method = RequestMethod.GET)
    public String previewTemplate(@RequestBody Org org) throws TemplateException, IOException {
        return orgService.preview(org);
    }
}
