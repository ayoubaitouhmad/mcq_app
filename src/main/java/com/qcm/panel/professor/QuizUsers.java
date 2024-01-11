package main.java.com.qcm.panel.professor;

import main.java.com.qcm.model.Quiz;

import javax.swing.*;
import java.awt.*;

public class QuizUsers extends JPanel {

    public QuizUsers(){}
    public QuizUsers(Quiz quiz){
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createTitledBorder(quiz.getTitle()));
        setVisible(true);
    }

}
