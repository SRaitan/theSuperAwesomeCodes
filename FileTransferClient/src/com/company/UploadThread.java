package com.company;

import java.io.*;
import java.net.Socket;

/**
 * Created by hackeru on 3/29/2017.
 */
public class UploadThread extends Thread {
    File fileToUpload;
    OutputStream outputStream;
    InputStream inputStream;
    Socket serverSocket;

    public UploadThread(File fileToUpload) {
        this.fileToUpload = fileToUpload;
        /*this.outputStream = outputStream;
        this.inputStream = inputStream;*/
    }
    @Override
    public void run() {
        try{
            //transport to server
            outputStream = serverSocket.getOutputStream();
            inputStream = serverSocket.getInputStream();
            InputStream fileInputStream = new FileInputStream(fileToUpload);
            int actuallyRead;
            //sending name of file to server and its size
            String fileName = fileToUpload.getName();
            outputStream.write(fileName.length());
            outputStream.write(fileName.getBytes());
            //sending content of file to server
            while ((actuallyRead = fileInputStream.read()) != -1){
                outputStream.write(actuallyRead);
            } // TODO: outputStream.write(OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }if (serverSocket != null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
