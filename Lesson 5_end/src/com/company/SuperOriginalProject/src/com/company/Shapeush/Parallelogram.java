package com.company.Shapeush;

import java.security.InvalidParameterException;

public class Parallelogram extends Shape{
    private Point p1,p2,p3,p4;

    /**
     * Make sure that p1-p2 is equal in length and parallel to p3-p4
     * and p1-p3 is parallel to p2-p4
     * throws exception otherwise
     * @param p1 top-left coordinate
     * @param p2 top-right coordinate
     * @param p3 bottom-left coordinate
     * @param p4 bottom-right coordinate
     */
    public Parallelogram(Point p1,Point p2, Point p3, Point p4) {
        //p1----p2   p3----p4
        Segment s1= new Segment(p1,p2);
        Segment s2= new Segment(p3,p4);
        //we are aiming for minimal amount of checking so we use segments and check basic conditions
        if(s1.slope()!=s2.slope() || s1.getLength()!=s2.getLength())
            throw new InvalidParameterException();
        Segment s3= new Segment(p1,p3);
        Segment s4= new Segment(p2,p4);
        if (s3.slope()!=s4.slope())
            throw new InvalidParameterException();
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }

    public Point getP4() {
        return p4;
    }

    public void setP4(Point p4) {
        this.p4 = p4;
    }

    @Override
    public double perimeter() {
        Segment s1= new Segment(p1,p2);
        //Segment s2= new Segment(p2,p4);
        Segment s3= new Segment(p3,p4);
        //Segment s4= new Segment(p1,p3);
        return 2*s1.getLength()+2*s3.getLength();
    }

    @Override
    public double area() { // TODO: 2/7/2017 always try to use what we already wrote
        return new Triangle(p1,p2,p3).area()*2;
    }
}
