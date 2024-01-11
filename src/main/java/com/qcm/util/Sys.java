package main.java.com.qcm.util;

import main.java.com.qcm.Main;
import main.java.com.qcm.frames.student.Home;
import main.java.com.qcm.frames.student.StartQuiz;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class Sys {

    public static Home home;
    public static StartQuiz startQuiz;
    public static void restartApplication()
    {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home") + File.separator + "bin" + File.separator + "java ");
        for (String jvmArg : ManagementFactory.getRuntimeMXBean().getInputArguments())
        {
            cmd.append(jvmArg + " ");
        }

        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Window.class.getName()).append(" ");

        try
        {
            Runtime.getRuntime().exec(cmd.toString());
        }
        catch (IOException e1)
        {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.exit(0);
    }
}
