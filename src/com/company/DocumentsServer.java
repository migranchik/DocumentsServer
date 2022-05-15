package com.company;

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
                        Socket socket = serverSocket.accept();
                        BufferedWriter writer =
                                new BufferedWriter(
                                        new OutputStreamWriter(
                                                socket.getOutputStream()));
                        BufferedInputStream fileIn = new BufferedInputStream(socket.getInputStream());
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(socket.getInputStream())
                                );
                ) {
                    String fileName = reader.readLine();
                    byte[] byteArray = new byte[1024];
                    File document = new File("./" + fileName);
                    document.createNewFile();
                    FileOutputStream fos = new FileOutputStream(document);
                    while(true){
                        int i = fileIn.read(byteArray);
                        fos.write(byteArray, 0, i);
                        if(i < 1024)
                            break;
                    }
                    fos.close();
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
