package ru.job4j.io;

import java.io.*;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter res = new PrintWriter(new BufferedWriter(new FileWriter(out)))) {
            data.forEach(e -> res.println(e));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");

    }
}