package com.example.sendemail.controllers;

import com.example.sendemail.model.EmailMessage;
import com.example.sendemail.service.EmailSenderService;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@CrossOrigin
public class EmailController {
    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/sendemail")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) throws MessagingException, IOException, TemplateException {
//        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        this.emailSenderService.sendEmail(emailMessage);
        return ResponseEntity.ok("Success");
    }
}
