package main.java.com.qcm.model;

import main.java.com.qcm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static PreparedStatement query(String query, String[] binding  ) throws SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        int x = 1;
        for (int i = 0; i < binding.length ; i++) {
            preparedStatement.setString(x, binding[i]);
            ++x;
        }
        return preparedStatement;
    }

}
