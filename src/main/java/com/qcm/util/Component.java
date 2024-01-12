package main.java.com.qcm.util;

import javax.swing.*;

public class Component {

    private static JFrame jFrame;
    private final static int jFrameHeight = 600;
    private final static int jFrameWidth = 800;

    public static void jFrame(JFrame jFrame, String title) {
        Component.jFrame = jFrame;
        jFrame.setTitle(title);
        jFrame.setSize(jFrameWidth, jFrameHeight);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
    }

    public static void alert(JFrame jFrame, String title) {
        JOptionPane.showMessageDialog(jFrame, title);
    }


}
