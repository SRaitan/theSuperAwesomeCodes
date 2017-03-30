package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final String UPLOAD_PICTURE = "1";
    public static final String DOWNLOAD_PICTURE = "2";
    public static final String EXIT = "0";
    public static int existingVersion=0;

    public static void main(String[] args) {
        printMenu();
    }
    //
    private static void printMenu() {
        while(true) {
            System.out.println("Enter: 1-Upload pic \n 2-Download pic \n 0-Exit");
            switch (getInputFromUser()) {
                case UPLOAD_PICTURE:
                    System.out.println("You are going to download the file of the server");
                    UploadThread uploadThread = new UploadThread(getPath());
                    uploadThread.start();
                    break;
                case DOWNLOAD_PICTURE:
                    File downloadPath = getDownloadPath();
                    downloadChoice(downloadPath, existingVersion);
                    if (downloadPath == null) exit();

                /*if(!file.exists()){
                    System.out.println("You have already downloaded this file");
                }
                else{
                    DownloadThread downloadThread = new DownloadThread(file);
                    downloadThread.start();
                }*/
                    break;
                case EXIT:
                    exit();
            }
        }
    }
    static void downloadChoice (File downloadPath, int existingVersion){
        System.out.println("downloading...");
        String downloadedFileName;
        if((downloadedFileName = downloadFromServer(downloadPath, existingVersion)) != null){
            System.out.println("successful download to "+ downloadedFileName);
        }
        else{
            System.out.println("error downloading");
        }

    }
    static String downloadFromServer (File downloadPath, int version){
        return "filename";

    }

    static void uploadChoice() {
        while (true) {
            System.out.println("enter path to file to upload");
            String input = getInputFromUser();
            File fileToUp = new File(input);
            if (fileToUp.exists() && fileToUp.isFile()) {
                if (uploadToServer(fileToUp)) {
                    System.out.println("uploaded successfully");
                    return;
                } else System.out.println("error uploading file");
            }
            else System.out.println("File is directory or nonexistent");
        }
    }

    private static boolean uploadToServer(File file) {
        return true;
    }

    private static File getDownloadPath() {
        while(true) {
            System.out.println("Please enter a download directory");
            String downloads = getInputFromUser();
            if(downloads.equals("exit"))
                return null;
            File downloadsFolder = new File(downloads);
            if (downloadsFolder.exists() && downloadsFolder.isDirectory())
                return downloadsFolder;
        }
    }


    private static File getPath() {
        System.out.println("enter a path");
        //todo: check things
       return new File(getInputFromUser());
    }
    private static void exit(){
        System.out.println("EXITING APP");
        System.exit(0);
    }

    public static String getInputFromUser() {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }


}
