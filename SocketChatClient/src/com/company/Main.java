package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import static com.company.GetMessagesThread.*;

public class Main {

    public static void main(String[] args) {
        String input;
        User user;
        while(true) {
            if ((user = menu()) == null)
                return;
            else {
                if (checkUser(user))
                    break;
                else
                    System.out.println(
                            user.getAction() == SIGN_UP ?
                                    "your username has a problem, buddy" :
                                    "username or password are incorrect");
            }
        }

        System.out.println("Welcome to the awesome chat מצופה כשלפ יש רק בשקם");
        GetMessagesThread getMessagesThread = new GetMessagesThread(user);
        getMessagesThread.start();

        while(!(input = getInputFromUser()).equals("exit")){
            Socket clientSocket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try{
                clientSocket = new Socket(SERVER_IP, PORT);
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                outputStream.write(SEND_MESSAGE);
                user.stream(outputStream);
                byte[] inputBytes = input.getBytes();
                outputStream.write(inputBytes.length);
                outputStream.write(inputBytes);
                int result = inputStream.read();
                if(result != OKAY)
                    System.out.println("error sending message");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getMessagesThread.stopGettingMessages();
    }

    private static boolean checkUser(User user) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket(SERVER_IP, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(user.getAction());
            user.stream(outputStream);
            int result = inputStream.read();
            return result == OKAY;
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
            if(socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return false;
    }

    private static User menu() {
        String input;
        System.out.println("please choose:");
        System.out.println("1. sign up");
        System.out.println("2. login");
        System.out.println("at any point, type 'exit' to exit this program");
        input = getInputFromUser();
        int action = 0;
        while(action == 0){
            switch (input){
                case "1":
                    action = SIGN_UP;
                    break;
                case "2":
                    action = LOGIN;
                    break;
                case "exit":
                    System.out.println("c u later!");
                    return null;
                default:
                    System.out.println("invalid choice. try again");

            }
        }
        System.out.println("please enter user name:");
        String userName = getInputFromUser();
        if(userName.equals("exit"))
            return null;
        //TODO: loop until valid user name
        System.out.println("Please enter password:");
        String password = getInputFromUser();
        if(password.equals("exit"))
            return null;
        return new User(userName, password, action);
    }

    private static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}