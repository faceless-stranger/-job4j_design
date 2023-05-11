package ru.job4j.assertj;

import java.util.*;
import java.util.stream.Collectors;

public class NameLoad {
    private final Map<String, String> values = new HashMap<>();

    public void parse(String... names) {
        if (names.length == 0) {                                                 /** Проверка что имя не пустое */
            throw new IllegalArgumentException("Names array is empty");          /** если имя пустое выкидывает исключение */
        }
        values.putAll(Arrays.stream(names)                                       /** putAll добавляет новую мапу из name stream */
                .map(String::trim)                                               /** удаление пробелов */
                .filter(this::validate)                                          /** фильтрация по методу validate */
                .map(s -> s.split("=", 2))                            /** Разделение строк на подстроки по символу = c лимитом в 2 */
                .collect(Collectors.toMap(
                        e -> e[0],                                               /** Слияния в map и установка ключа */
                        e -> e[1],                                               /** значение */
                        (first, second) -> String.format("%s+%s", first, second) /** избавление от дубликатов */
                )));
    }

    private boolean validate(String name) {
        if (!name.contains("=")) {                                               /** проверяет содержит ли имя пробел */
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", name));
        }
        if (name.startsWith("=")) {                                              /** Начинаеться ли имя на = */
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a key", name));
        }
        if (name.indexOf("=") == name.length() - 1) {                             /**проверяем заканчиваеться ли строка последним символом = */
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", name));
        }
        return true;
    }

    public Map<String, String> getMap() {
        if (values.isEmpty()) {
            throw new IllegalStateException("collection contains no data");
        }
        return values;
    }
}