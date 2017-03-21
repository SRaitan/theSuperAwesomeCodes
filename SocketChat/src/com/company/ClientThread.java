package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static com.company.Main.usersMap;

public class ClientThread extends Thread {

    Socket clientSocket;
    User newUser;
    InputStream inputStream;
    OutputStream outputStream;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            byte[] buffer = new byte[1024];
            int actuallyRead; 
            String messageFromClient;
            System.out.println(getClientMsg(buffer));
            outputStream.write("Send 1 for signUp, 2 for signIn, and Username + Password".getBytes());
            messageFromClient = getClientMsg(buffer);
            System.out.println();
            String opt = returnOpt(messageFromClient);
            String username = returnUsername(messageFromClient);
            String password = returnPassword(messageFromClient);
            newUser = new User(username, password);
            switch (opt){
                case "1":
                    if(signUp (newUser))
                        outputStream.write(("Welcome " + newUser.getUsername()).getBytes());
                    else outputStream.write("not true".getBytes());
                    //TODO: CHECK RETURN VALUES
                    break;
                case "2":
                    if(signIn (newUser))
                    outputStream.write(("Welcome back" + newUser.getUsername()).getBytes());
                    else outputStream.write("not true".getBytes());
                    //TODO: CHECK RETURN VALUES 2
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
        }
    }

    private boolean signUp(User user) {
        synchronized (usersMap) {
            if (!usersMap.containsKey(user.getUsername())) {
                usersMap.put(user.getUsername(), user.getPassword());
                return true;
            }
            return false;
        }
    }

    private boolean signIn(User user) {
        return usersMap.containsKey(user.getUsername())
                &&  user.getPassword().equals(usersMap.get(user.getUsername()));
    }

    private String returnOpt (String fromUser){
        return fromUser.split("&")[0];
    }
    private String returnUsername (String fromUser){
        return fromUser.split("&")[1];
    } 
    private String returnPassword (String fromUser){
        return fromUser.split("&")[2];
    }

    private String getClientMsg(byte[] buffer) throws IOException {
        int actuallyRead;
        String messageFromClient;
        actuallyRead = inputStream.read(buffer);
        return new String(buffer, 0, actuallyRead);
        //System.out.println(messageFromClient);
    }
    
}
