package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("data/even.txt.");
            int read;
            boolean rsl;
            while ((read = in.read()) != -1) {
                rsl = read % 2 == 0;
                System.out.println(rsl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}