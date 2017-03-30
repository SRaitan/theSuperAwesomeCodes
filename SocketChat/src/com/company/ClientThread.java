package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.List;

import static com.company.Main.usersMap;

public class ClientThread extends Thread {
    public static final int SEND_MESSAGE = 100;
    public static final int GET_MESSAGES = 101;
    public static final int SIGN_UP = 102;
    public static final int LOGIN = 103;
    public static final int OKAY = 90;
    public static final int FAILURE = 80;

    private Socket clientSocket;
    InputStream inputStream;
    OutputStream outputStream;
    private List<Message> messages;

    ClientThread(Socket clientSocket, List<Message> messages) {
        this.clientSocket = clientSocket;
        this.messages = messages;
    }

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            //region whatIdidBefore
/*            byte[] buffer = new byte[1024];
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
            case 1:
                if(signUp (newUser))
                    outputStream.write(("Welcome " + newUser.getUsername()).getBytes());
                else outputStream.write("not true".getBytes());
                //TODO: CHECK RETURN VALUES
                break;
            case 2:
                if(signIn (newUser))
                    outputStream.write(("Welcome back" + newUser.getUsername()).getBytes());
                else outputStream.write("not true".getBytes());
                //TODO: CHECK RETURN VALUES 2
                break;*/

            //endregion

            int action = inputStream.read();
            switch (action){
                case SEND_MESSAGE:
                    sendMessages();
                    break;
                case GET_MESSAGES:
                    getMessages();
                    break;
                case LOGIN:
                    logIn();
                    break;
                case SIGN_UP:
                    signUp();
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
    private String readFromStream(int length) throws IOException {
        if (length == -1)
            return null;
        byte[] bytesArray = new byte[length];
        int actuallyRead = inputStream.read(bytesArray);
        if (actuallyRead != length)
            return null;
        return new String(bytesArray);
    }
    private User readUserFromStream () throws IOException {
        //USERNAME
        User user = new User();
        int userNameLength = inputStream.read();
        user.setUsername(readFromStream(userNameLength));
        //PASSWORD
        int pwLength = inputStream.read();
        user.setPassword(readFromStream(pwLength));
        return user;
    }
    private boolean validUser(User u) throws IOException {
        if(u == null) return false;
        String existingPw = usersMap.get(u.getUsername());
        return existingPw != null && existingPw.equals(u.getPassword());
    }
    private void logIn() throws IOException {
        User user = readUserFromStream();
        outputStream.write(validUser(user)? OKAY : FAILURE);
    }
    private void getMessages() throws IOException {
        User user = readUserFromStream();
        if(!validUser(user))
            return;
        byte[] messageFromBytes = new byte[4];
        int actuallyRead = inputStream.read(messageFromBytes);
        if(actuallyRead != 4)
            return;
        int messageFrom = ByteBuffer.wrap(messageFromBytes).getInt();
        for (int i = messageFrom; i < messages.size(); i++) {
            String message = messages.get(i).getContent();
            //todo: change
            byte[] totalMessage = new byte[message.length() + messages.get(i).getSender().length()];

            byte[] messageBytes = message.getBytes();
            outputStream.write(messageBytes.length);
            outputStream.write(messageBytes);
            byte[] messageSenderBytes = messages.get(i).getSender().getBytes();
            outputStream.write(messageSenderBytes.length);
            outputStream.write(messageSenderBytes);
        }
    }
    private void sendMessages() throws IOException {
        User user = readUserFromStream();
        if(!validUser(user))
            return;
        int messageLength = inputStream.read();
        if(messageLength == -1)
            return;
        byte[] msgBytes = new byte [messageLength];
        int actuallyRead = inputStream.read(msgBytes);
        if(actuallyRead != messageLength)
            return;
        String byteMsg = new String(msgBytes);
        //when you add 2 bytes array this takes the address

        Message msg = new Message(new String(msgBytes), user.getUsername());
        messages.add(msg);
        outputStream.write(OKAY);
    }

    private void signUp() throws IOException {
        User user = readUserFromStream();
        boolean success = false;
        if(user == null) return;
        synchronized (usersMap) {
            if (!usersMap.containsKey(user.getUsername())) {
                usersMap.put(user.getUsername(), user.getPassword());
                success = true;
            }
            outputStream.write(success? OKAY : FAILURE);
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
