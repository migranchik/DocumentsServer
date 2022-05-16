package com.company.documents.server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;


public class DocumentsDownloader {
    public BufferedInputStream downloaderBufferedInputStream;
    String fileName;
    public DocumentsDownloader(BufferedInputStream bufferedInputStream, String fileName) {
        downloaderBufferedInputStream = bufferedInputStream;
        this.fileName = fileName;
    }
    public void download() {
        byte[] byteArray = new byte[1024];
        try {

            // create new file in server
            File document = new File("C:\\Users\\79996\\IdeaProjects\\DocumentsServer\\src\\com\\company\\documents\\files\\" + fileName);
            document.createNewFile();

            // open fileWriter
            FileOutputStream fileWriter = new FileOutputStream(document);

            // recording file
            while(true) {
                int bytesCount = downloaderBufferedInputStream.read(byteArray);
                fileWriter.write(byteArray, 0, bytesCount);
                if(bytesCount < 1024)
                    break;
            }

            // close file writer
            fileWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
