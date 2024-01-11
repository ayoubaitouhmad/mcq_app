package main.java.com.qcm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseListExample extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> itemList;
    private List<Item> items;

    public DatabaseListExample() {
        setTitle("Database List Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        items = new ArrayList<>();
        fetchDataFromDatabase();

        listModel = new DefaultListModel<>();

        for (Item item : items) {
            listModel.addElement(item.getName());
        }


        itemList = new JList<>(listModel);


        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Item selectedItem = items.get(selectedIndex);
                    System.out.println("Selected: " + selectedItem.getName() + " (ID: " + selectedItem.getId() + ")");
                    // Perform actions based on the selected item and its ID
                }
            }
        });

        // Create a main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(itemList), BorderLayout.CENTER);

        // Set the main panel as the content pane
        setContentPane(mainPanel);

        // Display the frame
        setVisible(true);
    }

    private void fetchDataFromDatabase() {
        
        items.add(new Item(1, "Item 1"));
        items.add(new Item(28, "Item 2"));
        items.add(new Item(3, "Item 3"));
    }

    // Represents an item with an ID and name
    private static class Item {
        private int id;
        private String name;

        public Item(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }


}
