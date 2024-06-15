package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    /* Это поле представляет соединение с базой данных через JDBC. Объект Connection установливает соединеник с конкретной базой данных и выполненяет SQL-запросы. */
    private Connection connection;

    /* Это поле представляет объект Properties, который содержит конфигурационные параметры (URL, пароль итд), необходимые для подключения к базе данных. */
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    /* Создаем соеденение с БД */
    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("hibernate.connection.url");
            String login = properties.getProperty("hibernate.connection.username");
            String password = properties.getProperty("hibernate.connection.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize connection", e);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
    }

    /* Вынес execute  в отдельный метод для избавления дублирования кода. Исп-ся для соеденнения с SQL */
    private void execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute SQL: " + sql, e);
        }
    }

    /* создает пустую таблицу без столбцов с указанным именем */
    public void createTable(String tableName) {
        String sql = String.format("CREATE TABLE %s ();", tableName);
        execute(sql);
    }

    /*  удаляет таблицу по указанному имени */
    public void dropTable(String tableName) {
        String sql = String.format("DROP TABLE %s;", tableName);
        execute(sql);
    }

    /* добавляет столбец в таблицу */
    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        execute(sql);
    }

    /* удаляет столбец из таблицы */
    public void dropColumn(String tableName, String columnName) {
        String sql = String.format("ALTER TABLE %s DROP COLUMN %S;", tableName, columnName);
        execute(sql);
    }

    /* переименовывает столбец */
    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName);
        execute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}