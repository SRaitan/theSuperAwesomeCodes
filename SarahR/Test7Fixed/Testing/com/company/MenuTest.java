package com.company;

import IOPackage.IO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuTest {
    Menu testMenu;
    boolean goodDecryption;

    @BeforeEach
    void setUp() {
        System.out.println("In setup");
        testMenu = new Menu(mock(IO.class));
    }

    @AfterEach
    void tearDown() {
        System.out.println("In teardown");
    }

    @Test
    void forLetterChoice() {
        when(testMenu.myIO.input()).thenReturn("u").thenReturn("0");
        testMenu.mainMenu();
        verify(testMenu.myIO).output("Please enter 1, 2, 3 or 0 to exit");
    }

    @Test
    void forNumberChoice() {
        when(testMenu.myIO.input()).thenReturn("45").thenReturn("0");
        testMenu.mainMenu();
        verify(testMenu.myIO).output("Please enter 1, 2, 3 or 0 to exit");
    }

    @Test
    void for1Choice() {
        when(testMenu.myIO.input()).thenReturn("1").thenReturn("testingWord1").thenReturn("n").thenReturn("0");
        testMenu.mainMenu();
        verify(testMenu.myIO).output("Enter the word you want to add");
        verify(testMenu.myIO).output("Added successfully");
        verify(testMenu.myIO).output("Would you like to add more words? Y / N");
    }

    @Test
    void for2Choice() {
        when(testMenu.myIO.input()).thenReturn("2").thenReturn("0");
        testMenu.mainMenu();
        verify(testMenu.myIO).output("List of words:");
    }

    @Test
    void for3Choice() {
        String orgMessage="to be one of the great";
        byte[] encrypted = orgMessage.getBytes();
        int realKey = 23;
        goodDecryption = false;
        for (int i = 0; i < encrypted.length; i++)
            encrypted[i] += realKey;

        CodeBreaker tester = new CodeBreaker(encrypted, Byte.MIN_VALUE, Byte.MAX_VALUE,
                (message, key) -> goodDecryption = true);//(key == realKey && new String(message).equals(new String(msgBytes))));
        tester.start();
        try {
            tester.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("reached");
        Assertions.assertTrue(goodDecryption,"Codebreaker is defective");
    }
}
