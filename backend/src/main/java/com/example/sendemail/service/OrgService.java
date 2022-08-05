package com.example.sendemail.service;

import com.example.sendemail.model.FileDoc;
import com.example.sendemail.model.Org;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import freemarker.template.TemplateException;
import freemarker.template.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrgService {
    @Autowired
    private FileService fileService;
    private final Configuration configuration;

    public  OrgService(Configuration configuration) {
        this.configuration = configuration;
    }

    public String addTemplate(Org org) throws IOException, TemplateException {
//        System.out.println(org.getName());
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("org", org);
        configuration.getTemplate("activation.ftl").process(model, stringWriter);
        String str = stringWriter.getBuffer().toString();
        String res = "";
        for (int i=0; i<str.length(); i++) {
            if (i+4<str.length() && str.substring(i, i+4).equals("{msg")) {
                res = res + '$';
            }
            res = res+str.charAt(i);
        }

//        File dest = new File("src/main/resources/templates/"+org.getName()+".ftl");
//        FileWriter wr = new FileWriter(dest);
//        wr.write(res);
//        wr.flush();
//        wr.close();

        MultipartFile multi = new MockMultipartFile("file", org.getName(), "text/plain", IOUtils.toByteArray(res));
        return fileService.addFile(multi);
    }

    public String updateTemplate(Org org) throws IOException, TemplateException {
//        System.out.println(org.getName());
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("org", org);
        configuration.getTemplate("activation.ftl").process(model, stringWriter);
        String str = stringWriter.getBuffer().toString();
        String res = "";
        for (int i=0; i<str.length(); i++) {
            if (i+4<str.length() && str.substring(i, i+4).equals("{msg")) {
                res = res + '$';
            }
            res = res+str.charAt(i);
        }

//        File dest = new File("src/main/resources/templates/"+org.getName()+".ftl");
//        FileWriter wr = new FileWriter(dest);
//        wr.write(res);
//        wr.flush();
//        wr.close();

        MultipartFile multi = new MockMultipartFile("file", org.getName(), "text/plain", IOUtils.toByteArray(res));
        return fileService.updateFileFromID(multi);
    }
}
