package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    /* Хранит посты. Ключом является уникальный идентификатор, а значением — объект класса Post. */
    private Map<Integer, Post> posts = new HashMap<>();

    /* Что-то про потокабезопасность рассмотреть потом */
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public Post add(Post post) {
        Integer id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public  Collection<Post> getPosts() {
        return posts.values();
    }
}