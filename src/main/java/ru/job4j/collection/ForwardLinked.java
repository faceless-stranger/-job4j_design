package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        ForwardLinked.Node<T> newNode = new ForwardLinked.Node<>(value, null);
        if (head == null) {
            head = newNode;
        } else {
            ForwardLinked.Node<T> item = head;
            while (item.next != null) {
                item = item.next;
            }
            item.next = newNode;
        }
        size++;
        modCount++;
    }

    public void addFirst(T value) {
        Node<T> node = head;
        head = new Node<>(value, node);
        size++;
        modCount++;
        }

    public T get(int index) {
        Objects.checkIndex(index, size);
        ForwardLinked.Node<T> value = head;
        for (int i = 0; i < index; i++) {
            value = value.next;
        }
        return value.item;
    }

    public T deleteFirst() {
        T value = null;
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        } else {
            value = head.item;
            head = head.next;
        }
        size--;
        modCount++;
        return value;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            ForwardLinked.Node<T> iteratorIndex = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("hasNext колекция была изменена");
                }
                return iteratorIndex != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("hasNext колекция была изменена");
                }
                T value = (T) iteratorIndex.item;
                iteratorIndex = iteratorIndex.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private ForwardLinked.Node<T> next;

        Node(T element, ForwardLinked.Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}