package com.company;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
static Map <String,String> usersMap = new HashMap<>();
    public static final int port = 3000;
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            List<Message> messages = new ArrayList<>();
            messages.add(new Message("Welcome to this awesome chat:", " מצופה יש רק בשקם"));
            while (true){
                System.out.println("Waiting for incoming communication");
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                ClientThread clientThread = new ClientThread(clientSocket, messages);
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
