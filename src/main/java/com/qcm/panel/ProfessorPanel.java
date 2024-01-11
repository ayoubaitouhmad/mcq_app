package main.java.com.qcm.panel;

import main.java.com.qcm.frames.professor.Home;
import main.java.com.qcm.model.Professor;
import main.java.com.qcm.model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfessorPanel extends JPanel {

    private GridBagConstraints gbc;
    private JTextField cinField;
    private JTextField nomField;
    private JComboBox<String> majorCombobox;
    private JComboBox<String> gradeCombobox;

    public ProfessorPanel() {
        setVisible(true);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Professor Space");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(titleLabel, gbc);

        JLabel cinLabel = new JLabel("cin:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;


        gbc.insets = new Insets(0, 0, 10, 10);
        add(cinLabel, gbc);

        cinField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(cinField, gbc);


        JLabel nomLabel = new JLabel("NOM:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;


        gbc.insets = new Insets(0, 0, 10, 10);
        add(nomLabel, gbc);

        nomField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(nomField, gbc);


        JButton loginButton = new JButton("start");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;


        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(signUpEventListener());
        add(loginButton, gbc);
    }


    public ActionListener signUpEventListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Professor professor = Professor.findByCin(cinField.getText());
                    if (professor != null) {
                        Home home = new Home();

                    } else {

                        if (
                                nomField.getText().isEmpty() ||
                                        cinField.getText().isEmpty()

                        ) {
                            JOptionPane.showMessageDialog(ProfessorPanel.this, "all filed are required");
                        } else {

                            Professor createdProfessor = new Professor();
                            createdProfessor.setFull_name(nomField.getText());
                            createdProfessor.setCin(cinField.getText());
                            createdProfessor.save();

                            Home home = new Home();
                            home.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                        }

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
    }


}
