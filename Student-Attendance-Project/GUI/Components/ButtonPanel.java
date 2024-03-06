package GUI.Components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;


import db.Database;



public class ButtonPanel extends JPanel{
    private Database database;
    JButton useButton, newButton, editButton, deleteButton;
    
    public ButtonPanel() {
        
        this.database = Database.getInstance();
        useButton = new JButton("Use");
        useButton.setBackground(Color.black);
        useButton.setForeground(Color.WHITE);
        useButton.setActionCommand("use");
        
        useButton.setFocusPainted(false);

        newButton = new JButton("New");
        newButton.setBackground(Color.black);
        newButton.setForeground(Color.WHITE);
        newButton.setActionCommand("new");
        
        newButton.setFocusPainted(false);

        editButton = new JButton("Edit");
        editButton.setBackground(Color.black);
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setActionCommand("edit");

        deleteButton = new JButton("Delete");
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.black);
        deleteButton.setActionCommand("delete");
        deleteButton.setFocusPainted(false);
        setBackground(Color.DARK_GRAY);
        add(useButton);
        add(newButton);
        add(editButton);
        add(deleteButton);
        setLayout(new FlowLayout(1,200,0));
    }

    
    
    

    
}
