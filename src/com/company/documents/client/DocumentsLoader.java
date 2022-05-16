package com.company.documents.client;

import java.io.*;

public class DocumentsLoader {
    BufferedOutputStream outputStream;
    BufferedWriter writer;
    String pathName;
    String fileName;
    public DocumentsLoader(BufferedOutputStream bufferedOutputStream, BufferedWriter writer, String pathName, String fileName){
        this.outputStream = bufferedOutputStream;
        this.writer = writer;
        this.pathName = pathName;
        this.fileName = fileName;
    }

    private void loader(byte[] byteArray, FileInputStream fileInputStream) throws IOException {
        while(true) {
            int i = fileInputStream.read(byteArray);
            outputStream.write(byteArray, 0, i);
            if(i < 1024)
                break;
        }
        outputStream.flush();
    }

    private void sendFileName() throws IOException {
        writer.write(fileName);
        writer.newLine();
        writer.flush();
    }

    public void load() throws IOException {
        sendFileName();
        File document = new File(pathName + fileName);
        byte[] byteArray = new byte[1024];
        FileInputStream fileInputStream = new FileInputStream(document.getPath());
        loader(byteArray, fileInputStream);
        fileInputStream.close();
    }
}
