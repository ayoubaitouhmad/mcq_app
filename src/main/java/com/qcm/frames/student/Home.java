package main.java.com.qcm.frames.student;

import main.java.com.qcm.DatabaseListExample;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.StudentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {

    private DefaultListModel<String> listModel;
    private List<Quiz> items;
    private JList<String> itemList;

    public Home(Student student) throws SQLException {
        setTitle("Styled Login and Navigation Example");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        // Create a panel for user information
        JPanel userInfoPanel = new JPanel(new GridLayout(3, 3));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("User Information"));

        JLabel nameLabel = new JLabel("Name:");
        JLabel nameValueLabel = new JLabel(student.getFull_name()); // Set the initial value

        JLabel ageLabel = new JLabel("Level:");
        JLabel ageValueLabel = new JLabel(student.getMajor()); // Set the initial value

        JLabel gradeLabel = new JLabel("Grade:");
        JLabel gradeValueLabel = new JLabel(student.getGrade()); // Set the initial value


        userInfoPanel.add(nameLabel);
        userInfoPanel.add(nameValueLabel);
        userInfoPanel.add(ageLabel);
        userInfoPanel.add(ageValueLabel);

        userInfoPanel.add(gradeLabel);
        userInfoPanel.add(gradeValueLabel);


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInfoPanel, BorderLayout.NORTH);
        List<Quiz> quizzes = student.quizzes();
        listModel = new DefaultListModel<>();

        for (Quiz quiz: quizzes) {
            listModel.addElement(quiz.getName());
        }

        itemList = new JList<>(listModel);


        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = itemList.getSelectedIndex();
                JOptionPane.showMessageDialog(Home.this, quizzes.get(selectedIndex).getId());
            }
        });

        mainPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);


        // Display the frame
        setVisible(true);
    }


}
