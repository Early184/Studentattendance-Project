package GUI.Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.ClassList;
import Models.Schueler;
import db.Database;

public class EditClassDialog extends JPanel implements ActionListener {
    ListCreationDialog dialog;
    DialogMainPanelClass classPanel;
    MainFrame frame;
    Database database;

    
    
    public EditClassDialog(MainFrame frame){
        this.frame = frame;
        this.database = Database.getInstance();
        
        dialog = new ListCreationDialog(frame);
        classPanel = new DialogMainPanelClass();
        classPanel.creationButtonPanel.closeButton.addActionListener(this);
        classPanel.creationButtonPanel.saveButton.setActionCommand("save2");
        classPanel.creationButtonPanel.saveButton.addActionListener(this);
        classPanel.switchPanel.switchButton.addActionListener(this);
        
        showStudentsInLists();
        dialog.add(classPanel);
        dialog.setVisible(true);
        
    }
    public void showStudentsInLists(){
        
        //ClassList chosenList = database.getClassListsArray().get(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex());
        classPanel.fillChosenClassForEdit(database.getClassListsArray().get(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex()));
        classPanel.grade.setText(database.getClassListsArray().get(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex()).getGrade());
        classPanel.specialization.setText(database.getClassListsArray().get(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex()).getSpecialization());
        for(Schueler schueler : database.getClassListsArray().get(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex()).getClassArray()){
            database.showClassArray().add(schueler);
        }
       
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String userSwitchStudent = e.getActionCommand();
        switch (userSwitchStudent) {
            case "switch":
                //studentToClass
                if (classPanel.listOfStudents.getSelectedIndex() != -1 && classPanel.classList.getSelectedIndex() == -1){
                    classPanel.addStudentToClass();
                    database.refreshModelForStudents(classPanel.listModelClass, database.showClassArray());
                    database.refreshModelForStudents(classPanel.listModelStudents, database.showStudents());
                    
                }
                //StudentBackToStudentList
                if(classPanel.listOfStudents.getSelectedIndex() == -1 && classPanel.classList.getSelectedIndex() != -1 ){
                    classPanel.returnStudentToList();
                    database.refreshModelForStudents(classPanel.listModelClass, database.showClassArray());
                    database.refreshModelForStudents(classPanel.listModelStudents, database.showStudents());
                }
                break;
            case "save2" :
                ClassList classList = new ClassList(classPanel.grade.getText(), classPanel.specialization.getText(), database.showClassArray());
                database.getClassListsArray().add(classList);
                database.getClassListsArray().remove(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex());
                for(Schueler Schueler : database.showClassArray()){
                    database.addStudentToStudentArray(Schueler);
                };
                database.writeListOfClasses();
                dialog.dispose();
                classList.setGrade(classPanel.grade.getText());
                classList.setSpecialization(classPanel.specialization.getText());
                database.refreshModelForClassLists(database.getListModelOfClasses(), database.getClassListsArray());
                break;
            default:
                break;
            }
    }
    
}
