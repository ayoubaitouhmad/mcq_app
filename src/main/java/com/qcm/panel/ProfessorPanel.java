package main.java.com.qcm.panel;

import main.java.com.qcm.frames.student.Home;
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
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel("Etudiant Space");
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


        JLabel filiereLabel = new JLabel("major");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2, 0, 10, 10);
        add(filiereLabel, gbc);


        String[] majorOptions = {"informatique", "médicale", "Option C"};
        majorCombobox = createStyledComboBox(majorOptions);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(majorCombobox, gbc);


        JLabel levelLabel = new JLabel("Level");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(2, 0, 10, 10);
        add(levelLabel, gbc);


        String[] gradeOptions = {"Option A", "Option B", "Option C"};
        gradeCombobox = createStyledComboBox(gradeOptions);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 0;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(gradeCombobox, gbc);


        JButton loginButton = new JButton("start");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;


        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(signUpEventListener());
        add(loginButton, gbc);
    }


    private static JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);

        // Set a custom renderer for better styling
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(new Font("Arial", Font.PLAIN, 14));
                label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Add padding

                if (isSelected) {
                    label.setBackground(new Color(173, 216, 230)); // Light blue background for selected item
                }

                return label;
            }
        });

        return comboBox;
    }


    public ActionListener signUpEventListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Student student = Student.findByCin(cinField.getText());
                    if (student != null) {


                        Home home = new Home(student);
                        home.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

//                        JOptionPane.showMessageDialog(StudentPanel.this, student.getFull_name());
//                        JOptionPane.showMessageDialog(StudentPanel.this, student.getId());
                    } else {

                        if (
                                nomField.getText().isEmpty() ||
                                        cinField.getText().isEmpty() ||
                                        gradeCombobox.getSelectedItem().toString().isEmpty() ||
                                        majorCombobox.getSelectedItem().toString().isEmpty()
                        ) {
                            JOptionPane.showMessageDialog(ProfessorPanel.this, "all filed are required");
                        } else {

                            Student createdStudent = new Student();
                            createdStudent.setFull_name(nomField.getText());
                            createdStudent.setCin(cinField.getText());

                            createdStudent.setGrade(gradeCombobox.getSelectedItem().toString());
                            createdStudent.setMajor(majorCombobox.getSelectedItem().toString());
                            createdStudent.save();

                            Home home = new Home(createdStudent);
                            home.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//                            JOptionPane.showMessageDialog(StudentPanel.this, "student created succesfully!");
                        }

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
    }


}
