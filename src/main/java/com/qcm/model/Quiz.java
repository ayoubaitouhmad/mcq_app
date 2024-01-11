package main.java.com.qcm.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends Model {

    private static final String table = "quizzes";
    private int id;
    private Professor professor;
    private String title;

    private boolean hasMultiChoices;
    private String targetCategory;




    public String getName() {
        return title;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasMultiChoices() {
        return hasMultiChoices;
    }

    public void setHasMultiChoices(boolean hasMultiChoices) {
        this.hasMultiChoices = hasMultiChoices;
    }

    public String targetCategory() {
        return targetCategory;
    }

    public void String(String targetCategory) {
        this.targetCategory = targetCategory;
    }

    public Quiz(int id, Professor professor, String title, boolean hasMultiChoices, String targetCategory) {
        this.id = id;
        this.professor = professor;
        this.title = title;
        this.hasMultiChoices = hasMultiChoices;
        this.targetCategory = targetCategory;
    }



    public static List<Quiz> all() throws SQLException {
        String insertQuery = "select * from  " + Quiz.table ;
        String[] binding = {};
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

         List<Quiz> items = new ArrayList<>();

        while  (resultSet.next()) {
           items.add(new Quiz(
                   resultSet.getInt("id"),
                   Professor.find( resultSet.getInt("professor")),
                   resultSet.getString("title"),
                   resultSet.getBoolean("hasMultiChoices"),
                   resultSet.getString("targetCategory")
           ));
        }
        return  items;

    }


    public List<Question> getQuestions() throws SQLException {
        String insertQuery = questionsQuery() ;
        String[] binding = {
                String.valueOf(this.id)
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

         List<Question> items = new ArrayList<>();

        while  (resultSet.next()) {
           items.add(new Question(
                   resultSet.getInt("id"),
                   resultSet.getString("question")
           ));
        }
        return  items;

    }

    private static String questionsQuery(){
        return  "select  * from questions join  quiz_questions quiz_question on questions.id = quiz_question.question join db_mcq.quizzes quizze on quiz_question.quiz = quizze.id where  quizze.id=? ";
    }




}
