package main.java.com.qcm;


import main.java.com.qcm.frames.professor.Home;
import main.java.com.qcm.model.Professor;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
//        Home.getInstance(Student.first() , false);
        new Home(
                Professor.first()
        );
    }

}
