package main.java.com.qcm;

import javax.swing.*;
import java.awt.*;

public class MCQApp extends JFrame {


    JTextArea jTextArea = new JTextArea();
    JButton jButton = new JButton();

    JTextField email = new JTextField(25); //indicate how many columns you need


    public MCQApp() {
//        init();
        (new NavigationMenuExample()).setVisible(true);

    }

    private void init() {
        setTitle("QCM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void start() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        jPanel.setBackground(Color.gray);
//        jPanel.setPreferredSize(new Dimension(250,250));
        add(jPanel, BorderLayout.CENTER);

        JTextField jTextField = new JTextField(16);
        jPanel.add(jTextField);

    }

}
