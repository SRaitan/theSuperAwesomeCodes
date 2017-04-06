package com.company;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */

public class MyDictionary {
    Set<String> commonWords;
    private Set<String> words;

    MyDictionary() {
        commonWords = new HashSet<>();
        initCommonWords();
        words = new HashSet<>();
    }
    private void initCommonWords() {
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");
    }

    Set<String> getWords() {
        //todo: change to return array & add totalDico field & isUpdated flag
        Set<String> totalDico = words;
        totalDico.addAll(commonWords);
        //todo:sort
        return totalDico;
    }

    boolean addWordToDictionary(String word) {
       return !commonWords.contains(word) && words.add(word);
    }

    boolean addAllWordsToDictionary(String[] words){
        return Collections.addAll(commonWords, words);
    }

}
