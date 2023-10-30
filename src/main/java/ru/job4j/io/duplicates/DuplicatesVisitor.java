package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty value = new FileProperty(Files.size(file), file.getFileName().toString());
            if (allFiles.containsKey((value))) {
                allFiles.get(value).add(file);
            } else {
                allFiles.put(value, new ArrayList<Path>());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void rsl() {
        for (Map.Entry<FileProperty, List<Path>> rsl : allFiles.entrySet()) {
            if (rsl.getValue() != null) {
                for (Path dir : rsl.getValue()) {
                    System.out.println("Name " + rsl.getKey().getName()
                            + " Size " + rsl.getKey().getSize() + " Директория " + dir);
                }
            }
        }
    }
}
