package com.company;

import java.io.*;
import java.net.Socket;
public class UploadThread extends Thread {
    Socket clientSocket;
    File serverFile;
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = new FileOutputStream(serverFile);
            int fileNameSize = inputStream.read();
            byte [] fileName = new byte[fileNameSize];
            int actuallyRead = inputStream.read(fileName);
            if(actuallyRead != fileNameSize)
                return;
            while ((actuallyRead = inputStream.read()) != -1){
                outputStream.write(actuallyRead);
            }
            // file is now what we received from client
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
