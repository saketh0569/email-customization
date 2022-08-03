package com.example.sendemail.model;

public class FileDoc {
    private String filename;
    private String filetype;
//    private String filesize;
    private byte[] file;

    public FileDoc() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

//    public String getFilesize() {
//        return filesize;
//    }
//
//    public void setFilesize(String filesize) {
//        this.filesize = filesize;
//    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
