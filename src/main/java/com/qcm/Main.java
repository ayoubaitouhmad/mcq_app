package main.java.com.qcm;



import main.java.com.qcm.frames.student.*;
import main.java.com.qcm.model.Question;
import main.java.com.qcm.model.Quiz;
import main.java.com.qcm.model.Student;
import main.java.com.qcm.util.Sys;

import javax.swing.*;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Home.getInstance(Student.first() , false);
    }

}
