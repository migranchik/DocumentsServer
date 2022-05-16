package com.company.documents.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DocumentsServer {
    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(8000))
        {
            System.out.println("Server started!");
            while (true) {
                try (
                        // waiting for connect client
                        Socket socket = serverSocket.accept();
                        // create buffered writer
                        BufferedWriter writer =
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                socket.getOutputStream()));
                        // create buffered reader
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(socket.getInputStream())
                                );
                        // create buffered input stream for file
                        BufferedInputStream inputFileReader = new BufferedInputStream(socket.getInputStream());
                ) {
                    // read file name
                    String fileName = reader.readLine();
                    // downloading file
                    DocumentsDownloader downloader = new DocumentsDownloader(inputFileReader, fileName);
                    downloader.download();

                } catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
