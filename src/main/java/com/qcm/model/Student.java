package main.java.com.qcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends Model {

    private static final String table = "students";

    private int id;
    private String full_name;
    private String major;
    private String grade;


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    private String cin;


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

    public Student() {
    }

    public Student(int id, String full_name, String major, String grade) {
        this.id = id;
        this.full_name = full_name;
        this.major = major;
        this.grade = grade;
    }

    public static Student findByName(String full_name) throws SQLException {

        String query = "SELECT * FROM " + Student.table + " WHERE full_name = ?";
        String[] binding = {full_name};
        PreparedStatement stm = query(query, binding);
        ResultSet resultSet = stm.executeQuery();
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

    public static Student findByCin(String cin) throws SQLException {

        String query = "SELECT * FROM " + Student.table + " WHERE cin = ?";
        String[] binding = {cin};
        PreparedStatement stm = query(query, binding);
        ResultSet resultSet = stm.executeQuery();

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

    public Student save() throws SQLException {
        String insertQuery = "INSERT INTO " + Student.table + " (full_name,major,grade,cin) VALUES (?,?,?,?)";
        String[] binding = {
                this.getFull_name(),
                this.getMajor(),
                this.getGrade(),
                this.getCin(),
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeUpdate();
        return this;
    }


    public static Student first() throws SQLException {
        String insertQuery = "select * from  " + Student.table + " limit  1";
        String[] binding = {};
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            return new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("major"),
                    resultSet.getString("grade")
            );
        }
        return new Student();
    }


    public  List<Quiz> quizzes() throws SQLException {
        List<Quiz> items = new ArrayList<>();
        for (Quiz quiz:  Quiz.all()) {
            if(Objects.equals(quiz.targetCategory(), this.getMajor())){
                items.add(quiz);
            }
        }
        return items;
    }
}
