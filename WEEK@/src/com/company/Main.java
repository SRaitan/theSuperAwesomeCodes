package com.company;


public class Main {

    public static void main(String[] args) {
	// write your code here
        int [] arr={45,12,80,15,8,2,};
        heapsort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
    }
    static void heapsort (int [] arr){ //0(nlogn)
        MyPriorityQueue myPriorityQueue=new MyPriorityQueue(arr);
        for (int i = 0; i < arr.length; i++) { //0(n)*
            int max=myPriorityQueue.extractMax();//0log(n)
            arr[arr.length-1-i]=max; //0(1)
        }
    }
}
