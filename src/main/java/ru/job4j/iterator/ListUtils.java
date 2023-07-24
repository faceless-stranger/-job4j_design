package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    /**
     * Метод должен вставляет до индекса
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод должен вставляет после индекса
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        list.add(index + 1, value);
    }

    /**
     * Метод удаляет все элементы, которые удовлетворяют предикату
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
               iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из списка те элементы, которые есть в elements
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        removeIf(list, elements::contains);
    }

}