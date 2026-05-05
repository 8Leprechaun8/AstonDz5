package org.example.collections;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Stream;

 public class CustomArrayList<T> implements CustomCollection<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T element) {
        ensureCapacity();
        elements[size++] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(int index, T element) {
        checkIndex(index);
        elements[index] = element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(Iterable<? extends T> elements) {
        for (T e : elements) {
            add(e);
        }
    }

    public void addAll(Stream<? extends T> stream) {
        stream.forEach(this::add);
    }


    public static <T> Collector<T, ?, CustomArrayList<T>> toCustomArrayList() {
        return Collector.of(
                CustomArrayList::new,
                CustomArrayList::add,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                }
        );
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;
            @Override
            public boolean hasNext() { return current < size; }
            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) elements[current++];
            }
        };
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newArr = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArr, 0, size);
            elements = newArr;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}