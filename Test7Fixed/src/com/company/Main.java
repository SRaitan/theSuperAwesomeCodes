package com.company;
import IOPackage.UserIO;
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu(new UserIO());
        menu.mainMenu();
    }
}