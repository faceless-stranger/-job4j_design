package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Интерфейс генератора */
public interface Generate  {

    /* Это абстрактный метод без реализации, который должен быть реализован всеми классами, которые реализуют этот интерфейс. */
    void generate();

    /* default: Метод по умолчанию имеет реализацию. Это означает, что классы, реализующие этот интерфейс, могут использовать этот метод без необходимости переопределять его, если не нужно изменять его поведение.
    *  сам метод считывает файл по определенному пути и сохраняет результат в List*/
    default List<String> read(String path) throws IOException {
        List<String> text = new ArrayList<>();
        Files.lines(Paths.get(path))
                .forEach(text::add);
        return text;
    }
}