package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        User vlad = new User("vlad", 0, calendar);
        int hashCodeVlad = vlad.hashCode();
        int hashVlad = hashCodeVlad ^ (hashCodeVlad >>> 16);
        int bucketVlad = hashVlad & 15;

        User dima = new User("vlad", 0, calendar);
        int hashCodeDima = dima.hashCode();
        int hashDima = hashCodeDima ^ (hashCodeVlad >>> 16);
        int bucketDima = hashDima & 15;

        Map<User, Object> map = new HashMap<>();
        map.put(vlad, new Object());
        map.put(dima, new Object());
        System.out.printf("Влад хешкод: %s, хеш: %s, бакет: %s", hashCodeVlad, hashVlad, bucketVlad);
        System.out.println();
        System.out.printf("Дима хешкод: %s, хеш: %s, бакет: %s", hashCodeDima, hashDima, bucketDima);

    }
}
