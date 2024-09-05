package ru.job4j.info;

import java.sql.ResultSet;

public class ExecuteInfo {
    /*
    public static void main(String[] args) {
        String sql = "UPDATE mytable SET name = 'John' WHERE id = 1";
        boolean isResultSet = statement.execute(sql);

        if (isResultSet) {
            // Запрос возвращает ResultSet (например, если это был SELECT запрос)
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } else {
            // Запрос не возвращает ResultSet (например, это был UPDATE, INSERT или DELETE)
            int updateCount = statement.getUpdateCount();
            if (updateCount > 0) {
                System.out.println("Rows affected: " + updateCount);
            } else {
                System.out.println("No rows were affected.");
            }
        }
    }     */
}
