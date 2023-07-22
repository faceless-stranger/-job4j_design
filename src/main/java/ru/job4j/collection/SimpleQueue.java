package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс реализует работу двух стеков.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int index = 0;

    /**
     * должен возвращать первое значение и удалять его из коллекции
     */
    public T poll() {
        if (index == 0){
            throw new NoSuchElementException("Queue is empty");
        }
        index--;
        return in.pop();
    }

    /**
     * помещает значение в конец.
     */
    public void push(T value) {
        if (index == 0) {
            in.push(value);
        } else if (index >= 1) {
            for (int i = 0; i < index; i++) {
                out.push(in.pop());
            }
            in.push(value);
            for (int i = 0; i < index; i++) {
                in.push(out.pop());
            }
        }
        index++;
    }
}

