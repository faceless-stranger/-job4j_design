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
        container = (container.length == size) ? arrayExpansion() : container;
        container[size++] = value;
        modCount++;
    }

    public T[] arrayExpansion() {
        T[] value = Arrays.copyOf(container, container.length > 0 ? container.length * 2 : 1);
        return value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T value = container[index];
        container[index] = newValue;
        return value;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T value = container[index];
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
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Была колекция была изменена");
                }
                if (size - 1 < indexIterator) {
                    throw new NoSuchElementException("next элементов больше нет");
                }
                return container[indexIterator++];
            }
        };
    }
}