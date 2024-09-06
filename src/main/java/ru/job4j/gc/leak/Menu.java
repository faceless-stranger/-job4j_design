package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    public static final Integer ADD_POST = 1;
    public static final Integer ADD_MANY_POST = 2;
    public static final Integer SHOW_ALL_POSTS = 3;
    public static final Integer DELETE_POST = 4;

    public static final String SELECT = "Выберите меню";
    public static final String COUNT = "Выберите количество создаваемых постов";
    public static final String TEXT_OF_POST = "Введите текст";
    public static final String EXIT = "Конец работы";

    public static final String ID_FOR_DELETE = "Удалено";

    public static final String MENU = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        /* Создаем генератор случайных чисел */
        Random random = new Random();
        /* Создаем генератор пользователей, и через конструктор выгружаем данные из файлов*/
        UserGenerator userGenerator = new UserGenerator(random);
        /* Предназначен для генерации списка комментариев с использованием случайных фраз и случайных пользователей */
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                createPost(commentGenerator, userGenerator, postStore, text, 1);
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0) {
                    createPost(commentGenerator, userGenerator, postStore, text, value);
                } else {
                    System.out.println("Количество постов должно быть положительным числом.");
                }
                createPost(commentGenerator, userGenerator, postStore, text, value);
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(ID_FOR_DELETE);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text, int value) {
        /* Генерируем 1000 случайных пользователей */
        userGenerator.generate();
        /* Генерируем 50 случайных комментариев привязывая их к случайным пользователям */
        commentGenerator.generate();
        for (int i = 0; i < value; i++) {
            /* Создаем пост на базе нашего текста и реализуем там наши комментарии*/
            postStore.add(new Post(text,  commentGenerator.getComments()));
        }

    }
}