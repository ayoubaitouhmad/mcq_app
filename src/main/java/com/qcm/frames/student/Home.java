package main.java.com.qcm.frames.student;

import main.java.com.qcm.DatabaseListExample;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.StudentPanel;
import main.java.com.qcm.util.Sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Home extends JFrame {

    private static Home instance;
    private Student student;

    private DefaultListModel<String> listModel;
    private List<Quiz> items;
    private JList<String> itemList;
    private List<Quiz> quizzes;
    private JPanel mainPanel;

    public static Home getInstance(Student student , boolean refresh ) throws SQLException {
        if(refresh){
            return  new Home(student);
        }
        if (instance == null) {
            instance = new Home(student);
        }
        return instance;
    }

    public void refresh() throws SQLException {
        fillData();
    }


    public Home(Student student) throws SQLException {
        this.student = student;
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


      mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInfoPanel, BorderLayout.NORTH);

        fillData();


//        itemList.setCellRenderer(new CustomBackgroundRenderer());
//        changeBackgroundColor(itemList, 2, Color.RED);



        // Set the main panel as the content pane
        setContentPane(mainPanel);


        // Display the frame
        setVisible(true);
    }


    private  void fillData() throws SQLException {

        quizzes = student.quizzes();
        listModel = new DefaultListModel<>();

        for (Quiz quiz : quizzes) {
            listModel.addElement(quiz.getName(
                    student
            ));
        }

        itemList = new JList<>(listModel);
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = itemList.getSelectedIndex();
                try {
                    int score = student.quizScore(quizzes.get(selectedIndex).getId());
                    System.out.println(student.quizScore(quizzes.get(selectedIndex).getId()));
                    if (score == 0) {

                        Home.getInstance(student,false).setVisible(false);
                        new StartQuiz(
                                quizzes.get(selectedIndex),
                                student
                        );

                    } else {
                        JOptionPane.showMessageDialog(Home.this, "Already take the quiz , score:" + score);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        mainPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);
    }

    private static void changeBackgroundColor(JList<String> jList, int index, Color backgroundColor) {
        DefaultListModel<String> model = (DefaultListModel<String>) jList.getModel();
        model.getElementAt(index);
        jList.repaint();
    }

    static class CustomBackgroundRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Change background color for specific items
            if (value != null && value.toString().endsWith("Item 2")) {
                component.setBackground(Color.YELLOW);
            } else if (value != null && value.toString().endsWith("Item 5")) {
                component.setBackground(Color.GREEN);
            } else {
                // Reset background color for other items
                component.setBackground(list.getBackground());
            }

            return component;
        }
    }

}
