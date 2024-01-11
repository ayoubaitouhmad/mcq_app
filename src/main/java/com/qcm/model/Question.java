package main.java.com.qcm.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Question extends Model {

    private static final String table = "questions";


    private int id;
    private String question;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question() {
    }

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public List<Option> options() throws SQLException {
        String insertQuery = optionsQuery();
        String[] binding = {
                String.valueOf(this.id)
        };
        PreparedStatement stm = query(insertQuery, binding);
        stm.executeQuery();
        ResultSet resultSet = stm.executeQuery();

        List<Option> items = new ArrayList<>();

        while (resultSet.next()) {
            items.add(new Option(
                    resultSet.getInt("id"),
                    this,
                    resultSet.getString("option"),
                    resultSet.getBoolean("is_correct")
            ));
        }
        return items;

    }

    private static String optionsQuery() {
        return "select * from options join db_mcq.questions q on q.id = options.question_id where options.question_id = ?";
    }


    public Option correctOption() throws SQLException {
        for (Option option : options()) {
            if (option.isIs_correct()) {
                return option;
            }
        }
        return  null;
    }


}
