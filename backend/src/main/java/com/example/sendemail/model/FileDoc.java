package com.example.sendemail.model;

public class FileDoc {
    private String filename;
    private String filetype;
    private String templatetype;
    private Boolean flag;
//    private String filesize;
    private byte[] file;

    public FileDoc() {
    }

    public String getTemplatetype() {
        return templatetype;
    }

    public void setTemplatetype(String templatetype) {
        this.templatetype = templatetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
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
