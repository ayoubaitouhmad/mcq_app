package main.java.com.qcm.util;

import main.java.com.qcm.model.Professor;
import main.java.com.qcm.model.Quiz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE = "db_mcq";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL + DATABASE, USER, PASSWORD);
    }


    public static void importDataBase() throws SQLException {

        Connection connection = DriverManager.getConnection(DatabaseConnection.URL, DatabaseConnection.USER, DatabaseConnection.PASSWORD);
        try (BufferedReader reader = new BufferedReader(new FileReader("lib/db.sql"))) {
            String line;
            StringBuilder sqlStatements = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sqlStatements.append(line);
                if (line.trim().endsWith(";")) {
                    try (Statement statement = connection.createStatement()) {
                        statement.execute(sqlStatements.toString());
                    }
                    sqlStatements.setLength(0); // Clear the StringBuilder for the next statement
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertData() throws SQLException {
        Connection connection = getConnection();
        if (Quiz.all().isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader("lib/inserts.sql"))) {
                String line;
                StringBuilder sqlStatements = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    sqlStatements.append(line);
                    if (line.trim().endsWith(";")) {
                        try (Statement statement = connection.createStatement()) {
                            statement.execute(sqlStatements.toString());
                        }
                        sqlStatements.setLength(0); // Clear the StringBuilder for the next statement
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


}
