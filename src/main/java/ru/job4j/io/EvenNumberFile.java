package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            StringBuilder even = new StringBuilder();
            int number;
            while ((number = in.read()) != -1) {
                even.append((char) number);
                // rsl = number % 2 == 0;
                System.out.println(even);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}