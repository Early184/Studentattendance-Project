package GUI.Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


import db.Database;

public class MainFrame extends JFrame {
    private Database database;
    public MainFrame() {
        this.database = Database.getInstance();
        database.readStudents();
        setTitle("Attendance-Check");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        mainPanel mainPanel = new mainPanel(this);
        add(mainPanel);
        setVisible(true);
    }
    
    public void listOrStudent(){
        Object [] options = {" Klasse ", " Student "};
        String message = "Bitte wählen sie eine Option.";

        int answer = JOptionPane.showOptionDialog(this, message, "Auswahl", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
        if(answer == 0){
            // method for creating list
            // visibility manager for creating list
            createListDialog();
            

        }
        else if(answer == 1){
            
            createStudentDialog();

            //method for student
            //visibility manager for student
        }

    }
    public void createListDialog(){
        ListCreationDialog studentListCr = new ListCreationDialog(this);
        DialogMainPanelClass dialogPanelClass = new DialogMainPanelClass();
        studentListCr.add(dialogPanelClass);
        
        studentListCr.setVisible(true);
        //Saving functions here
        //dispose function for list model
    }
    public void createStudentDialog(){
        ListCreationDialog studentCreation = new ListCreationDialog(this);
        DialogMainPanelStudent dialogPanelStudent = new DialogMainPanelStudent();
        studentCreation.add(dialogPanelStudent);
        studentCreation.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        dialogPanelStudent.creationButtonPanel.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                studentCreation.dispose();
            }
        });
        studentCreation.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                database.writeStudents();
            }
        });
        studentCreation.setVisible(true);
    }
    
    

    
}
