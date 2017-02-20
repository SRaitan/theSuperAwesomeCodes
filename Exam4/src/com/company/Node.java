package com.company;

/**
 * Created by hackeru on 2/20/2017.
 */
public class Node<T> {
    T value;
    Node<T> next;
    Node<T> previous;

    public Node(T value) {this.value = value;}
}
