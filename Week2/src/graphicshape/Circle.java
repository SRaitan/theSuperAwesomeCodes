package graphicshape;

/**
 * Created by hackeru on 2/5/2017.
 */
public class Circle extends Shape{
    int x, y, radius;

    public Circle() {
        this(10);
    }
    public Circle (int x, int y, int radius){
        if(x>=0)
            this.x=x;
        else this.x=Math.abs(x);
        if(y>=0)
            this.y=y;
        else this.y=Math.abs(y);
        if(radius>=0)
        this.radius=radius;
        else this.radius=Math.abs(radius);
    }
    public Circle(int radius){
        this(20,20, radius);
    }
    public String toString(){
        return "balonzooo   " + super.toString();
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

