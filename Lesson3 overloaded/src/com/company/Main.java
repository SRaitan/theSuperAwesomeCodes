package com.company;

public class Main {

    public static void main(String[] args) {
	// Overloaded
     /* int balonzo=9;
        Integer balonzoo=12;
        int y=balonzo;
        int yy=balonzoo;
        //balonzo++;
        System.out.println(y);
        //balonzoo++;
        System.out.println(yy);
        MyClass c=new MyClass(15);
        MyClass b=c;
        c.y++;
        System.out.println(b.y);
        Integer temp=balonzo; //BOXING
        balonzo++;
        System.out.println(temp);
        Boolean hj=false;*/
    byte b=9;
    Integer i=9;

   /* aMethod(b);// short.
    aMethod(i);// Object. If we didn't have Object-> int
    aMethod(12);//int. If we only had short+byte funcs-> compilation error b/c a number is generally int
    aMethod("9");//String. If we didn't have String-> Object*/
    }

  /*  public static void aMethod (int val){System.out.println("int");}
    public static void aMethod (short val){System.out.println("short");}
    public static void aMethod (Object val){System.out.println("Object");}
    public static void aMethod (String val){System.out.println("String");}*/

   /* public static void aMethod (byte val){System.out.println("byte");}
    public static void aMethod (short val){System.out.println("short");}*/

    //aMethod(9,10); //compilation error ambiguous function call
    public static void aMethod (long val, int val2){System.out.println("long, int");}
    public static void aMethod (int val, long val2){System.out.println("int, long");}
}
class MyClass{
    public int y;
    MyClass(int y){this.y=y;}
        }
