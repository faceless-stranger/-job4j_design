package ru.job4j.info;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetinfo {
    public static void main(String[] args) {
        /* Подключаемся к базе данных */
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "user", "password");
             /* Создаем объект Statement для выполнения SQL-запросов */
             Statement statement = connection.createStatement()) {

            /* Выполняем SQL-запрос и получаем результат в виде ResultSet */
            ResultSet resultSet = statement.executeQuery("SELECT id, name FROM mytable");

            /* Перебираем строки результата */
            while (resultSet.next()) {
                /* Извлекаем данные из текущей строки */
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                /* Выводим данные на экран */
                System.out.println("ID: " + id + ", Name: " + name);
            }

            /* Закрываем ResultSet */
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
