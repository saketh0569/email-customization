package com.example.sendemail.service;

import com.example.sendemail.model.EmailMessage;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {
    private final JavaMailSender mailSender;
    private final Configuration configuration;

    public EmailSenderService(Configuration configuration, JavaMailSender mailSender) {
        this.configuration = configuration;
        this.mailSender = mailSender;
    }

//    public void sendEmail(String to, String subject, String message) {
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom("spare4147@gmail.com");
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(message);
//
//        this.mailSender.send(simpleMailMessage);
//    }

    public void sendEmail(EmailMessage msg) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(msg.getSubject());
        helper.setTo(msg.getTo());
        String emailContent = getEmailContent(msg);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    String getEmailContent(EmailMessage msg) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("msg", msg);
        configuration.getTemplate(msg.getCompany()+".ftl").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
