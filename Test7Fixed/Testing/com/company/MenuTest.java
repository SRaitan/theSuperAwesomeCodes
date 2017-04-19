package com.company;

import IOPackage.IO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuTest {
    Menu testMenu;
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
        when(testMenu.myIO.input()).thenReturn("3").thenReturn("C:\\Users\\hackeru.HACKERU3\\Desktop\\forTest7\\test_encrypted.txt").thenReturn("0");
        testMenu.mainMenu();
        verify(testMenu.myIO).output("Enter the path of the file you want to decode, 0 to exit");
        verify(testMenu.myIO).output("Key:  15");
        verify(testMenu.myIO).output("Decrypted file:");
        verify(testMenu.myIO).output("a.be and,the");
    }
}