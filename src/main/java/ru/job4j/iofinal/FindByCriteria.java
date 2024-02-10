package ru.job4j.iofinal;

import ru.job4j.assertj.io.ArgsName;
import ru.job4j.assertj.io.Search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class FindByCriteria {
    public static void main(String[] args) throws IOException {
        findByCriteria(args);
    }

    public static void findByCriteria(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        ValidationSeartch.validation(argsName);
        List<Path> path = parameterSearch(argsName);
        recordingFiles(path, argsName);
    }

    private static List<Path> parameterSearch(ArgsName argsName) throws IOException {
        Path root = Path.of(argsName.get("d"));
        List<Path> result = null;
        if ("name".equals(argsName.get("t"))) {
            result = Search.search(root, p -> p.toFile().getName().endsWith(argsName.get("n")));
        } else if ("regex".equals(argsName.get("t"))) {
            Pattern pattern = Pattern.compile(argsName.get("n"));
            result = Search.search(root, p -> pattern.matcher(p.toFile().getName()).find());
        } else if ("mask".equals(argsName.get("t"))) {
            StringBuilder sb = new StringBuilder();
            char[] value = argsName.get("n").toCharArray();
            for (char rsl : value) {
                char chr = rsl;
                if (rsl == '*') {
                    sb.append("." + rsl);
                } else if ('?' == rsl) {
                    sb.append(rsl + ".");
                } else if ('.' == rsl) {
                    sb.append("\\.");
                } else {
                    sb.append(rsl);
                }
            }
            Pattern pattern = Pattern.compile(sb.toString());
            result = Search.search(root, p -> pattern.matcher(p.toFile().getName()).find());
        }
        return result;
    }

    private static void recordingFiles(List<Path> paths, ArgsName argsName) {
        Path fullDir = Path.of(argsName.get("d"), argsName.get("o"));
        try (BufferedWriter buf = new BufferedWriter(new FileWriter(fullDir.toFile()))) {
            for (Path value : paths) {
                buf.write(value.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

