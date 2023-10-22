package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * должен считать все ключи в карту values
     */
    public void load() {

        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> {
                if (line.startsWith("/") || line.isEmpty()) {
                    return;
                }
                if (line.startsWith("=") || line.endsWith("=") || !line.contains("=") || line.equals("=")) {
                    throw new IllegalArgumentException("Синтаксис нарушен");
                }

                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    values.put(parts[0], parts[1]);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}