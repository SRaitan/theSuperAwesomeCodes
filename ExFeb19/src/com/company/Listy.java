package com.company;

/**
 * Created by Raitan on 2/19/2017.
 */
public interface Listy<T> {
    void add(T x);
    void add(T x, int index);
    void remove(int index);
    void set(int index, T x);
    T get(int index);
    /**
     * finds an element in the list
     * @param x what we are looking for
     * @return first zero-based position of x in list. -1 if not found
     */
    int indexOf(T x);
    T[] toArray();
}
