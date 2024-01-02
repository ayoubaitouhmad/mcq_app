package main.java.com.qcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends Model {

    private static final String table = "students";

    private int id;
    private String full_name;
    private String major;
    private String grade;


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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student(int id, String full_name, String major, String grade) {
        this.id = id;
        this.full_name = full_name;
        this.major = major;
        this.grade = grade;

    }

    public static Student findByName(String full_name) throws SQLException {
        Connection connection = connection();


        String query = "SELECT * FROM "+Student.table+" WHERE full_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, full_name.toLowerCase());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("major"),
                    resultSet.getString("grade")
            );
        }
        return null;
    }





}
