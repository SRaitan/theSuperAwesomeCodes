package com.company;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
UploadedFile uploadedFile;
Socket clientSocket;
private OutputStream fileOutputstream;
private InputStream inputStream;
private OutputStream outputStream;
public static final int UPLOAD = 100;
public static final int DOWNLOAD = 101;
public static final int OK = 90;
public static final int FAILURE = 80;
private InputStream fileInputstream;
    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action=inputStream.read();
            switch (action){
                case UPLOAD:
                    upload();
                    break;
                case DOWNLOAD:
                    download();
                    break;
                
            }
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
                }if (clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(fileOutputstream != null)
                try {
                    fileOutputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }if(fileInputstream!= null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void download() throws IOException {
        outputStream.write( uploadedFile.isLock() ? FAILURE : OK );
        fileInputstream = new FileInputStream(uploadedFile);
        //todo: reading/downloading lock
        int oneByte;
        while (())
    }

    private void upload() throws IOException {
        boolean lock = false;
        synchronized (uploadedFile){
            if(!uploadedFile.isLock()) {
                uploadedFile.lock();
                lock = false;
            }
        }
        outputStream.write( lock? FAILURE : OK );
        int fileNameLength = inputStream.read();
        if(fileNameLength == -1)
            return;
        byte[] filenameBytes = new byte[fileNameLength];
        int actuallyRead = inputStream.read(filenameBytes);
        if(actuallyRead != fileNameLength){
            uploadedFile.unlock();
            return;
        }
        fileOutputstream = new FileOutputStream(uploadedFile);
        int oneByte;
        while((oneByte = inputStream.read())!= -1)
            fileOutputstream.write(oneByte);
        fileOutputstream.close();
        fileOutputstream = null;
    }
}
