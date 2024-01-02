package main.java.com.qcm;

import main.java.com.qcm.model.Professor;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.panel.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class NavigationMenuExample extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;


    private LoginForm studentForm;
    private LoginForm professorForm;

    public NavigationMenuExample() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Styled Login and Navigation Example");
        setSize(400, 300);
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
        studentForm =new LoginForm(
                "Etudiants Space ",
                "nom",
                student()
        );

        // Panel 2: Other Panel
        professorForm = new LoginForm(
                "Professor Space ",
                "nom",
                student()
        );


        cardPanel.add(studentForm, "Login Panel");
        cardPanel.add(professorForm, "Other Panel");

        add(cardPanel);

        loginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login Panel");
            }
        });

        otherPanelItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Other Panel");
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



    public ActionListener student(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Student student =  Student.findByName(studentForm.getUsernameFieldValue());
                    if (student!= null) {
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, student.getFull_name());
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, student.getId());
                    } else {
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, "etudiants does not exist!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
    }


    public ActionListener professor(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Professor professor =  Professor.findByName(studentForm.getUsernameFieldValue());
                    if (professor!= null) {
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, professor.getFull_name());
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, professor.getId());
                    } else {
                        JOptionPane.showMessageDialog(NavigationMenuExample.this, "professors does not exist!");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
    }

}
