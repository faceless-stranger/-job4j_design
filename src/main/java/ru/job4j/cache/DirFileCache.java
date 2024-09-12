package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        Map<String, String> result = new HashMap<>();
        StringBuilder text = new StringBuilder();
        try {
            Path path = Paths.get(cachingDir);
            Files.lines(path).forEach(line -> text.append(line).append("\n"));
            result.put(key, text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(text);
    }
}