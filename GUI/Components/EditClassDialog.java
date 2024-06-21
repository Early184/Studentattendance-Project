package GUI.Components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;


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
        int selectedIndex = frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex();
        if (selectedIndex != -1) {
            ClassList chosenList = database.getClassListsArray().get(selectedIndex);
            classPanel.fillChosenClassForEdit(chosenList);
            classPanel.grade.setText(chosenList.getGrade());
            classPanel.specialization.setText(chosenList.getSpecialization());
            List<Schueler> classArray = chosenList.getClassArray();
            
            database.showClassArray().clear();
            
            database.showClassArray().addAll(classArray);
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
                database.getClassListsArray().remove(frame.mainPanel.classListPanel.JListOfClasses.getSelectedIndex());
                database.getClassListsArray().add(classList);
                
                
                for(Schueler Schueler : database.showClassArray()){
                    database.addStudentToStudentArray(Schueler);
                };
                
                classList.setGrade(classPanel.grade.getText());
                classList.setSpecialization(classPanel.specialization.getText());
                database.writeListOfClasses();
                dialog.dispose();
                
                
                break;
            case "close" : 
                dialog.dispose();
            default:
                break;
            }
    }
    
}
