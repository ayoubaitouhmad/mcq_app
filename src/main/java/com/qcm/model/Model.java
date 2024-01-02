package main.java.com.qcm.model;

import main.java.com.qcm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Model {

    private static final String primaryKey = "id";






    public static Connection connection() {
        try {
            return DatabaseConnection.getConnection();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


}
