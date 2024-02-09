package ru.job4j.iofinal;

import ru.job4j.assertj.io.ArgsName;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidationSeartch {
    public static void validation(ArgsName argsName) {
        Path directory = Path.of(argsName.get("d"));
        String fileName = argsName.get("n");
        String typeSearth = argsName.get("t");
        String result = argsName.get("o");
        if (!Files.exists(Path.of(argsName.get("d")))) {
            throw new IllegalArgumentException(String.format("Директория %s некоректная", directory));
        }
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(String.format("%s не являеться директорией", directory));
        }
        if (!directory.isAbsolute()) {
            throw new IllegalArgumentException(String.format("%s не являеться абсолютным путём", directory));
        }
        if (!"name".equals(typeSearth) && !"mask".equals(typeSearth) && !"regex".equals(typeSearth))  {
            throw new IllegalArgumentException(String.format("%s не соответсвует типу поиска. Доступные типы: mask, name, regex.", typeSearth));
        }
    }
}
