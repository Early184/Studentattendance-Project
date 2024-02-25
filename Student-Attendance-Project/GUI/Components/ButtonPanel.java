package GUI.Components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JPanel;


import db.Database;



public class ButtonPanel extends JPanel implements ActionListener{
    private MainFrame frame;
    private Database database;
    public ButtonPanel(MainFrame frame) {
        this.frame= frame;
        this.database = Database.getInstance();
        JButton useButton = new JButton("Use");
        useButton.setBackground(Color.black);
        useButton.setForeground(Color.WHITE);
        useButton.setActionCommand("use");
        useButton.addActionListener(this);
        useButton.setFocusPainted(false);

        JButton newButton = new JButton("New");
        newButton.setBackground(Color.black);
        newButton.setForeground(Color.WHITE);
        newButton.setActionCommand("new");
        newButton.addActionListener(this);
        newButton.setFocusPainted(false);

        JButton editButton = new JButton("Edit");
        editButton.setBackground(Color.black);
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.setActionCommand("edit");

        JButton deleteButton = new JButton("Delete");
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

    @Override
    public void actionPerformed(ActionEvent e) {
       String userChoice = e.getActionCommand();

    switch(userChoice){
        case "use": 
            System.out.println("bla");
            //popupWindow with several methods etc.
            ;break;
        case "new":
            frame.listOrStudent();
            //opening JOptionPane for commandPrompt list or student 
            ;break;
        case "new2":
            System.out.println("bla");
            ;break;
        case "edit":
            System.out.println("bla");
            //Jesse sach mal an was wir da machen sollen
            ;break;
        case "delete":
            //spotlight on list to delete, then open JOptionPane for commit
            System.out.println("bla");
            break;
       }
    }
    
    

    
}
