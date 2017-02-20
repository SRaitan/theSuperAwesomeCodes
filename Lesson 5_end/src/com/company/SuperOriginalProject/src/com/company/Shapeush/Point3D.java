package com.company.Shapeush;

/**
 * Created by hackeru on 2/7/2017.
 */
public class Point3D extends Point {
    int z;
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
    public Point3D() {this(0,0,0);}
    public int getZ() {return z;}
    public void setZ(int z) {this.z = z;}
}
