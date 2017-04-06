package com.company;
import java.io.File;
import java.util.Set;


public class Main {
    private static final String ADD_WORD = "1";
    private static final String VIEW_WORDS = "2";
    private static final String EXIT = "0";
    private static final String DECODE = "3";
    private static IO myIO = new UserIO();
    private static MyDictionary dictionary = new MyDictionary();

    public static void main(String[] args) {
        while (true) {
            myIO.output("\n Welcome! Please choose: ");
            myIO.output("1-Enter a new word for the dictionary");
            myIO.output("2-View dictionary");
            myIO.output("3-Decode an encrypted File (Caesar Cipher)");
            myIO.output("0-Exit\n");
            String choice = myIO.input();
            switch (choice) {
                case ADD_WORD:
                    myIO.output("Enter the word you want to add");
                    boolean success = dictionary.addWordToDictionary(myIO.input());
                    myIO.output(success ? "Added successfully" : "Your word already exists in the dictionary");
                    myIO.output("Would you like to add more words? Y/N");
                    String answer;

                    while (!(answer = myIO.input().toUpperCase()).equals("Y") && !answer.equals("N")) {
                        myIO.output("Please answer Y/N");
                    }
                    if (answer.equals("Y")) {
                        myIO.output("Enter words separated by commas. To finish press enter");
                        String userInput = myIO.input();
                        String[] words = userInput.split(",");
                        dictionary.addAllWordsToDictionary(words);
                    }

                    break;
                case VIEW_WORDS:
                    myIO.output("List of words:");
                    Set<String> tempDico = dictionary.getWords();
                    for (String word : tempDico)
                        myIO.output(word);
                    break;
                case DECODE:
                    try {
                        boolean valid = false;
                        File orgFile = null;
                        while (!valid) {
                            myIO.output("Enter the path of the file you want to decode");
                            //todo: give option to press 0 to exit
                            orgFile = new File(myIO.input());
                            valid = orgFile.isFile() && orgFile.canRead();
                        }
                        DecodeListener listener = new DecodeListener();
                        CodeBreaker FirstHalfOfCharBreaker =
                                new CodeBreaker(orgFile, Character.MIN_VALUE, Character.MAX_VALUE / 2, listener);
                        CodeBreaker SecondHalfOfCharBreaker = new CodeBreaker(orgFile, (Character.MAX_VALUE / 2) + 1, Character.MAX_VALUE, listener);
                        FirstHalfOfCharBreaker.start();
                        SecondHalfOfCharBreaker.start();
                        try {
                            FirstHalfOfCharBreaker.join();
                            SecondHalfOfCharBreaker.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(listener.realKey!=Integer.MIN_VALUE) {
                            String decrypted =
                                    new String(new CodeBreaker().tryDecode(FileManipulator.fileToCharArray(orgFile), listener.realKey));
                            myIO.output("Cipher Key "+listener.realKey);
                            myIO.output("Decrypted file:");
                            myIO.output(decrypted);
                        }
                        else myIO.output("Could not crack code, they're too good for me!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Please enter 1,2 or 3");
            }
        }
    }

}
