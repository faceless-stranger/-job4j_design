package ru.job4j.io;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

class AnalysisTest {

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File server = tempDir.resolve("server.txt").toFile();
        try (PrintWriter out = new PrintWriter(server)) {
            out.print("200 10:56:01\n"
                    + "300 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
            System.out.println(out);
        }
        Analysis analysis = new Analysis();
        File target = tempDir.resolve("target.csv").toFile();
        analysis.unavailable(server.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("10:58:01;10:59:01;11:01:02;11:02:02;").hasToString(rsl.toString());
    }
}