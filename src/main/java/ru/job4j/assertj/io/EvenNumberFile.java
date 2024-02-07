package ru.job4j.assertj.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder even = new StringBuilder();
            int number;
            while ((number = in.read()) != -1) {
                even.append((char) number);
            }
           String[] result = even.toString().split(System.lineSeparator());
            for (String res : result) {
                int value = Integer.parseInt(res);
                boolean rsl = value % 2 == 0;
                System.out.println(value + " являеться " + rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}