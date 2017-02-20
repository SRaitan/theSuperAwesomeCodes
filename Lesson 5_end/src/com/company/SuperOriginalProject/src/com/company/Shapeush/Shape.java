package com.company.Shapeush;

public abstract class Shape {
    public static int counter=0;
    public final void refresh() {System.out.println("I am the REFRESH methooooood");}

    public Shape() {
        counter++;
    }

    public abstract double area();

    public abstract double perimeter();
}
/*Whatever inherits from abstract class must either be abstract or implement abstract methods.
* Likewise, it is a problem if a abstract class inherits from a non abstract one.
* final is a bit better for computer performance but that should be irrelevant when coding
* final ---> can't inherit from final class,
 * or inheritors can't change final methods,
 * or inheritors can't change value of final fields*/