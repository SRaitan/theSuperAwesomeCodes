package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Point pr=new Point(6,5);
        Point pointer=pr;
        Point yu=new Point(5,5);
        System.out.println(pr.hashCode());
        System.out.println(yu.hashCode());
        System.out.println("pr:"+pr);
        System.out.println("pointer:"+pointer);
        Point p1=null, p2=null;

    }
}
