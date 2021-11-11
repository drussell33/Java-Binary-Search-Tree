package edu.wou.algorithms;

import java.security.InvalidKeyException;
import java.util.function.Consumer;

public interface IBinarySearchTree<K extends Comparable<K>, V> {
    void insert(K key, V value) throws InvalidKeyException;
    void remove(K key) throws InvalidKeyException;
    V find(K key) throws InvalidKeyException;
    void preOrderTraversal(Consumer visit);
    void inOrderTraversal(Consumer visit);
    void postOrderTraversal(Consumer visit);
    void breadthFirstTraversal(Consumer visit);
    int size();
    int getHeight();
    boolean isFull();
    boolean isPerfect();
    boolean isComplete();
}
