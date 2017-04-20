package com.company;
import java.io.IOException;
import java.util.List;

public class CodeBreaker extends Thread {
    private MyDictionary dictionary;
    private byte [] encryptedBytes;
    private int begin, until;
    private CodeBreakerListener myListener;

    CodeBreaker(byte[] encryptedBytes, int begin, int until, CodeBreakerListener myListener) {
        this.dictionary = new MyDictionary();
        this.encryptedBytes = encryptedBytes;
        this.begin = begin;
        this.until = until;
        this.myListener = myListener;
    }

    @Override
    public void run() {
        try {
            if (myListener != null) {
                int cipherKey = findKey();
                if(wasFound(cipherKey)){
                    myListener.crackedCode(decryptBytes(encryptedBytes,cipherKey), cipherKey);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean wasFound(int cipherKey) {
        //if key was not found the value stays as Integer max value
        return cipherKey != Integer.MAX_VALUE;
    }

    int findKey() throws IOException {
        int key = begin;
        while(!isFileDecoded(encryptedBytes,encryptCommonWords(key), key)){
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
        int wordIndex = -1;
        String fileAsString = new String(file);
        for (byte[] word : encodedCommonWords) {
            while ((wordIndex = fileAsString.indexOf(new String(word), wordIndex + 1))!= -1) {
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

    interface CodeBreakerListener {
        void crackedCode(byte [] decryptedBytes, int key);
    }
}