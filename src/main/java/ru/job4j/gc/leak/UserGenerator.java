package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* Генератор пользователей. При создании UserGenerator мы заполним списки с именами, фамилиями, отчествами, а при вызове generate зачищаем список users и
будем брать случайные значения из списков. Таким образом каждый раз создаем 1000 пользователей. */
public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final Integer NEW_USERS = 1000;

    public  List<String> names;
    public  List<String> surnames;
    public  List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    /* Используется для генерации users на случайных значениях surnames names patrons*/
    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < NEW_USERS; i++) {
            users.add(new User(
                    surnames.get(random.nextInt(surnames.size())) + SEPARATOR
                            + names.get(random.nextInt(names.size())) + SEPARATOR
                            + patrons.get(random.nextInt(patrons.size()))));
        }
    }

    /* Метод readAll() предназначен для чтения данных из нескольких файлов и их загрузки в соответствующие поля объекта.*/
    private void readAll() {
        try {
            /* Тут мы выгружаем данные из файлов в поля класса.
            * Для реализации используем метод реализованный в интерфейсе  */
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public List<User> getUsers() {
        return users;
    }
}