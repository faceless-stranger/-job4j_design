package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validation(String[] args) {
        if (args.length == 0 || args.length > 2) {
            throw new IllegalArgumentException("Parameters are set incorrectly.");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(String.format("Not directory"));
        }
        if (!args[1].contains(".txt")) {
            throw new IllegalArgumentException(String.format("Incorrect file extension"));
        }

    }
}




