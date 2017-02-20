package com.company;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by hackeru on 2/7/2017.
 */
public class Point {
    public int x;
    public int y;

    @Override
    public boolean equals(Object obj) {
        if(obj==null) return false;
        if(obj==this) return true;
        if(obj instanceof Point){
            Point other = (Point) obj;
            return (this.x == other.x && this.y == other.y );}
        return false;
    }
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {return x;}
    public int getY() {
        return y;
    }
    public Point() {this(0,0);}
    public Point(Point other) {this(other.x,other.y);} //clone ctor
    public String lParen(){return "(";}
    public String rParen(){return ")";}
    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }
    private String printMe(){return this.x+","+this.y;}

    @Override
    public String toString() { return "("+printMe()+")"; }

    @Override
    public int hashCode() {//helps check when objects are not equal
        //if we are overriding method we want to make sure the hashcode() works as it should:
        //randomized numbers, yet same result for same values
        return (7 * x) ^ (11 * y) ^ (53 * y);
        //simple yet easy to implement and random--> primary numbers, bitwise XOR, and using 'y' twice
    }

}
