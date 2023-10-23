package ru.job4j.io;

import ru.job4j.map.Map;

import java.io.*;
import java.util.HashMap;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)),true);
            String start = null;
            String end;
            String line;
            while ((line = in.readLine()) != null) {
                if ((line.startsWith("400") || line.startsWith("500")) && start == null) {
                    start = line.split(" ")[1];
                } else if ((line.startsWith("200") || line.startsWith("300")) && start != null) {
                    end = line.split(" ")[1];
                    out.println("Время отключения " + start + " время включения " + end);
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}