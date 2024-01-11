package main.java.com.qcm.frames.professor;

import main.java.com.qcm.model.Professor;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.ProfessorPanel;
import main.java.com.qcm.panel.StudentPanel;
import main.java.com.qcm.panel.professor.QuizList;
import main.java.com.qcm.panel.professor.QuizUsers;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Home extends JFrame {

    Professor professor;
    private JPanel cardPanel;
    public static CardLayout cardLayout;
    private JPanel mainPanel;
    private JMenuItem loginItem;
    private JMenuItem studentMenu;
    private JPanel quizList;
    private JPanel quizData;
    private JPanel allStudents;

    public Home() {
    }

    public Home(Professor professor) throws SQLException {
        this.professor = professor;


        setTitle("Professor");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        menu();
        professorInfo();

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);


        quizzes();
        quizData(Quiz.all().getFirst());
        allStudents();
        cardPanel.add(quizList, "QCMS");
        cardPanel.add(quizData, "QCM");
        cardPanel.add(allStudents, "STUDENTS");

//        cardPanel.add(new QuizList(), "PROFESSOR");
        add(cardPanel);


        loginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "QCMS");
            }
        });

        studentMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "STUDENTS");
            }
        });

        setVisible(true);
    }


    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        loginItem = new JMenuItem("List des QCMS");
         studentMenu = new JMenuItem("lIST DES USERS");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(loginItem);
        menu.add(studentMenu);
        menu.addSeparator();
        menu.add(exitItem);
        menuBar.add(menu);
    }

    private void professorInfo() {
        JPanel userInfoPanel = new JPanel(new GridLayout(3, 3));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Professor Information"));

        JLabel nameLabel = new JLabel("Name:");
        JLabel nameValueLabel = new JLabel(professor.getFull_name()); // Set the initial value

        JLabel ageLabel = new JLabel("Level:");
        JLabel ageValueLabel = new JLabel(professor.getCin()); // Set the initial value

        userInfoPanel.add(nameLabel);
        userInfoPanel.add(nameValueLabel);
        userInfoPanel.add(ageLabel);
        userInfoPanel.add(ageValueLabel);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInfoPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
    }


    public void quizzes() throws SQLException {
        quizList = new JPanel();
        quizList.setLayout(new GridLayout(3, 3));
        quizList.setBorder(BorderFactory.createTitledBorder("QCMs"));
        quizList.setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        tableModel.addColumn("id");
        tableModel.addColumn("title");
        tableModel.addColumn("targetCategory");

        for (Quiz quiz : Quiz.all()) {
            Object[] rowData = {quiz.getId(), quiz.getTitle(), quiz.targetCategory()};
            tableModel.addRow(rowData);
        }


        JTable dataTable = new JTable(tableModel);

        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {
                        try {
                            quizData(Quiz.all().get(selectedRow));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        cardPanel.remove(1);
                        cardPanel.add(quizData, "QCM");
                        cardLayout.show(cardPanel, "QCM");
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(dataTable);
        quizList.add(scrollPane, BorderLayout.CENTER);

    }

    public void quizData(Quiz quiz) throws SQLException {
        quizData = new JPanel();
        quizData.setLayout(new GridLayout(3, 3));
        quizData.setBorder(BorderFactory.createTitledBorder(quiz.getTitle()+ " users"));
        quizData.setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        tableModel.addColumn("id");
        tableModel.addColumn("cin");
        tableModel.addColumn("fullname");
        tableModel.addColumn("major");
        tableModel.addColumn("grade");
        tableModel.addColumn("score");
        tableModel.addColumn("status");
        for (Student student : quiz.getSuccessfulStudents()) {
            int score = student.quizScore(quiz.getId());
            Object[] rowData = {
                    student.getId(),
                    student.getCin(),
                    student.getFull_name(),
                    student.getMajor(),
                    student.getGrade() ,
                    score,
                    score>=10 ? "passed" : "failed",
            };
            tableModel.addRow(rowData);
        }
        JTable dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        quizData.add(scrollPane, BorderLayout.CENTER);
    }

    public void allStudents() throws SQLException {
        allStudents = new JPanel();
        allStudents.setLayout(new GridLayout(3, 3));
        allStudents.setBorder(BorderFactory.createTitledBorder("List des users"));
        allStudents.setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };

        tableModel.addColumn("id");
        tableModel.addColumn("cin");
        tableModel.addColumn("fullname");
        tableModel.addColumn("major");
        tableModel.addColumn("grade");
//        tableModel.addColumn("created at");
        for (Student student : Student.all()) {

            Object[] rowData = {
                    student.getId(),
                    student.getCin(),
                    student.getFull_name(),
                    student.getMajor(),
                    student.getGrade() ,
//                    student.getC() ,

            };
            tableModel.addRow(rowData);
        }
        JTable dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        allStudents.add(scrollPane, BorderLayout.CENTER);
    }

}
