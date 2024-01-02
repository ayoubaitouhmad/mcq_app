package main.java.com.qcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends  Model {

    private static final String table = "professors";

    private int id;
    private String full_name;
    private String speciality;





    public Professor(int id, String full_name, String speciality) {
        this.id = id;
        this.full_name = full_name;
        this.speciality = speciality;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public static Professor findByName(String full_name) throws SQLException {
        Connection connection = connection();
        String query = "SELECT * FROM "+Professor.table+" WHERE full_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, full_name.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Professor(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("speciality")
            );
        }
        return null;
    }


}
