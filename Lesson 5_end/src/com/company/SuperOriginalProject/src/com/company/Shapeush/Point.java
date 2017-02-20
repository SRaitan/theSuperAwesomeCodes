package com.company.Shapeush;

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
        Point other = (Point) obj;
        return (this.x == other.x && this.y == other.y );
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
    @Override
    public String toString() {
        return "("+this.x+","+this.y+")";

        /*
        StringBuilder res=new StringBuilder();
        res.append("(");
        boolean inherits=false;
        Field fields[]=new Field[10];
        if(this.getClass().getSuperclass()!=null) {
            inherits = true;
            fields=this.getClass().getSuperclass().getDeclaredFields();
        }
        Field [] myFields=this.getClass().getDeclaredFields();
        try {
            if(inherits) {
                for (Field f : fields) {
                    res.append(f.get(this));
                    res.append(',');
                }
            }
            for (int i = 0; i < myFields.length; i++) {
                res.append(myFields[i].get(this));
                if(i!=myFields.length - 1)
                    res.append(',');
            }
        }
       catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res+")";
    }*/
    }

}
