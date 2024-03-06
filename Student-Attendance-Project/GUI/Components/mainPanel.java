package GUI.Components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Models.ClassList;
import db.Database;



public class mainPanel extends JPanel implements ActionListener{
    private MainFrame frame;
    private Database database;
    public ClassListPanel classListPanel;
    private EditClassDialog editClassDialog;
    public mainPanel(MainFrame frameCon) {
        this.frame = frameCon;
        this.database = Database.getInstance();
        
        setLayout(new BorderLayout());

        TitleNamePanel title = new TitleNamePanel("Hauptmenü");
        add(title, BorderLayout.NORTH);
        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.useButton.addActionListener(this);
        buttonPanel.editButton.addActionListener(this);
        buttonPanel.deleteButton.addActionListener(this);
        buttonPanel.newButton.addActionListener(this);
        add(buttonPanel,BorderLayout.CENTER);
        
        
        
        classListPanel = new ClassListPanel();
        add(classListPanel,BorderLayout.SOUTH);
        setVisible(true);
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
            
            if(classListPanel.JListOfClasses.getSelectedIndex() != -1){
                database.showStudents().clear();
                database.readStudents();
                editClassDialog = new EditClassDialog(frame);
                
                database.showClassArray().clear();
                database.getListModelOfClasses().clear();

                database.refreshModelForClassLists(database.getListModelOfClasses(), database.getClassListsArray());
                
            }
            
            
            
            ;break;
        case "delete":
            //spotlight on list to delete, then open JOptionPane for commit
            System.out.println(database.getClassListsArray());
            database.getClassListsArray().remove(classListPanel.JListOfClasses.getSelectedIndex());
            database.writeListOfClasses();
            database.refreshModelForClassLists(database.getListModelOfClasses(),database.getClassListsArray());
            
            break;
       }
    }
    
}
