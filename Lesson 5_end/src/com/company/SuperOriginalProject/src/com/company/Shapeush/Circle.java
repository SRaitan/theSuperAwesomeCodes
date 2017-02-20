package com.company.Shapeush;

/**
 * Created by hackeru on 2/7/2017.
 */
public class Circle extends Shape{
    int x,y,radius;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }
    /**
     * SETS THE RADIUS OF THE CIRCLE
     * @param radius non-negative radius length
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI*radius*radius;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }

    public Circle() { this(0,0,0);}

    public Circle(int x, int y, int radius) {

        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
