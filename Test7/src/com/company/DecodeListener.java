package com.company;

/**
 * Created by hackeru on 4/6/2017.
 */
public class DecodeListener implements CodeBreaker.CodeBreakerListener {
    int realKey = Integer.MIN_VALUE;

    @Override
    public void crackedCode(int key) {
        if (key != Integer.MAX_VALUE)
            realKey = key;
    }
}
