package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CodeBreaker extends Thread {
    private MyDictionary dictionary;
    private byte[] encryptedFileBytes;
    private int begin, until;
    private CodeBreakerListener myListener;

    CodeBreaker(File encryptedFile, int begin, int until, CodeBreakerListener myListener) {
        dictionary = new MyDictionary();
        try {
            this.encryptedFileBytes = fileToByteArray(encryptedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.begin = begin;
        this.until = until;
        this.myListener = myListener;
    }

    @Override
    public void run() {
        try {
            int cipherKey = findKey();
            if (myListener != null) {
                if(cipherKey != Integer.MAX_VALUE){
                    //if key was actually found
                    myListener.crackedCode(decryptBytes(encryptedFileBytes,cipherKey), cipherKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int findKey() throws IOException {
        int key = begin;
        while(!isFileDecoded(encryptedFileBytes,encryptCommonWords(key), key)){
            key++;
            if(key == until)
                return Integer.MAX_VALUE; //key was not found
        }
        return key;
    }

    private List<byte[]> encryptCommonWords(int key) {
        List<byte[]> commonWords = dictionary.getCommonWordsAsByteArrayList();
        for (byte[] word : commonWords)
            encryptBytes(word,key);
        return commonWords;
    }

    byte[] encryptBytes(byte[] bytes, int key) {
        for (int i = 0; i < bytes.length; i++)
            bytes[i] += key;
        return bytes;
    }

    byte[] decryptBytes(byte[] bytes, int key){
        return encryptBytes(bytes,-key);
    }

    private boolean isFileDecoded(byte [] file, List<byte[]> encodedCommonWords, int key) {
        int success = 3;
        boolean reachedEndOfFile;
        int wordIndex;
        String fileAsString = new String(file);
        for (byte[] word : encodedCommonWords) {
             if((wordIndex = fileAsString.indexOf(new String(word)))!= -1) {
                 reachedEndOfFile = (wordIndex + word.length == fileAsString.length());
                 int charAfterWord = !reachedEndOfFile ? fileAsString.charAt(wordIndex + word.length) : 0;
                 //excluding beginning and end of file, checks words
                 if (wordIndex - 1 == -1 || isCommaBlankOrSpace(fileAsString.charAt(wordIndex - 1),key))
                     if (reachedEndOfFile || isCommaBlankOrSpace(charAfterWord, key))
                         success--;
             }
        }
        return success <= 0;
    }

    private boolean isCommaBlankOrSpace(int charToCheck, int key) {
        byte encryptedBlank =(byte)(' ' + key);
        byte encryptedFullStop =(byte)('.' + key);
        byte encryptedComma =(byte)(',' + key);
        return charToCheck == encryptedBlank
                || charToCheck == encryptedFullStop || charToCheck == encryptedComma;
    }

    /**
     * This func used to be in separate class for SOLID but was moved to here since it was the only func there
     * @param file original file to be converted to byte array
     * @return byte array representing file
     * @throws IOException if file is invalid
     */
    private byte[] fileToByteArray(File file) throws IOException {
        byte[] fileBytes = new byte[(int) file.length()];
        InputStream fileInputStream = new FileInputStream(file);
        int actuallyRead;
        int counter = 0;
        while ((actuallyRead = fileInputStream.read()) != -1)
            fileBytes[counter++] = (byte) actuallyRead;
        return fileBytes;
    }

    interface CodeBreakerListener {
        void crackedCode(byte [] decryptedFile, int key);
    }
}