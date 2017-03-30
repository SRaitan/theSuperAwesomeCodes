package com.company;


import java.io.*;
import java.net.Socket;

public class DownloadThread extends Thread {
    File fileToDownload;
    OutputStream outputStream;
    InputStream inputStream;
    Socket serverSocket;

    public DownloadThread(File fileToDownload) {
        this.fileToDownload = fileToDownload;
    }
    @Override
    public void run() {
        try {
            //transport to server
            outputStream = new FileOutputStream(fileToDownload);
            inputStream = serverSocket.getInputStream();
            InputStream fileInputStream = new FileInputStream(fileToDownload);
            //sending name of file to server
            byte[] fileName = setBytesArrayForInput();
            fileName = fileToDownload.getName().getBytes();
            int actuallyRead;
            outputStream.write(fileName.length);
            outputStream.write(fileName);
            // receiving file from server
            while ((actuallyRead = fileInputStream.read()) != -1){
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
                }if (serverSocket!=null)
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private byte[] setBytesArrayForInput() throws IOException {
        int length = inputStream.read();
        return new byte[length];
    }
}
