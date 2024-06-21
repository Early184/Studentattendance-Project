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
    public ListCreationDialog studentListCr;
    public DialogMainPanelClass dialogPanelClass;
    public MainFramePanel mainPanel;
    public MainFrame() {
        
        this.database = Database.getInstance();
        database.readStudents();
        setTitle("Attendance-Check");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        mainPanel = new MainFramePanel(this);
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
            createClassDialog();
            
        }
        else if(answer == 1){
            
            createStudentDialog();

            //method for student
            //visibility manager for student
        }

    }
    public void createClassDialog(){
        studentListCr = new ListCreationDialog(this);
        dialogPanelClass = new DialogMainPanelClass();
        studentListCr.add(dialogPanelClass);
        
        studentListCr.setVisible(true);
        database.showClassArray().clear();
        //Saving functions here
        //dispose function for list model

        dialogPanelClass.creationButtonPanel.closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                studentListCr.dispose();
            }
        });
    }
    
    public void createStudentDialog(){
        ListCreationDialog studentCreation = new ListCreationDialog(this);
        DialogMainPanelStudent dialogPanelStudent = new DialogMainPanelStudent();
        studentCreation.add(dialogPanelStudent);
        
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
