package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    private final Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public final V get(K key) {
        /* Получаем SoftReference по ключу */
        SoftReference<V> softReference = cache.get(key);
        /* Проверяем, есть ли объект в кэше и не очищен ли он сбо рщиком мусора */
        V value = (softReference != null) ? (V) softReference.get() : null;
        if (value == null) {
            /* Если значение отсутствует или было удалено, загружаем его. Кладем новое значение в кэш.*/
            value = load(key);
            cache.put(key, new SoftReference<>(value));
        }
        return value;
    }

    protected abstract V load(K key);
}