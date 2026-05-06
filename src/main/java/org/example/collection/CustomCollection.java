package org.example.collection;

import java.util.Iterator;

public interface CustomCollection<T> extends Iterable<T> {
    void add(T element);
    T get(int index);
    void set(int index, T element);
    int size();
    void addAll(Iterable<? extends T> elements);
    Iterator<T> iterator();
}