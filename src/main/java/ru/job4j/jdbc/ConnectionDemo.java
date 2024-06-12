package ru.job4j.jdbc;

import ru.job4j.assertj.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
         /* Создаем экземпляр Config с путем к файлу конфигурации */
        Config config = new Config("C:\\projects\\job4j_design\\data\\app.properties");
        /* Загружаем конфигурацию из файла */
        config.load();
        /* Регестрируем драйвер в системе, где данные получаем по ключу. Операцию повторяем и для других значений */
       Class.forName(config.value("hibernate.connection.driver_class"));
        String url = config.value("hibernate.connection.url");
        String login = config.value("hibernate.connection.username");
        String password = config.value("hibernate.connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}