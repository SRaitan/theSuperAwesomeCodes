package com.company;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by eladlavi on 29/03/2017.
 */
public class ClientThread extends Thread {

    private UploadedFile uploadedFile;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private OutputStream fileOutputStream;
    private InputStream fileInputStream;
    public static final int UPLOAD = 100;
    public static final int DOWNLOAD = 101;
    public static final int OKAY = 90;
    public static final int FAILURE = 91;

    boolean shouldDecrement = false;

    public ClientThread(UploadedFile uploadedFile, Socket clientSocket) {
        this.uploadedFile = uploadedFile;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
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
        }finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(uploadedFile != null && uploadedFile.isLocked())
                uploadedFile.unlock();
            if(shouldDecrement)
                uploadedFile.concurrentDownloaded.decrementAndGet();
        }
    }

    private void upload() throws IOException {
        boolean lock = true;
        synchronized (uploadedFile){
            if(!uploadedFile.isLocked() && uploadedFile.concurrentDownloaded.get()==0){
                uploadedFile.lock();
                lock = false;
            }
        }
        outputStream.write(lock ? FAILURE : OKAY);
        if(lock)
            return;
        int fileNameLength = inputStream.read();
        if(fileNameLength == -1) {
            uploadedFile.unlock();
            return;
        }
        byte[] fileNameBytes = new byte[fileNameLength];
        int actuallyRead = inputStream.read(fileNameBytes);
        if(actuallyRead != fileNameLength){
            uploadedFile.unlock();
            return;
        }
        fileOutputStream = new FileOutputStream(uploadedFile);
        int oneByte;
        while((oneByte = inputStream.read()) != -1) {
            fileOutputStream.write(oneByte);
        }
        fileOutputStream.close();
        fileOutputStream = null;
        uploadedFile.setFileNameBytes(fileNameBytes);
        uploadedFile.increaseVersion();
        uploadedFile.unlock();
    }

    private void download() throws IOException {
        if(uploadedFile.isLocked()) {
            outputStream.write(FAILURE);
            return;
        }else{
            outputStream.write(OKAY);
        }
        byte[] versionBytes = new byte[4];
        ByteBuffer.wrap(versionBytes).putInt(uploadedFile.getVersion());
        outputStream.write(versionBytes);
        int shouldSendFile = inputStream.read();
        if(shouldSendFile != OKAY) {
            return;
        }
        uploadedFile.concurrentDownloaded.incrementAndGet();
        shouldDecrement = true;
        outputStream.write(uploadedFile.getFileNameBytes().length);
        outputStream.write(uploadedFile.getFileNameBytes());
        fileInputStream = new FileInputStream(uploadedFile);
        // TODO: reading / downloading lock
        int oneByte;
        while((oneByte = fileInputStream.read()) != -1) {
            outputStream.write(oneByte);
        }
        fileInputStream.close();
        fileInputStream = null;
    }
}