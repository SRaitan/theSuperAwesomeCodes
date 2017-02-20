package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        TwoDimensionalArray a= new TwoDimensionalArray(2,3);
        int t=0;
        for (int i = 0; i <2 ; i++) {
            for (int j = 0; j <3 ; j++) {
                a.set(i,j,t++);
            }
        }
        a.print();
        System.out.println("יששש הצלחנוו");
    }
}


class TwoDimensionalArray{
    //private int[][] arr;
    private int[] arr;
    private int rows;
    //private int columns;
    public TwoDimensionalArray(int rows_, int columns_){
        //arr = new int[rows][columns];
        rows=rows_;
        //columns=columns_;
        arr = new int[rows * columns_];
    }
    private int columns(){return arr.length/rows;}
    private int pos(int row, int col){return (row*columns())+col;}
    //write smart calcs once in separate func
    public void set(int row, int col, int value){
        if(row >= rows|| col >= columns())
            throw new IndexOutOfBoundsException();
        arr[pos(row,col)]=value;
    }
    public int get(int row, int col){
        if(row >= rows || col >= columns())
            throw new IndexOutOfBoundsException();
        return arr[pos(row,col)];
    }
    public void print(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns(); j++) {
                System.out.print(arr[pos(i,j)] + " ");
            }
            System.out.println();
        }
    }
static public int sumInArray(int z, int[] arr)
{
    for (int i = 0, j=arr.length; i < arr.length &  j>0 ; i++, j++) {

    }
    return 4;
}
}