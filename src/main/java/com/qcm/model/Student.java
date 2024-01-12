package main.java.com.qcm.model;

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


    public Quiz quiz;
    public StudentAttempt studentAttempt;

    public static Student find(int studentId) throws SQLException {
        for (Student student : all()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }


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

    public Student(int id, String full_name, String major, String grade, String cin) {
        this.id = id;
        this.full_name = full_name;
        this.major = major;
        this.grade = grade;
        this.cin = cin;
    }
    public Student(int id, String fullName, String major, String grade,String cin, Quiz quiz, StudentAttempt studentAttempt) {
        this.id = id;
        this.full_name = fullName;
        this.major = major;
        this.grade = grade;
        this.cin = cin;

        this.studentAttempt = studentAttempt;
        this.quiz = quiz;


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
                    resultSet.getString("grade"),
                    resultSet.getString("cin")
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
                    resultSet.getString("grade"),
                    resultSet.getString("cin")
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
                    resultSet.getString("grade"),
                    resultSet.getString("cin")
            );
        }
        return new Student();
    }


    /***
     * Get user quizzes depend on user major
     * @return
     * @throws SQLException
     */
    public  List<Quiz> getQuizzesByStudentMajor() throws SQLException {
        List<Quiz> items = new ArrayList<>();
        for (Quiz quiz:  Quiz.all()) {
            if(Objects.equals(quiz.targetCategory(), this.getMajor())){
                items.add(quiz);
            }
        }
        return items;
    }

    /***
     * Get user quizzes depend on user major
     * @return
     * @throws SQLException
     */
    public  List<Student> getStudentQuizzesHistorique() throws SQLException {
        String insertQuery = studentQuizzesHistoriqueQuery();
        String[] binding = {
                String.valueOf(this.id),
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();
        List<Student> items = new ArrayList<>();
        while (resultSet.next()) {
            items.add(new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("major"),
                    resultSet.getString("grade"),
                    resultSet.getString("cin"),
                    Quiz.find( resultSet.getInt("quiz_id")),
                    StudentAttempt.find(resultSet.getInt("student_attempt_id"))
            ));
        }

        return  items;

    }


    private static String studentQuizzesHistoriqueQuery(){
        return "select students.* , q.id  as quiz_id,sa.id as student_attempt_id from  students join db_mcq.student_attempts sa on students.id = sa.student join db_mcq.quizzes q on q.id = sa.quiz where students.id=?";
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

    public static List<Student> all() throws SQLException {
        String insertQuery = "select * from  " + Student.table;
        String[] binding = {};
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

        List<Student> items = new ArrayList<>();

        while (resultSet.next()) {
            items.add(  new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("full_name"),
                    resultSet.getString("major"),
                    resultSet.getString("grade"),
                    resultSet.getString("cin")
            ));
        }
        return items;
    }


    /***
     * Check if student has already passe the quiz
     * this will not work  if the student has the right to take same quiz multiple times
     * @param quizId
     * @return
     * @throws SQLException
     */
    public boolean hasStudentTakeQuiz(int quizId) throws SQLException {
        for (Student student : getStudentQuizzesHistorique()){
            if(student.quiz.getId() == quizId){
                return  true;
            }

        }
        return  false;
    }
}
