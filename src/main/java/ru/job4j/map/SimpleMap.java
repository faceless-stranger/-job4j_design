package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    /**
     * количество бакетов в массиве
     */
    private int capacity = 8;
    /**
     * количество пар в массиве
     */
    private int count = 0;
    /**
     * счетчик изменений для fail-fast
     */
    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] cloud = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (nonNull(entry)) {
                cloud[getIndex(entry.key)] = entry;
            }
        }
        table = cloud;
    }

    @Override
    public V get(K key) {
        MapEntry<K, V> value = table[getIndex(key)];
        return nonNull(value) && checkNullObject(value.key, key)
                ? value.value
                : null;
    }

    /**
     * используется для сравнения ключей, он проверяет равны ли хэши ключей и равны ли сами ключи.
     */
    private boolean checkNullObject(K value, K key) {
        return Objects.hashCode(value) == Objects.hashCode(key)
                && Objects.equals(value, key);
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        MapEntry<K, V> entry = table[index];
        boolean rsl = nonNull(entry) && checkNullObject(entry.key, key);
        if (rsl) {
            table[index] = null;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int indexIterator = 0;
            int countIterator = count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("hasNext колекция была изменена");
                }
                while (countIterator > indexIterator && table[indexIterator] == null) {
                    indexIterator++;
                    countIterator++;
                }
                return countIterator > indexIterator;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("next элементов больше нет");
                }
                K value = table[indexIterator].key;
                indexIterator++;
                return value;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}