package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            arrayExpansion();
        }
        container[size++] = value;
        modCount++;
    }

    private void arrayExpansion() {
        container = Arrays.copyOf(container, container.length > 0 ? container.length * 2 : 1);
    }

    @Override
    public T set(int index, T newValue) {
        T value = get(index);
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(
                container, index + 1,
                container, index,
                container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int indexIterator = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("hasNext колекция была изменена");
                }
                return size > indexIterator;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("next элементов больше нет");
                }
                return container[indexIterator++];
            }
        };
    }
}