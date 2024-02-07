package ru.job4j.assertj.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        /* TODO добавить необходимые проверки. */
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO разобрать аргументы на значения. */
        for (String arg : args) {
            String[] st = arg.split("=", 2);
            values.put(st[0].substring(1), st[1]);
        }
    }

    public static ArgsName of(String[] args) {
        /* TODO добавьте необходимые проверки. */
        validate(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void validate(String[] args) {
        /* Проверка что  массив не пустой*/
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }

        for (String arg : args) {
            /* Проверка на наличие разделителя */
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", arg));
            }
            /* Проверка на наличие - */
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", arg));
            }
            /* Проверка на наличия key/value */
            String[] st = arg.split("=", 2);
            String key = st[0].substring(1);
            String value = st[1];
            if (key.isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a key", arg));
            }
            if (value.isEmpty()) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain a value", arg));
            }
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}