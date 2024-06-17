package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().forEach(line -> {
                String[] value = line.split(";");
                if (value.length != 2 || value[0].isEmpty() || value[1].isEmpty()) {
                    throw new IllegalArgumentException("Incorrect data");
                }
                users.add(new User(value[0], value[1]));
            });
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        /* По предварительно прочитанной информации мы используем .class что-бы получаете объект типа Class, который
        представляет собой метаданные класса и не требует создания экземпляра этого класса. Затем, используя .getClassLoader(),
        что являеться загрузчиком классов, для использования его методов. Метод getResourceAsStream() исп-ся для получения
        InputStream, который позволяет читать данные из файла example.txt. Без этого метода мы не смогли бы получить доступ к содержимому файла, так как он находится внутри JAR-файла */
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "C:\\projects\\job4j_design\\data\\dump.txt");
        dataBase.save(dataBase.load());
    }
}