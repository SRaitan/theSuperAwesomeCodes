package com.company;

/**
 * Created by hackeru on 4/6/2017.
 */
public class DecodeListener implements CodeBreaker.CodeBreakerListener {
    String decodedText = null;
    int cipherKey;

    @Override
    public void crackedCode(byte [] crackedCode, int key) {
        decodedText = new String(crackedCode);
        cipherKey=key;
    }
}