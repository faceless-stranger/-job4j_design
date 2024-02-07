package ru.job4j.assertj.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        List<Integer> columnList = new ArrayList<>();
        String delimiter = argsName.get("delimiter");
        List<String[]> fileQuality = csvReader(argsName.get("path"), delimiter);
        String[] columnsCSV = fileQuality.get(0);
        String[] dateFilter = argsName.get("filter").split(",");
        /* Нахожу столбцы в масиве по нужным параметрам */
        for (String filter : dateFilter) {
            for (int i = 0; i < columnsCSV.length; i++) {
                if (columnsCSV[i].equals(filter)) {
                    columnList.add(i);
                    break;
                }
            }
        }
        /*Теперь зная в каких столбцах нужные мне аргументы записываю данные в итоговый List*/
        List<String> result = new ArrayList<>();
        for (String[] row : fileQuality) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i : columnList) {
                stringBuilder.append(row[i]).append(delimiter);
            }
            result.add(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
        }
        csvWriter(result, argsName.get("out"));
    }

    private static void csvWriter(List<String> result, String out) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            for (String writeString : result) {
                writer.println(writeString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> csvReader(String patch, String delimiter) {
        List<String[]> csvReader = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(patch)))) {
            while (scanner.hasNextLine()) {
                csvReader.add(scanner.nextLine().split(delimiter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvReader;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Не соблюдено количество аргукментов");
        }
        ArgsName argsName = ArgsName.of(args);
        validation(argsName, args);
        handle(argsName);
    }

    private static void validation(ArgsName argsName, String[] args) {
        if (Files.isDirectory(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException("Проверьте директорию в которой находиться файл csv");
        }
        if (Files.isDirectory(Path.of(argsName.get("out")))) {
            throw new IllegalArgumentException("Проверьте директорию в которой находиться файл csv");
        }
        if (!Files.isRegularFile(Path.of(argsName.get("path")))) {
            throw new IllegalArgumentException("Файл " + argsName.get("path") + " не является регулярным файлом (CSV)");
        }
    }
}
