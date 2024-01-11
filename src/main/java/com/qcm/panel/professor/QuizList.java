package main.java.com.qcm.panel.professor;

import main.java.com.qcm.model.Quiz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class QuizList extends JPanel {
    public QuizList() throws SQLException {
        setLayout(new GridLayout(3, 3));
        setBorder(BorderFactory.createTitledBorder("Professor Information"));
        setVisible(true);


        // Create a non-editable DefaultTableModel
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        tableModel.addColumn("id");
        tableModel.addColumn("title");
        tableModel.addColumn("targetCategory");

        for (Quiz quiz : Quiz.all()) {
            Object[] rowData = {quiz.getId(), quiz.getTitle(), quiz.targetCategory()};
            tableModel.addRow(rowData);
        }


        JTable dataTable = new JTable(tableModel);

        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = dataTable.getSelectedRow();
                    if (selectedRow != -1) {

                        try {
                            new QuizUsers(Quiz.all().get(0));
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);


    }
}
