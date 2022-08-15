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
import org.springframework.http.ResponseEntity;
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
import java.util.concurrent.TimeUnit;

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

        // get the base template and store it temporarily
        String st = fileService.getContentFromBaseTemplate(org.getType());
//        System.out.println("here "+st);
        File dest = new File("src/main/resources/templates/base.ftl");
        FileWriter wr = new FileWriter(dest);
        wr.write(st);
        wr.flush();
        wr.close();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        configuration.getTemplate(org.getType()+".ftl").process(model, stringWriter);
        configuration.getTemplate("base.ftl").process(model, stringWriter);
        String str = stringWriter.getBuffer().toString(); // contains the base template content as string
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

        MultipartFile multi = new MockMultipartFile("file", org.getName()+"_"+org.getType(), "text/plain", IOUtils.toByteArray(res));
//        MultipartFile multi = new MockMultipartFile(org.getType(), org.getName()+"_"+org.getType(), "text/plain", IOUtils.toByteArray(res));
        return fileService.addFile(multi, org.getType(), org.getFlag());
    }

    public String getTypeFromID(String id) throws IOException {
        return fileService.getTypeFromID(id);
    }

    public String updateTemplate(Org org) throws IOException, TemplateException {
        org.setType(getTypeFromID(org.getId()));
//        System.out.println(org.getType());
        deleteTemplate(org.getId());
        return addTemplate(org);
    }

    public void deleteTemplate(String id) throws IOException {
        fileService.deleteFileFromID(id);
    }

    public String preview(Org org) throws IOException, TemplateException {
        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("org", org);

        String st = fileService.getContentFromBaseTemplate(org.getType());
        File dest = new File("src/main/resources/templates/preview.ftl");
        FileWriter wr = new FileWriter(dest);
        wr.write(st);
        wr.flush();
        wr.close();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        configuration.getTemplate("preview.ftl").process(model, stringWriter);
        String str = stringWriter.getBuffer().toString(); // contains the base template content as string
//        String res = "";
//        boolean flag = false;
//        for (int i=0; i<str.length(); i++) {
//            if (i+4<=str.length() && str.charAt(i)=='{' && str.substring(i, i+4).equals("{msg")) {
//                flag = true;
//                res = res+'<';
//            }
//            else if (str.charAt(i)=='}' && flag) {
//                flag = false;
//                res = res+'>';
//            }
//            else
//                res = res+str.charAt(i);
//        }
        return str;
    }
}
