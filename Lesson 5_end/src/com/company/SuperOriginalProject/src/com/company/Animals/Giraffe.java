package com.company.Animals;

/**
 * Created by hackeru on 2/6/2017.
 */
public class Giraffe extends Animal {
    public static int counter=0;

    static void incrementCount(){counter++;}

    public Giraffe() {
        incrementCount();
    }

    public void makesound()
    {
        System.out.println("I am a giraffe I don't make sounds");
    }
}
