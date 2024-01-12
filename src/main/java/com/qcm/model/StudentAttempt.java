package main.java.com.qcm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentAttempt extends Model {

    private static final String table = "student_attempts";
    private int id;
    private int score;
    private String createdAt;
    private Student student;
    private Quiz quiz;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public StudentAttempt(int score, Student student, Quiz quiz) {
        this.score = score;
        this.student = student;
        this.quiz = quiz;

    }

    public StudentAttempt(int id, int score, Student student, Quiz quiz) {
        this.id = id;
        this.score = score;
        this.student = student;
        this.quiz = quiz;

    }

    public StudentAttempt(int id, int score, Student student, Quiz quiz, String createdAt) {
        this.id = id;
        this.score = score;
        this.student = student;
        this.createdAt = createdAt;
        this.quiz = quiz;

    }


    public void save() throws SQLException {
        String insertQuery = "INSERT INTO " + StudentAttempt.table + " (student,quiz,score) VALUES (?,?,?)";
        String[] binding = {
                String.valueOf(this.getStudent().getId()),
                String.valueOf(this.getQuiz().getId()),
                String.valueOf(this.getScore()),
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeUpdate();
    }

    public static List<StudentAttempt> all() throws SQLException {
        String insertQuery = "select * from  " + StudentAttempt.table;
        String[] binding = {};
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

        List<StudentAttempt> items = new ArrayList<>();

        while (resultSet.next()) {
            items.add(new StudentAttempt(
                    resultSet.getInt("id"),
                    resultSet.getInt("score"),
                    Student.find(resultSet.getInt("student")),
                    Quiz.find(resultSet.getInt("quiz")),
                    resultSet.getString("created_at")

            ));
        }
        return items;

    }


    public static StudentAttempt find(int studentAttemptId) throws SQLException {
        for (StudentAttempt studentAttempt : all()) {
            if (studentAttempt.getId() == studentAttemptId) {
                return studentAttempt;
            }
        }
        return null;
    }


    /***
     * Check if student passes the quiz
     * @return
     * @throws SQLException
     */
    public String result() throws SQLException {
        if (score >= quiz.getQuestions().size()/2) {
            return "admis";
        }
        return "Rachete";
    }
}
