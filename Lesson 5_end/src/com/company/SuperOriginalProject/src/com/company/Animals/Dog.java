package com.company.Animals;

/**
 * Created by hackeru on 2/6/2017.
 */
public class Dog extends Animal {
    @Override
    public void makesound() {
        bark();
    }
    public void bark()
    {
        System.out.println("BARK BARK (This is a regular dog bark)");
    }

}
/*If i had a static method it cant modify non-static fields bc they are different*/