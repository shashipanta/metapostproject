package com.meta.userpostproject.component;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class PostMultipartFile implements MultipartFile {

    private byte[] inputByteArr;
    private String fileName;

    public PostMultipartFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return this.fileName;
    }

    @Override
    public String getContentType() {
        return this.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return inputByteArr == null || inputByteArr.length == 0;
    }

    @Override
    public long getSize() {
        return inputByteArr.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return inputByteArr;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(inputByteArr);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        try(FileOutputStream fos = new FileOutputStream(dest)){
            fos.write(inputByteArr);
        }
    }
}
