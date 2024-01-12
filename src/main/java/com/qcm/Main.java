package main.java.com.qcm;


import main.java.com.qcm.frames.professor.Home;
import main.java.com.qcm.frames.student.StartQuiz;
import main.java.com.qcm.model.Professor;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.ProfessorPanel;
import main.java.com.qcm.util.DatabaseConnection;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

//        DatabaseConnection.importDataBase();
//        DatabaseConnection.insertData();
//        Index.getInstance();
//     new Home(Professor.first());
     new main.java.com.qcm.frames.student.Home(Student.first());
//        new StartQuiz(Quiz.all().getFirst() , Student.first());
    }

}
