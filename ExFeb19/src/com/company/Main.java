package com.company;
import java.io.*;

public class Main {
    public static final int ASCII_0 = 48;
    static ArrayListy<User> users=new ArrayListy<>();
    static boolean signed_in=false;
    public static void main(String[] args) {
        // write your code here
        printMenu();
    }
    public static void andAction(int choice){
        switch (choice){
            case 1:
                users.add(addNewUser());
                signed_in=true;
                printMenu();
                break;
            case 2:
                SignIn();
                signed_in=true;
                printMenu();
                break;
            case 3:
                if(signed_in){
                    System.out.println("All right! Ready to do some serious String-flipping?");
                    System.out.println("Enter your String:");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        String toFlip=bufferedReader.readLine();
                        System.out.println("Flipped String: " + reverseString(toFlip));
                    }
                    catch (IOException ex){
                        System.out.println("error reading");
                    }
                    printMenu();
                }
                else{
                    System.out.println("Sorry, we know you'd love to use this feature, but you have to be a member first");
                     printMenu();}
                break;
            case 4:
                System.out.println("You are now signed out of our awesome app");
                System.out.println("Come back soon!");
                signed_in=false;
                printMenu();
                break;
            default:
                System.out.println("Exiting");
        }
    }
    public static User addNewUser(){
        String user=null;
        String pw=null;
        System.out.println("Enter username. It must contain lowercase letters, and may end with numbers");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            user=bufferedReader.readLine();
            while(!isValid(user,true) || userExists(user)!=-1) {
                System.out.println("Please enter another username. The username you picked either exists or is incorrect");
                user=bufferedReader.readLine();
            }
        }
        catch (IOException ex){
            System.out.println("error reading");
        }
        System.out.println("Enter password. It may contain letters, characters and numbers");
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(System.in));
        try {
            pw=bufferedReader.readLine();
            System.out.println("Confirm password");
            String pw2=bufferedReader.readLine();
            while(!isValid(pw,false) || !pw.equals(pw2)) {
                System.out.println("Please re-enter password");
                pw = bufferedReader.readLine();
                System.out.println("Confirm new password");
                pw2 = bufferedReader.readLine();
            }
        }
        catch (IOException ex){
            System.out.println("error reading");
        }
        return new User(user,pw);
    }
    public static int userExists (String str){
        for (int i = 0; i < users.size ; i++)
            if(users.get(i).getUsername().equals(str))
                return i;
        return -1;
    }
    public static boolean isValid (String str, boolean user){
            if(user){
            byte[] checker = str.getBytes();
            if(checker.length<4 || checker.length>12)
                return false;
            for (int i = 0; i < checker.length; i++) {
                if(i==0 && !isLowercase(checker[i]) || (i!=0 && !isLowercase(checker[i]) && !isNumber(checker[i])))
                    return false;
            }
            return true;
        } //else if is password
        byte[] checker = str.getBytes();
        if(checker.length<4 || checker.length>12)
            return false;
        for (int i = 0; i < checker.length; i++) {
            if(checker[i]< ASCII_0 || checker[i]>126)
                return false;
        }
        return true;
    }
    public static boolean isLowercase(byte c){return (c<122 ||c<97);}
    public static boolean isNumber (byte c){return (c<57 ||c<48);}
    public static void printMenu(){
        System.out.println("*******************************************");
        System.out.println("Welcome to this crazily awesome application");
        System.out.println("You have never seen anything like this");
        System.out.println("Choose an option from the following list");
        System.out.println("1- Sign up to our app");
        System.out.println("2- Sign in to our app");
        System.out.println("3- Reverse a string (Available to members only, so hurry up and become one)");
        System.out.println("4- Sign out");
        System.out.println("5- Exit");
        System.out.println("*******************************************");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String choice=bufferedReader.readLine();
            andAction(Integer.parseInt(choice));
        }
        catch (IOException ex){
            System.out.println("error reading");
        }
    }
    public static boolean SignIn(){
        System.out.println("Enter your username");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String user=bufferedReader.readLine();
            while(userExists(user)==-1) {
                System.out.println("You don't seem to be a member. Please press 1 to sign up in main menu");
                user=bufferedReader.readLine();
                if(user.equals("1"))
                    printMenu();
            }
            User u=users.get(userExists(user));
            System.out.println("Enter your password");
            String pw=bufferedReader.readLine();
            while(!pw.equals(u.getPassword())){
                System.out.println("Please re-enter your password");
                pw=bufferedReader.readLine();
            }
            System.out.println("You are now signed in as " + user);
        }
        catch (IOException ex){
            System.out.println("error reading");
        }
        return false;
    }
    public static String reverseString (String original){
        if(original==null)
            return null;
        StringBuilder result=new StringBuilder();
        char[] word = original.toCharArray();
        int l = word.length-1;
        for (int i = 0; i <= l; i++)
            result.append(word[l-i]);
        return result.toString();
    }
}
