package com.company;

import java.io.File;
import java.io.IOException;

/**
 * Created by hackeru on 4/6/2017.
 */

public class CodeBreaker extends Thread {
    private MyDictionary dictionary;
    private File encryptedFile;
    private int begin, until;
    CodeBreakerListener myListener;

    public CodeBreaker(File encryptedFile, int begin, int until, CodeBreakerListener myListener) {
        dictionary = new MyDictionary();
        this.encryptedFile = encryptedFile;
        this.begin = begin;
        this.until = until;
        this.myListener = myListener;
    }

    @Override
    public void run() {
        try {
            int cipherKey = decode(encryptedFile);
            if (myListener != null)
                myListener.crackedCode(cipherKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int decode(File encryptedFile) throws IOException {
        int key = begin;
        char[] fileChars = FileManipulator.fileToCharArray(encryptedFile);
        while(!isGoodCode(tryDecode(fileChars, key))){
            if(key == until)
                return Integer.MAX_VALUE;
            key++;
        }
       return key;
    }
    //should be in separate class that dea

    public CodeBreaker() {}

    public char[] tryDecode(char[] fileChars, int key) {
        for (int i = 0; i < fileChars.length; i++)

            fileChars[i] += key;
        return fileChars;
    }
    private boolean isGoodCode (char[] attempt) {
        int success = 3;
        String attemptStr = new String(attempt);
        String [] words = attemptStr.split(" ");
        for (String word : words) {
            if(word.endsWith(".") || word.endsWith(","))
                word = word.substring(0,word.length() - 1);
            if(dictionary.commonWords.contains(word))
                success--;
        }
        return success == 0;
    }
    interface CodeBreakerListener {
        void crackedCode(int key);
    }
}
