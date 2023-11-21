package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /*- run(), содержит логику чата */
    public void run() {
        List<String> log = new ArrayList<>();
        Scanner user = new Scanner(System.in);
        List<String> answersDatabase = readPhrases();
        validation(answersDatabase);
        System.out.println("Задайте шару предсказателю свой вопрос");
        String question;
        String check = "продолжить";
        do {
            question = user.nextLine();
            switch (question) {
                case STOP:
                    check = STOP;
                    System.out.println("Предсказание приостановлены");
                    break;
                case CONTINUE:
                    check = CONTINUE;
                    System.out.println("Предсказание продолжаются");
                    break;
                default:
                    if (CONTINUE.equals(check) && !OUT.equals(question)) {
                        String answer = answersDatabase.get(new Random().nextInt(answersDatabase.size()));
                        log.add(question + "-" + answer);
                        System.out.println(answer);
                    }
                    break;
            }
        } while (!question.equals(OUT));
        saveLog(log);
    }

    /*- readPhrases(), читает фразы из файла */
    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(botAnswers))) {
            bf.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    /* - saveLog(), сохраняет лог чата в файл */
    private void saveLog(List<String> log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (String line : log) {
                bw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validation(List<String> answersDatabase) {
        if (answersDatabase == null) {
            throw new IllegalArgumentException("База ответов пуста");
        }
        if (Files.isDirectory(Path.of(botAnswers))) {
            throw new IllegalArgumentException("Проверьте директорию ответов бота");
        }
        if (Files.isDirectory(Path.of(path))) {
            throw new IllegalArgumentException("Проверьте директорию логов пользователя");
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\Console Bot\\Save_Dialogue.txt", "C:\\projects\\job4j_design\\data\\Console Bot\\bot_answer.txt");
        cc.run();
    }
}