package com.company;
/**
 * Created by Raitan on 2/9/2017.
 */
public class TestHeap {
    int[] arr;
    int realSize;
    int median;
    static int quickSelect(int arr[], int r, int l, int k) {
        if (k > 0 && k <= r - l + 1) {
            int pos = Main.partition(arr, l, r);
            if (pos - l == k - 1)
                return pos;
            if (pos - l > k - 1)
                return quickSelect(arr, l, pos - 1, k);
            return quickSelect(arr, pos + 1, r, k - pos + l - 1);//bc we are still dealing with the original array
        }
        return Integer.MAX_VALUE; //err ^_^
    }

    public TestHeap() {
        this(new int[20]);
        realSize=0;
    }
    public TestHeap(int[] arr) {this.arr = arr;}

    int div2(int a){
        if((a&1)==0) return a/2;
        return a/2+1;
    }
    void ex3 (){
        //part 1 & 2
        int pos=quickSelect(arr,0,arr.length,arr.length/2);
        median = arr[pos];//finds median and also organizes array
        int i=pos;
        while(i<realSize){
            pos=quickSelect(arr,pos,arr.length,pos/2); //new median

        }

    }
    private void removeAt(int i) { // by index
        if (realSize == 0) throw new IllegalArgumentException();
        if (i >= realSize) throw new IndexOutOfBoundsException();
        if (i == realSize-1) {//final leaf
            --realSize;
            return;
        }
        arr[i] = arr[realSize-1]; //puts last leaf into what we want to delete
        --realSize;
        // the new node here can be smaller than the previous,
        // so it might be smaller than the parent-->bubble up
        // if that is the case
        if (i > 0 && arr[i] > arr[(i-1)/2]) {
            bubbleUp(i);
        } else if (i < realSize/2) {
            // The new value could be larger
            // than that of the child--> heapify
            heapifyMin(i,realSize);
        }
    }
    private int peek (int x){return arr[x];} //0(1)

    public void remove(int value) {// according to value, finds index
        for(int i = 0; i < realSize; ++i) {
            if (arr[i] == value) {
                removeAt(i);
                break;
            }
        }
    }
    public void insert(int x){
        if(realSize == arr.length){
            int[] temp = new int[realSize * 2];
            for (int i = 0; i < realSize; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        int i = realSize;
        realSize++;
        arr[i] = x;
        bubbleUp(i);
    }
    private void bubbleUp(int i){
        int p;
        while(i != 0 && arr[(p=parent(i))] < arr[i]){
            int temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
            i = p;
        }
    }
    private void heapifyMin (int i, int size){
        int smallest=i;
        int l=leftChild(i);
        int r=rightChild(i);
        if(l<size && arr[l]<arr[smallest])
            smallest=l;
        if(r<size && arr[r] < arr[smallest])
            smallest=r; //now we found who is the smallest out of 3 --> father & 2 sons
        if(smallest!=i){
            int temp=arr[i];
            arr[i]=arr[smallest];
            arr[smallest]=temp;
            heapifyMin(smallest,size);
        }
    }
    private int leftChild(int i){
        return 2*i+1;
    }
    private int rightChild(int i){
        return 2*i+2;
    }
    private int parent(int i){
        return ((i-1)/2);
    }

}