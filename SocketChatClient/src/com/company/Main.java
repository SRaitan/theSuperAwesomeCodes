package com.company;

import java.io.*;
import java.net.Socket;

public class Main {
    public static final int PORT = 3000;

    //Socket Client
    public static void main(String[] args) {
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write("Hi server, I want to join the SocketChat".getBytes());
            byte[] buffer = new byte[1024];
            int actuallyRead = inputStream.read(buffer); //answer
            String responseFromServer = new String(buffer, 0, actuallyRead);
            System.out.println(responseFromServer);
            StringBuilder toServer = new StringBuilder();

            int option = Integer.valueOf(inputUser());
            toServer.append(option);
            toServer.append('&');

            System.out.println("Username");
            String username = inputUser();
            toServer.append(username);
            toServer.append('&');

            System.out.println("Password");
            String password = inputUser();
            toServer.append(password);
            toServer.append('&');

            outputStream.write(toServer.toString().getBytes());

            actuallyRead = inputStream.read(buffer); //answer
            responseFromServer = new String(buffer, 0, actuallyRead);
            System.out.println(responseFromServer);
            toServer = new StringBuilder();


            inputStream.close();
            inputStream = null;
            outputStream.close();
            outputStream = null;
            socket.close();
            socket = null;
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
    }
    public static String inputUser() {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
