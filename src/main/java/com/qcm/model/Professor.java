package main.java.com.qcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Professor extends  Model {

    private static final String table = "professors";

    private int id;
    private String full_name;
    private String cin;
    private String speciality;



    public Professor() {}

    public Professor(int id, String full_name, String speciality) {
        this.id = id;
        this.full_name = full_name;
        this.speciality = speciality;
    }
    public Professor(int id, String full_name, String speciality , String cin) {
        this.id = id;
        this.full_name = full_name;
        this.speciality = speciality;
        this.cin = cin;
    }


    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
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

    public static Professor find(int id) throws SQLException {
        Connection connection = connection();
        String query = "SELECT * FROM "+Professor.table+" WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
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




    public static Professor findByCin(String  cin) throws SQLException {
        Connection connection = connection();
        String query = "SELECT * FROM "+Professor.table+" WHERE cin = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, cin);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new Professor(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("speciality"),
                    resultSet.getString("cin")
            );
        }
        return null;
    }


    /***
     * create new Professor
     * @return
     * @throws SQLException
     */
    public Professor save() throws SQLException {
        String insertQuery = "INSERT INTO " + Professor.table + " (full_name,speciality,cin) VALUES (?,?,?)";
        String[] binding = {
                this.getFull_name(),
                this.getSpeciality(),
                this.getCin()
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeUpdate();
        return  this;
    }

    /***
     * Get First Student
     * @return
     * @throws SQLException
     */
    public static Professor first() throws SQLException {
        String insertQuery = "select * from  " + Professor.table + " limit  1";
        String[] binding = {};
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();
        if (resultSet.next()) {
            return new Professor(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("speciality"),
                    resultSet.getString("cin")
            );
        }
        return  null;
    }


}
