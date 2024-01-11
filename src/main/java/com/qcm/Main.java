package main.java.com.qcm;


import main.java.com.qcm.model.Student;
import main.java.com.qcm.util.DatabaseConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        DatabaseConnection.importDataBase();
        DatabaseConnection.insertData();
        Index.getInstance();
    }

}
