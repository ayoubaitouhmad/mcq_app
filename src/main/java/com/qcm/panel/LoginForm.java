package main.java.com.qcm.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LoginForm extends JPanel {

    private GridBagConstraints gbc;
    private JTextField usernameField;

    public LoginForm(
            String title,
            String label,
            ActionListener actionListener
    ) {

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setBackground(new Color(240, 240, 240));

        JLabel titleLabel = new JLabel(title.toUpperCase(Locale.ROOT));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel(label.toUpperCase(Locale.ROOT) + ":");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(usernameField, gbc);


        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginButton.setBackground(new Color(51, 153, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(actionListener);
        add(loginButton, gbc);
    }


    public String getUsernameFieldValue() {
        return usernameField.getText();
    }
}
