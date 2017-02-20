package com.company;
import java.security.*;
/**
 * Created by hackeru on 2/20/2017.
 */
public class MinHeap
   {
    private Comparable[] arr;
    private int size;

    MinHeap(){
        arr = (Comparable[]) new CoolNode[10];
        size = 0;
    }
    public MinHeap(Comparable[] arr){
        this.arr = arr;
        size = arr.length;
        for (int i = size/2; i >= 0; i--) {
            heapify(i);
        }
    }
    private boolean compare(Comparable x, Comparable y){return x.compareTo(y)==-1;}

    private void heapify(int i){
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if(l<size && compare(arr[l],arr[largest]))
            largest = l;
        if(r<size && compare(arr[r], arr[largest]))
            largest = r;
        if(largest != i){
            Comparable temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(largest);
        }
    }

    private void bubbleUp(int i){
        int p;
        while(i != 0 && compare(arr[i], arr[(p=parent(i))])){
            Comparable temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
            i = p;
        }
    }
    private int leftChild(int i){return 2*i + 1;}

    private int rightChild(int i){return 2*i + 2;}

    private int parent(int i){return (i-1)/2;}

    public void insert(Comparable x){
        if(size == arr.length){
            Comparable[] temp = (Comparable[])new Object[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        int i = size;
        size++;
        arr[i] = x;
        bubbleUp(i);
    }

    public Comparable getTop(){
        if(size == 0)
            throw new IndexOutOfBoundsException();

        Comparable result = arr[0];
        return result;
    }


    public Comparable extractMin(){
        if(size == 0)
            throw new IndexOutOfBoundsException();

        if(size == 1){
            size--;
            return arr[0];
        }
        Comparable min = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return min;
    }

    public int getSize(){
        return size;
    }
}
