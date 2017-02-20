package com.company;

import java.security.InvalidParameterException;

public class Main {
    public static void main(String[] args) {
        Number [] nums={5,7,4.3f,new Double(3), new Fraction(5,2)};
        System.out.println(sum(nums));
    }
    //*//*//*//*\\*\\*\\*\\*\\*\\רשימת הפונקציה של אובייקט זה לפי סוג המצביע לא אוביקט
    static boolean containsPairWithSumX2 (int [] arr, int sum){ //runtime 0(n)
        //this only works if we have numerator number range (max-min) that is workable.
        //better runtime vs bad memory usage --> so we mix* both:

        //*add: if (arr.length<100) return containsPairWithSumX(arr,sum);
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length-1 ; i++) {
            if(arr[i]>max)
                max=arr[i];
            if(arr[i]<min)
                min=arr[i];
        }
        //*add: if (max-min>1000) return containsPairWithSumX(arr,sum);
        boolean[] binmap = new boolean[max-min+1];
        for (int i = 0; i < arr.length; i++) {
            int temp = sum - arr[i]; //complement
            if (temp >= min && binmap[temp - min]) return true;
            binmap[arr[i] - min] = true;
        }
        return false;
    }
    //CLASSES & OVERLOADING
    public static double sum (Number[] nums)
    {
        double sum=0.0;
        for (Number num: nums) {
            sum+=num.doubleValue();//returns double value of given number
        }
        return sum;
    }
}
class Fraction extends Number
{
   private int numerator;
   private int denominator;
    //EX1 add method that gets another fraction and adds it to current fraction
    //Ex2 simplify fraction
    public Fraction()
    {
        this(0,1);
    }
    public Fraction(int numerator) {
        this(numerator,1);
    }
    Fraction(int numerator, int denominator)
    {
        if(denominator == 0) throw new InvalidParameterException("Division by zero!");
        this.numerator = numerator;
        this.denominator = denominator;
    }
    public void simplify ()
    {
        int d=GCD(denominator,numerator);
        numerator/=d;
        denominator/=d;
    }
    private int GCD(int x, int y){
        if(x==0) return y;
        return GCD(y%x, x);
    }
    public void add(Fraction num)
    {
        int gcd=GCD(this.denominator,num.denominator);
        int a=this.denominator/gcd;
        int b=num.denominator/gcd;
        this.denominator*=b;
        this.numerator*=b;
        //actual sum
        this.numerator+=num.numerator*a;
        simplify();
    }
    @Override
    public int intValue() {// not exact but this is the conversion
        return numerator/denominator;
    }
    @Override
    public long longValue() {
        return intValue();
    }
    @Override
    public float floatValue() {
        return (float)numerator/denominator;
        /*Note: return (float)(numerator/denominator) will not work because we
          are making the division result - which is int - a float*/
    }
    @Override
    public double doubleValue() {
        return floatValue();
    }
}