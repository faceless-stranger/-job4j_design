package ru.job4j.assertj.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("data/resultMultiple.txt")) {
            out.write("1*1=1".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*2=2".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*3=3".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*4=4".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*5=5".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*6=6".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*7=7".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1*8=7".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}