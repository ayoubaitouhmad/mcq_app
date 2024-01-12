package main.java.com.qcm.frames.student;

import main.java.com.qcm.Index;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.util.Component;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Home extends JFrame {


    private Student student;

    private List<Quiz> studentQuizzes;
    private JPanel mainPanel;


    JMenuItem allQuizzesMenuItem;
    JMenuItem myQuizzesMenuItem;

    public static JPanel cardPanel;
    public static CardLayout cardLayout;
    private JPanel quizListPanel;
    private JPanel startQuizPanel;
    private JPanel studentQuizzesPanel;


    public Home(Student student) throws SQLException {
        this.student = student;
        this.studentQuizzes = student.getQuizzesByStudentMajor();
        Component.jFrame(this, "Student Dashboard");
        menu();
        studentInfo();
        quizzes();
        studentQuizzesPanelInit();


        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        startQuizPanel = new StartQuiz(studentQuizzes.getFirst(), student);
        cardPanel.add(quizListPanel, "QUIZZES");
        cardPanel.add(startQuizPanel, "START_QUIZ");
        cardPanel.add(studentQuizzesPanel, "STUDENT_QUIZZES");

        add(cardPanel);

        setVisible(true);
    }

    private void studentQuizzesPanelInit() throws SQLException {
        studentQuizzesPanel = new JPanel();
        studentQuizzesPanel.setLayout(new GridLayout(3, 3));
        studentQuizzesPanel.setBorder(BorderFactory.createTitledBorder("QCMs de filiere " + this.student.getMajor()));
        studentQuizzesPanel.setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        tableModel.addColumn("Reference");
        tableModel.addColumn("title");
        tableModel.addColumn("date");
        tableModel.addColumn("score");
        tableModel.addColumn("Decision");

        for (Student student : student.getStudentQuizzesHistorique()) {
            Object[] rowData = {
                    "#" + student.quiz.getId(),
                    student.quiz.getTitle(),
                    student.studentAttempt.getCreatedAt(),
                    student.studentAttempt.getScore(),
                    student.studentAttempt.result(),
            };
            tableModel.addRow(rowData);
        }


        JTable dataTable = new JTable(tableModel);

        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {

                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(dataTable);
        studentQuizzesPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        allQuizzesMenuItem = new JMenuItem("List des QCMS");
        myQuizzesMenuItem = new JMenuItem("mes QCMS");
        JMenuItem back = new JMenuItem("back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Index.getInstance().setVisible(true);
            }
        });

        allQuizzesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "QUIZZES");
            }
        });

        myQuizzesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentQuizzesPanelInit();
                    cardPanel.remove(2);
                    cardPanel.add(studentQuizzesPanel, "STUDENT_QUIZZES");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(cardPanel, "STUDENT_QUIZZES");
            }
        });


        menu.add(allQuizzesMenuItem);
        menu.add(myQuizzesMenuItem);
        menu.addSeparator();
        menu.add(back);
        menuBar.add(menu);


    }


    private void studentInfo() {
        JPanel userInfoPanel = new JPanel(new GridLayout(4, 2));
        userInfoPanel.setBorder(BorderFactory.createTitledBorder("Professor Information"));

        JLabel nameLabel = new JLabel("nom:");
        JLabel nameValueLabel = new JLabel(student.getFull_name());

        JLabel ageLabel = new JLabel("cin:");
        JLabel ageValueLabel = new JLabel(student.getCin());

        JLabel levelLabel = new JLabel("level:");
        JLabel levelValueLabel = new JLabel(student.getGrade());

        JLabel majorLabel = new JLabel("filiere:");
        JLabel majorValueLabel = new JLabel(student.getMajor());


        userInfoPanel.add(nameLabel);
        userInfoPanel.add(nameValueLabel);
        userInfoPanel.add(ageLabel);
        userInfoPanel.add(ageValueLabel);

        userInfoPanel.add(levelLabel);
        userInfoPanel.add(levelValueLabel);
        userInfoPanel.add(majorLabel);
        userInfoPanel.add(majorValueLabel);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(userInfoPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);
    }

    public void quizzes() throws SQLException {
        quizListPanel = new JPanel();
        quizListPanel.setLayout(new GridLayout(3, 3));
        quizListPanel.setBorder(BorderFactory.createTitledBorder("QCMs de filiere " + this.student.getMajor()));
        quizListPanel.setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        tableModel.addColumn("reference");
        tableModel.addColumn("title");
        tableModel.addColumn("targetCategory");

        for (Quiz quiz : studentQuizzes) {
            Object[] rowData = {"#" + quiz.getId(), quiz.getTitle(), quiz.targetCategory()};
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
                            Quiz quiz = studentQuizzes.get(selectedRow);
                            if (!student.hasStudentTakeQuiz(quiz.getId())) {
                                if (!quiz.getQuestions().isEmpty()) {
                                    startQuizPanel = new StartQuiz(quiz, student);
                                    cardPanel.remove(1);
                                    cardPanel.add(startQuizPanel, "START_QUIZ");
                                    cardLayout.show(cardPanel, "START_QUIZ");
                                } else {
                                    Component.alert(Home.this, "Quiz Doest have any questios");
                                }
                            } else {
                                Component.alert(Home.this, "You already take quiz and your score is  " + student.quizScore(quiz.getId()));
                            }

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(dataTable);
        quizListPanel.add(scrollPane, BorderLayout.CENTER);

    }


}
