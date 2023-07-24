package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс реализует работу двух стеков.
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int indexIn = 0;
    private int indexOut = 0;

    /**
     * должен возвращать первое значение и удалять его из коллекции
     */
    public T poll() {
        if (indexOut == 0 && indexIn == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (indexOut == 0) {
            while (indexIn != 0) {
                out.push(in.pop());
                indexIn--;
                indexOut++;
            }
        }
        indexOut--;
        return out.pop();
    }

    /**
     * помещает значение в конец.
     */
    public void push(T value) {
        in.push(value);
        indexIn++;
    }
}

