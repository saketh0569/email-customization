package com.example.sendemail.controllers;

import com.example.sendemail.model.FileDoc;
import com.example.sendemail.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(fileService.addFile(file), HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> download(@RequestParam("id") String id) throws IOException {
        FileDoc loadFile = fileService.downloadFileFromID(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    @GetMapping("/download2")
    public ResponseEntity<ByteArrayResource> downloadFromFileName(@RequestParam("str") String str) throws IOException {
        FileDoc loadFile = fileService.downloadFileFromName(str);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(loadFile.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
                .body(new ByteArrayResource(loadFile.getFile()));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFromFileID(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("here 1");
//        FileDoc loadFile = fileService.updateFileFromID(id);
//        String id = "62e8097bef38bc5c4f1ebce7";
        return new ResponseEntity<>(fileService.updateFileFromID(file), HttpStatus.OK);
    }
}
