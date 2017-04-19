package com.company;

import IOPackage.IO;

import java.io.File;
import java.util.Set;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Menu {
    private static final String ADD_WORD = "1";
    private static final String VIEW_WORDS = "2";
    private static final String EXIT = "0";
    private static final String DECODE = "3";
    public IO myIO;
    private static MyDictionary dictionary = new MyDictionary();

    public Menu(IO myIO) {
        this.myIO = myIO;
    }

    public void mainMenu() {
        while (true) {
            myIO.output("Welcome! Please choose:\n " +
                    "1-Enter a new word for the dictionary\n " +
                    "2-View dictionary\n " +
                    "3-Decode an encrypted file (Caesar Cipher)\n " +
                    "0-Exit \n");
            String choice = myIO.input();
            switch (choice) {
                case ADD_WORD:
                    addWord();
                    break;
                case VIEW_WORDS:
                    printDictionary();
                    break;
                case DECODE:
                    try {
                        boolean valid = false;
                        File orgFile = null;
                        while (!valid) {
                            myIO.output("Enter the path of the file you want to decode, 0 to exit");
                            String path = myIO.input();
                            if (path.equals("0"))
                                return;
                            orgFile = new File(path);
                            valid = orgFile.isFile() && orgFile.canRead();
                        }
                        DecodeListener listener = new DecodeListener();
                        startDecoding(orgFile, listener);
                        getDecryptedText(listener);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    return;
                default:
                    myIO.output("Please enter 1, 2, 3 or 0 to exit");
            }
        }
    }

    private void startDecoding(File orgFile, DecodeListener listener) {
        CodeBreaker FirstHalfOfCharBreaker =
                new CodeBreaker(orgFile, 0, Byte.MAX_VALUE/ 2, listener);
        CodeBreaker SecondHalfOfCharBreaker = new CodeBreaker(orgFile, (Byte.MAX_VALUE/ 2) + 1, Byte.MAX_VALUE, listener);
        FirstHalfOfCharBreaker.start();
        SecondHalfOfCharBreaker.start();
        try {
            FirstHalfOfCharBreaker.join();
            SecondHalfOfCharBreaker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getDecryptedText(DecodeListener listener) {
        if(listener.decodedText != null) {
            myIO.output("Key:  " + listener.cipherKey);
            myIO.output("Decrypted file:");
            myIO.output(listener.decodedText);
        }
        else myIO.output("Could not crack code, they're too good for me!");
    }

    private void addWord() {
        myIO.output("Enter the word you want to add");
        boolean success = dictionary.addWordToDictionary(myIO.input());
        myIO.output(success ? "Added successfully" : "Your word already exists in the dictionary");
        myIO.output("Would you like to add more words? Y / N");
        String answer;
        while (!(answer = myIO.input().toUpperCase()).equals("Y") && !answer.equals("N")) {
            myIO.output("Please answer Y / N");
        }
        if (answer.equals("Y")) {
            myIO.output("Enter words separated by commas. To finish press enter");
            String userInput = myIO.input();
            String[] words = userInput.split(",");
            dictionary.addAllWordsToDictionary(words);
        }
    }

    private void printDictionary() {
        myIO.output("List of words:");
        Set<String> tempDico = dictionary.getAllWords();
        for (String word : tempDico)
            myIO.output(word);
    }
}
