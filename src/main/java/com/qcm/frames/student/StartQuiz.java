package main.java.com.qcm.frames.student;

import main.java.com.qcm.WrappingJRadioButton;
import main.java.com.qcm.model.Option;
import main.java.com.qcm.model.Question;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;


public class StartQuiz extends JFrame {

    private List<Question> questions;
    private JRadioButton radioButtons[] = new JRadioButton[4];


    private int currentQuestionIndex;
    private int score;
    private ButtonGroup genderGroup;
    private JButton submitButton;
    private Quiz quiz;
    private Student student;

    private JLabel questionLabel;
    private JPanel jPanel;

    public StartQuiz(
            Quiz quiz,
            Student student
    ) throws SQLException {
        this.quiz = quiz;
        this.student = student;
        this.questions = this.quiz.getQuestions();
        currentQuestionIndex = -1;
        setTitle("Gender Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);


        initializeComponents();

        setLayout(new FlowLayout());


        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() throws SQLException {

        questionLabel = new JLabel("");
        genderGroup = new ButtonGroup();

        add(questionLabel);
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton();
            genderGroup.add(radioButtons[i]);
            radioButtons[i].setPreferredSize(new java.awt.Dimension(700, 20));
            add(radioButtons[i]);
        }


        populateGenderOptions();

        submitButton = new JButton("Submit");
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    displaySelectedGender();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void populateGenderOptions() throws SQLException {
        genderGroup.clearSelection();
        currentQuestionIndex++;
        questionLabel.setText(currentQuestion().getQuestion());
        Question question = currentQuestion();
        List<Option> options = question.options();
        for (int i = 0; i < radioButtons.length; i++) {
            Option option = options.get(i);
            radioButtons[i].setText(option.getOption());
        }

    }


    private void displaySelectedGender() throws SQLException {

        System.out.println("currentQuestionIndex:" + currentQuestionIndex);

        if (currentQuestionIndex < questions.size()) {
            Enumeration<AbstractButton> buttons = genderGroup.getElements();
            while (buttons.hasMoreElements()) {
                JRadioButton button = (JRadioButton) buttons.nextElement();
                if (button.isSelected()) {
                    if (Objects.equals(button.getText(), Objects.requireNonNull(currentQuestion()).correctOption().getOption())) {
                        score++;

                    }
                    else {
                        score--;
                    }

                }


            }
            populateGenderOptions();

        }


    }


    private Question currentQuestion() {

        if (currentQuestionIndex < questions.size()) {
            return questions.get(currentQuestionIndex);
        }

        System.out.println("score:" + score);




        return null;

    }


}