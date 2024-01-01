package main.java.com.qcm;

import javax.swing.*;

public class MCQApp {


    JFrame jFrame = new JFrame();
    JTextArea jTextArea = new JTextArea();
    JButton jButton = new JButton();


    public MCQApp() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(650, 650);
    }

    public void start() {

        jFrame.setVisible(true);
    }

}
