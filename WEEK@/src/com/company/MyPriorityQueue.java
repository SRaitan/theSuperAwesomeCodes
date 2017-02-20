package com.company;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.security.InvalidParameterException;
import java.util.concurrent.RecursiveTask;

/**
 * Created by eladlavi on 02/02/2017.
 */
public class MyPriorityQueue {
    private int[] arr;
    private int size;

    public MyPriorityQueue(){
        arr = new int[10];
        size = 0;
    }
    //runtime keeps going down q=1/2 from size/2 all leaves.
    // a level above we have 0(1), a level above as well.. only root is 0(logn)
    public MyPriorityQueue(int[] arr) {
        this.arr = arr;
        size=arr.length;
        for (int i = size/2; i >= 0; i--) { //from here size/2 we have only leaves
            heapify(i);
        }
    }
    // we assume both sons- right and left are ok. only 'i' might ruin the heap...
    private void heapify(int i){
        int largest = i; // me
        //fix me, then call recursively with the real largest if it's not me
        int l = leftChild(i);
        int r = rightChild(i);
        if(l<size && arr[l] > arr[largest])
            largest = l;
        if(r<size && arr[r] > arr[largest])
            largest = r;
        if(largest != i){
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(largest);
        }
    }
    // opposite--i is a node that might be larger than its parent. i's brother is maxHeap.
    private void bubbleUp (int i){
        int p;//parent
        while(i!=0 && arr[(p=parent(i))]<arr[i]){
            //in java you can assign inside boolean expr. (for, if while)
            int temp= arr[i];
            arr[i]=arr[p];
            arr[p]=temp;
            i=p;
            //swapped 'i' with father
        }
    }
    //ALL THESE FUNCS RETURN No IN HEAP, NOT VALUE!
    private int leftChild(int i){
        return 2*i + 1;
    }

    private int rightChild(int i){
        return 2*i + 2;
    }

    private int parent(int i){
        return (i-1)/2;
    }

    public void insert(int x){
        if(size == arr.length){
            int[] temp = new int[size * 2];
            for (int i = 0; i < size; i++) {
            temp[i] = arr[i];}
        arr = temp;
        }
        int i=size;
        size++;
        arr[i]=x;
        bubbleUp(i); //easier b/c we are at the end of arr and heapify begins from root
    //arr[size++] = x;
    }
    public void increase(int i, int addit){
        if(i>size || i<0) throw new IndexOutOfBoundsException();
        if(arr[i]>addit) throw new InvalidParameterException("i: "+i);
        arr[i]+=addit;
        bubbleUp(i);
    }
    ///*************************
    public void delete (int i){
        if(i>size || i<0) throw new IndexOutOfBoundsException();
        increase(i, Integer.MAX_VALUE-arr[i]);
        extractMax();
    }
    //************************ use other funcs in func
    public int getMax(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        int result = arr[0];
        for (int i = 1; i < size; i++) {
            if(arr[i] > result)
                result = arr[i];
        }
        return result;
    }
    //34 123 5 23 5  6 45 34 5 2 2
    public int extractMax(){
        if(size == 0)
            throw new IndexOutOfBoundsException();
        if(size == 1) {
            size--;
            return arr[0];
        }
        int ret = arr[0];
        arr[0] = arr[size-1];
        //we cant put something that is not in heap, so we out min value of heap
        heapify(0);//logN
        size--;
        return ret;
        /*int indexOfMax = 0;
        int result = arr[0];
        for (int i = 1; i < size; i++) {
            if(arr[i] > result) {
                result = arr[i];
                indexOfMax = i;
            }
        }
        for (int i = indexOfMax; i < size - 1; i++) {
            arr[i] = arr[i+1];
        }
        size--;
        return result;*/
    }
    public int getSize(){
        return size;
    }

}