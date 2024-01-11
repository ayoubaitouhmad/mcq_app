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

    /***
     * Find Student by full name
     * @param full_name
     * @return
     * @throws SQLException
     */
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

    /***
     * Find user by national ID
     * @param cin
     * @return
     * @throws SQLException
     */
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

    /***
     * create new Student
     * @return
     * @throws SQLException
     */
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


    /***
     * Get First Student
     * @return
     * @throws SQLException
     */
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


    /***
     * Get user quizzes depend on user major
     * @return
     * @throws SQLException
     */
    public  List<Quiz> quizzes() throws SQLException {
        List<Quiz> items = new ArrayList<>();
        for (Quiz quiz:  Quiz.all()) {
            if(Objects.equals(quiz.targetCategory(), this.getMajor())){
                items.add(quiz);
            }
        }
        return items;
    }


    public int quizScore(int quizId) throws SQLException {
        String insertQuery = "select student_attempts.score from student_attempts join quizzes q on q.id = student_attempts.quiz join students s on s.id = student_attempts.student where s.id = ? and q.id=? limit 1;";
        String[] binding = {
                String.valueOf(this.id),
                String.valueOf(quizId),
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

        if (resultSet.next()) {
            return  resultSet.getInt("score");
        }
       return 0;
    }


}
