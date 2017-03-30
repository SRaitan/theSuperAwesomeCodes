package com.company;

import java.io.*;
import java.net.Socket;

public class DownloadThread extends Thread {
    Socket clientSocket;
    File serverFile;
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(serverFile);
            inputStream = clientSocket.getInputStream();
            int fileNameSize = inputStream.read();
            byte[] fileName = new byte[fileNameSize];
            int actuallyRead;
            outputStream.write(fileName.length);
            outputStream.write(fileName);
            while ((actuallyRead = inputStream.read()) != -1){
                outputStream.write(actuallyRead);
            }
            // TODO: outputStream.write(OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }if(inputStream!= null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }if (clientSocket!=null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
