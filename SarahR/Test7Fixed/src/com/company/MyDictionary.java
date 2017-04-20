package com.company;

import java.util.*;

/**
 * Created by hackeru on 4/6/2017.
 */

public class MyDictionary {
    private Set<String> commonWords;
    private Set<String> words;

    Set<String> getCommonWords() {
        return commonWords;
    }

    /**
     * Returns common words as list of byte arrays so as to facilitate use with files
     * @return a list of byte arrays representing every common word in bytes
     */
    List<byte[]> getCommonWordsAsByteArrayList(){
        List<byte[]> result = new ArrayList<>();
        for (String word : commonWords) {
            result.add(word.getBytes());
        }
        return result;
    }

    MyDictionary() {
        initCommonWords();
        words = new TreeSet<>();
    }

    private void initCommonWords() {
        commonWords = new TreeSet<>();
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");
        commonWords.add("the");
    }

    Set<String> getAllWords() {
        //todo:add totalDico field & isUpdated flag
        Set<String> totalDico = words;
        totalDico.addAll(commonWords);
        return totalDico;
    }

    boolean addWordToDictionary(String word) {
        return !commonWords.contains(word) && words.add(word);
    }

    boolean addAllWordsToDictionary(String[] words){
        return Collections.addAll(commonWords, words);
    }


}