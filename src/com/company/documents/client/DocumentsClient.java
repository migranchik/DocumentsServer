package com.company.documents.client;

import java.io.*;
import java.net.Socket;

public class DocumentsClient {
    public static void main(String[] args) {
        String pathName = "C:\\Users\\79996\\IdeaProjects\\DocumentsServer\\src\\com\\company\\documents\\client\\";
        String fileName = "lox.pdf";
        try (
                Socket client = new Socket("127.0.0.1", 8000);
                BufferedWriter writer =
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        client.getOutputStream()));
                BufferedOutputStream fileOut = new BufferedOutputStream(client.getOutputStream());
                BufferedReader reader =
                        new BufferedReader(
                                new InputStreamReader(client.getInputStream())
                        );
        )
        {
            DocumentsLoader documentsLoader = new DocumentsLoader(fileOut, writer, pathName, fileName);
            documentsLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}