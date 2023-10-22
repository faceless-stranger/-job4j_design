package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    /**
     * должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.
     */
    public List<String> filter() {
        ArrayList<String> res = new ArrayList<>();
        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            buff.lines().forEach(e -> {
                if (e.contains(" 404 ")) {
                    res.add(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);

    }
}