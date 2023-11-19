package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void validation(ArgsName value) {
        /* Проверить, что архивируемая директория существует. */
        if (!Files.isDirectory(Path.of(value.get("d")))) {
            throw new IllegalArgumentException("The specified path is not a directory");
        }
        /* расширение передано в правильном формате */
        if (!value.get("e").startsWith(".") || value.get("e").length() < 2) {
            throw new IllegalArgumentException("Format is incorrect");
        }
        /* имя архива имеет расширение ".zip" */
        if (!value.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Archive name does not match");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        if (args.length != 3) {
            throw new IllegalArgumentException("Not all search parameters are specified");
        }
        ArgsName date = ArgsName.of(args);
        validation(date);
        zip.packFiles(Search.search(Paths.get(date.get("d")), p -> !p.toFile().getName().endsWith(date.get("e"))), new File(date.get("o")));
    }
}