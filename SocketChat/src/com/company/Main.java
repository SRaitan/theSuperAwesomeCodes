package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {
static Map <String,String> usersMap = new HashMap<>();
    public static void main(String[] args) {
        try {

            ServerSocket serverSocket = new ServerSocket(3000);
            Socket socket = serverSocket.accept();
            System.out.println("newUser connected");
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
