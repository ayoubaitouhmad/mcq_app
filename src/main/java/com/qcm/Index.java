package main.java.com.qcm;

import main.java.com.qcm.frames.student.Home;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.ProfessorPanel;
import main.java.com.qcm.panel.StudentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Index extends JFrame {

    private static Index instance;


    public static Index getInstance() {
        if (instance == null) {
            instance = new Index();
        }
        return instance;
    }


    private JPanel cardPanel;
    private CardLayout cardLayout;


    private StudentPanel studentForm;
    private ProfessorPanel professorForm;

    public Index() {

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Styled Login and Navigation Example");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        JMenuItem loginItem = new JMenuItem("Student Space");
        JMenuItem otherPanelItem = new JMenuItem("Professor space");
        JMenuItem exitItem = new JMenuItem("Exit");

        menu.add(loginItem);
        menu.add(otherPanelItem);
        menu.addSeparator();
        menu.add(exitItem);

        menuBar.add(menu);
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        // Panel 1: Login Panel
        studentForm =new StudentPanel();
        // Panel 2: Other Panel
        professorForm = new ProfessorPanel();
        cardPanel.add(studentForm, "STUDENT");
        cardPanel.add(professorForm, "PROFESSOR");
        add(cardPanel);
        loginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "STUDENT");
            }
        });

        otherPanelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "PROFESSOR");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }








}
