package com.company;
import java.io.*;
import java.net.Socket;
import static com.company.GetMessagesThread.*;
public class Main {
    //Socket Client
    public static void main(String[] args) {
        User user;
        String input;
        while (true){
            if ((user = menu()) == null)
                return;
            if(checkUser(user))
                break;
            else
                System.out.println(user.getChoice() == SIGN_UP? "Your username is apparently taken, buddy" : "Incorrect username or password, my friend");
        }

        GetMessagesThread getMessagesThread = new GetMessagesThread(user);
        getMessagesThread.start();
        while(!(input = inputUser()).equals("exit")){
            Socket clientSocket;
            InputStream inputStream;
            OutputStream outputStream;
            try{
                clientSocket = new Socket(SERVER_IP, PORT);
                inputStream = clientSocket.getInputStream();
                outputStream = clientSocket.getOutputStream();
                outputStream.write(SEND_MESSAGE);
                user.streamUser(outputStream);
                //todo: send with username
                byte[] inputBytes = input.getBytes();
                /*byte[] totalMsg = new byte[inputBytes.length + user.getUsername().length() + 2];
                int i = 0;
                byte[] userNameBytes = user.getUsername().getBytes();
                for (; i < userNameBytes.length; i++) {
                    totalMsg[i] = userNameBytes[i];
                }
                totalMsg[i++] = ':';
                totalMsg[i] = ' ';
                for (int j = 0; j < inputBytes.length; j++)
                    totalMsg[userNameBytes.length + 2 + j] = inputBytes[j];*/
                outputStream.write(inputBytes.length);
                outputStream.write(inputBytes);
                int result = inputStream.read();
                if(result != OKAY)
                    System.out.println("Error sending message");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        getMessagesThread.stopGettingMessages();
    }

        //region whatIdidBefore
        /*try {
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
            Thread.sleep(000);

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
        } catch (InterruptedException e) {
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
            if(socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/
        //endregion

    private static boolean checkUser(User user) {
        Socket clientSocket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            clientSocket = new Socket(SERVER_IP, PORT);
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            outputStream.write(user.getChoice());
            byte[] userNameBytes = user.getUsername().getBytes();
            byte[] pwBytes = user.getPassword().getBytes();
            outputStream.write(userNameBytes.length);
            outputStream.write(userNameBytes);
            outputStream.write(pwBytes.length);
            outputStream.write(pwBytes);
            int result = inputStream.read();
            return result == OKAY;
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            GetMessagesThread.CloseStreams(clientSocket,inputStream,outputStream);
        }
        //no reason to reach this
        return false;
    }

    private static User menu() {
        System.out.println("Please choose: 1-SignUp, 2-Login");
        System.out.println("At any point, type exit to exit this chat");
        String input;
        //input = inputUser();
        int action = 0;
        while (action == 0) {
            switch (input = inputUser()) {
                case "1":
                    action = SIGN_UP;
                    break;
                case "2":
                    action = LOGIN;
                    break;
                case "exit":
                    exit();
                default:
                    System.out.println("Yeah...that wasn't exactly 1, 2, or exit...^_^");
            }
        }
        System.out.println("Please enter username");
        String userName = inputUser();
        if (userName.equals("exit"))
            exit();
        //todo: loop until valid username
        System.out.println("Please enter password");
        String pw = inputUser();
        if (pw.equals("exit"))
            exit();
        return new User(userName, pw, action);
    }

    private static User exit() {
        System.out.println("I don't know why you want to leave such an awesome chat, but...you're out!");
        return null;
    }

    static String inputUser() {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
