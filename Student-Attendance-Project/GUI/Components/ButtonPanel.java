package GUI.Components;

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
        useButton.setActionCommand("use");
        useButton.addActionListener(this);
        useButton.setFocusPainted(false);

        JButton newButton = new JButton("New");
        newButton.setActionCommand("new");
        newButton.addActionListener(this);
        newButton.setFocusPainted(false);

        JButton editButton = new JButton("Edit");
        editButton.setFocusPainted(false);
        editButton.setActionCommand("edit");

        JButton deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.setFocusPainted(false);
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
