package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(fib(150));
    }

    static int distance(int x, int y) {
        int dis = 0;
        int max, min;
        max = x;
        min = y;
        if ( x < y ) {
            min = x;
            max = y;
        }
        while ((min + dis) < max) {
            dis++;
        }
        return dis;
    }
    /*    int mainres=log(2,y);
        int rest=log(2,(y-pow(y,log(2,y))));
        return x<<mainres+x<<rest;*/
    static int prod(int x, int y)
    {
    int res=0;
    while(y>0){
        if(y%2==0){
            y=y>>1;
            x=x<<1;
        }else{
            y--;
            res+=x;
        }
    }
    return res;
    }

    static int div(int x, int y) {
        if ( x < y ) return 0;
        if (y == 0) return -1;//err
        int mone = 0;
        int res = x;
        while (res <= x) {
            res += y;
            mone++;
        }
        return mone;
    }
static int log2 (int num)
{
    if (num==0) return -1;
    if (num==1) return 0;
    if (num==2) return 1;
    return 1+log2(num/2);
}
    static int mod(int x, int y) {
        if(y==0) return -1;
        return x < y ? x : distance(x, prod(div(x, y), y));
    }
    //-------------------------------------------------------\\
    static int pow (int x, int y)
    {
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        }
        if (x == 1 || y==0)
            return 1;
        int result = x;
        for (int i = 1; i < y; i++)
            result *= x;
        return result;
    }
    static int goodPow(int x, int y)
    {
        int res=0;
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        }
        if (x == 1 || y==0)
            return 1;
        while(y>0){
            if((y&1)==0){//x^y --> x^(y/2)^2
            int temp=goodPow(x,y/2);
            return temp*temp;
            }else{ //x^y --> x^(y-1)*x
            int temp=goodPow(x,(y-1)/2);
            return temp*temp*x;
            }
        }
        return res;
    }
    static long fib(int n)// 0n
    {
        if(n==1||n==2) return 1;
        int a=0;
        int b=1;
        int c;
        for (int i = 2; i <=n ; i++)
        {
            c=a+b;
            a=b;
            b=c;
        }
        return b;
    }
}
