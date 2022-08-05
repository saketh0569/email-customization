package com.example.sendemail.service;

import com.example.sendemail.model.FileDoc;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {
    @Autowired
    private GridFsTemplate template;
    @Autowired
    private GridFsOperations operations;

    public String addFile(MultipartFile upload) throws IOException {
        DBObject metadata = new BasicDBObject();
        metadata.put("filesize", upload.getSize());
        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);
        return fileID.toString();
    }

    public FileDoc downloadFileFromID(String id) throws IOException {
        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));
        FileDoc filet = new FileDoc();
        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            filet.setFilename( gridFSFile.getFilename() );
            filet.setFiletype( gridFSFile.getMetadata().get("_contentType").toString() );
//            filet.setFilesize( gridFSFile.getMetadata().get("fileSize").toString() );
            filet.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }
        return filet;
    }

    public FileDoc downloadFileFromName(String str) throws IOException {
        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("filename").is(str)));
        FileDoc filet = new FileDoc();
        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            filet.setFilename( gridFSFile.getFilename() );
            filet.setFiletype( gridFSFile.getMetadata().get("_contentType").toString() );
//            filet.setFilesize( gridFSFile.getMetadata().get("fileSize").toString() );
            filet.setFile(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }
//        System.out.println(filet.getFilename());
        return filet;
    }

    public String updateFileFromID(MultipartFile upload) throws IOException {
        template.delete(new Query(Criteria.where("filename").is(upload.getOriginalFilename())));
        return this.addFile(upload);
    }
}
