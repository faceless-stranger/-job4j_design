package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParent = findBy(parent);
        Optional<Node<E>> nodeChild = findBy(child);
        if (nodeParent.isPresent()
                && nodeParent.get().value == parent
                && nodeChild.isEmpty()) {
            nodeParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        /** Он создает пустой объект типа Optional, который не содержит значения. */
        Optional<Node<E>> rsl = Optional.empty();
         /** создает очередь с именем data, которая может содержать элементы типа Node<E> */
        Queue<Node<E>> data = new LinkedList<>();
         /**Пытается вставить элемент в конец очереди и возвращает true, если вставка прошла успешно, и false, если вставка не удалась */
        data.offer(this.root);
         /**Проверяет что data не пустая */
        while (!data.isEmpty()) {
         /** удаляет элемент из начала очереди и присвавает el/ если очеред пуста вернет нулл */
            Node<E> el = data.poll();
            /** Проверяет равенл ли элемент нашему значению */
            if (el.value.equals(value)) {
         /** присваеиваем рсл значение el и если оно null бросает exeption */
                rsl = Optional.of(el);
                break;
            }
         /** ?? */
            data.addAll(el.children);
        }
        return rsl;
    }
}