package com.example.sendemail.service;

import com.example.sendemail.model.EmailMessage;
import com.example.sendemail.model.FileDoc;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailSenderService {
    @Autowired
    private FileService fileService;
    private final JavaMailSender mailSender;
    private final Configuration configuration;

    public EmailSenderService(Configuration configuration, JavaMailSender mailSender) {
        this.configuration = configuration;
        this.mailSender = mailSender;
    }

//    to send simple mail message
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

//    for offline storage
//    String getEmailContent(EmailMessage msg) throws IOException, TemplateException {
//        StringWriter stringWriter = new StringWriter();
//        Map<String, Object> model = new HashMap<>();
//        model.put("msg", msg);
//        configuration.getTemplate(msg.getCompany()+".ftl").process(model, stringWriter);
//        return stringWriter.getBuffer().toString();
//    }

    String getEmailContent(EmailMessage msg) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("msg", msg);
//        System.out.println(msg.getCompany());

        FileDoc filet = fileService.downloadFileFromName(msg.getCompany());
        String str = new String(filet.getFile()); // this is ftl file content
//        System.out.println("str is "+str);
//        System.out.println("here "+filet.getFilename());
//        System.out.println(String.valueOf(filet.getFile()));

        File dest = new File("src/main/resources/templates/temp.ftl");
        FileWriter wr = new FileWriter(dest);
        wr.write(str);
        wr.flush();
        wr.close();

        configuration.getTemplate("temp.ftl").process(model, stringWriter);
//        dest.delete();
        return stringWriter.getBuffer().toString();
    }
}
