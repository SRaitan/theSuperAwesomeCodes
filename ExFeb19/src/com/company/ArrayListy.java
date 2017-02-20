package com.company;

/**
 * Created by Raitan on 2/19/2017.
 */
public class ArrayListy <T> implements Listy<T> {
    T[] arr;
    int size;

    public ArrayListy() {
        this.arr = (T[])new Object[10];
        size=0;
    }
    private void fixOverflow(){
        T [] temp=(T[])new Object[size*3];
        for (int i = 0; i < arr.length; i++)
            temp[i] = arr[i];
        arr = temp;
    }

    public ArrayListy(T[] arr) {
        this.arr = arr;
        this.size=arr.length;
    }
    /**
     * adds x to end of list
     * @param x parameter to add
     */
    @Override
    public void add(T x) {
        if(size==arr.length)
            fixOverflow();
        arr[size++]=x;
    }

    @Override
    public void add(T x, int index) {
        if (index >= size || index<0) throw new IndexOutOfBoundsException();
        if (size + 1 == arr.length)
            fixOverflow();

        for (int i = size; i > index; i--)  //***\\
            arr[i]=arr[i-1];
        arr[index]=x;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index<0) throw new IndexOutOfBoundsException();
        for (int i = index; i < size-1; i++)
            arr[i-1]=arr[i];
        size--;
    }


    @Override
    public void set(int index, T x) {
        if (index >= size || index<0) throw new IndexOutOfBoundsException();
        arr[index]=x;
    }

    @Override
    public T get(int index) {
        if (index >= size || index<0) throw new IndexOutOfBoundsException();
        return arr[index];
    }

    @Override
    public int indexOf(T x) {
        for (int j = 0; j < size; j++) {
            if(arr[j]==x)
                return j;
        }
        return -1;
    }
    @Override
    public String toString() {
        if(size==0) return "{}";
        String s="{";
        for (int i = 0; i < size-1; i++)
            s+=arr[i]+",";
        s+=arr[size-1];
        return s+"}";
    }

    @Override
    public T [] toArray() {
        T  [] temp =(T[])new Object[size];
        for (int i = 0; i < size; i++)
            temp[i]=arr[i];
        return temp;
    }
}
