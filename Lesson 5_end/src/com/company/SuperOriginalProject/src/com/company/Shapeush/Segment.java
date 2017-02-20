package com.company.Shapeush;

public class Segment { //line segment
    private Point p1, p2;
    private double length;
    private boolean isLengthCalculated;
    //<><><><><><><><><><><><><><><><><><><>\\
    public Segment(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        //we are not  certain that length is always needed
        //so instead of calling method in ctor we use flag
        //in getLength
    }
    public Segment() { this (new Point(),new Point()); length=0;}
    public Point getP1() {
        return p1;
    }
    public void setP1(Point p1) { this.p1 = p1; isLengthCalculated=false;}
    public Point getP2() {
        return p2;
    }
    public void setP2(Point p2) { this.p2 = p2; isLengthCalculated=false;}
    double getLength() { //in order to save runtime
        if(!isLengthCalculated)
            calculateLength();
        return length;
    }
    public void setLength(double length) {this.length = length;}
    private void calculateLength (){ //expensive action so we made field
        int deltaY=p1.getY()-p2.getY();
        int deltaX=p1.getX()-p2.getX();
        length=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        isLengthCalculated=true;
    }
    double slope(){
        int deltaY=p1.getY()-p2.getY();
        int deltaX=p1.getX()-p2.getX();
        if (deltaX==0) return Double.MAX_VALUE;
        //perpenticular line has undefined slope
        return deltaY/deltaX;
    }
    // y = mx + b --> ax + by + c = 0

    /**
     * Our segment is on a line in Ax + By + C =0 format
     * @return A -- the multiplicand of x in the equation
     */
     double A(){return -1*slope();}
     double B(){ return 1; }
     double C(){ return slope()*p1.getX()-p1.getY(); }
     double distanceFromPoint (Point p){
        double A = A();// bc we use A() twice and it's a lot of calculations
        double B = B();// ditto
        double numerator= Math.abs(A * p.getX()+B * p.getY() + C());
        double denominator=Math.sqrt(A*A + B*B);
        return numerator/denominator;
    }

    @Override
    public boolean equals(Object obj) {
         if(obj instanceof Segment)
            return(((Segment) obj).getP1().equals(this.getP1()) && ((Segment) obj).getP2().equals(this.getP2())
                    || ((Segment) obj).getP1().equals(this.getP2()) && ((Segment) obj).getP2().equals(this.getP1())) ;
         //if the points are equal or the opposite--user entered the opposite information
         return false;
    }

    @Override
    public String toString() {
        return p1 + "-" + p2;
    }
}

