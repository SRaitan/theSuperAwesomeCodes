package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by hackeru on 3/28/2017.
 */
public class GetMessagesThread extends Thread {
    public static final int PORT = 3000;
    public static final int SEND_MESSAGE = 100;
    public static final int GET_MESSAGES = 101;
    public static final int SIGN_UP = 102;
    public static final int LOGIN = 103;
    public static final int OKAY = 90;
    public static final int FAILURE = 80;
    public static final String SERVER_IP = "10.0.11.4";

    public boolean go = true;
    private int lastMsgRecieved;
    private User user;

    public GetMessagesThread(User user) {
        this.user=user;
        this.lastMsgRecieved = -1;
    }

    public void stopGettingMessages(){
        go=false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (go) {
            Socket clientSocket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                clientSocket = new Socket(SERVER_IP, PORT);
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                outputStream.write(GET_MESSAGES);

                user.streamUser(outputStream);

                byte[] lastMsgRecievedBytes = new byte[4];
                ByteBuffer.wrap(lastMsgRecievedBytes).putInt(lastMsgRecieved + 1);
                outputStream.write(lastMsgRecievedBytes);
                int messageLength;
                while ((messageLength = inputStream.read()) != -1) {
                    byte[] msgBytes = new byte[messageLength]; // can initialize to maxlength and then use from-to
                    inputStream.read(msgBytes);
                    String message = new String(msgBytes);
                    System.out.println(message); //todo: not SOLID!  declare interface MessageHandler and send the message to MessageHandler
                    lastMsgRecieved++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                CloseStreams(clientSocket, inputStream, outputStream);
                    stopGettingMessages();
            }
        }
    }

    static void CloseStreams(Socket clientSocket, InputStream inputStream, OutputStream outputStream) {
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
        try{
        Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
