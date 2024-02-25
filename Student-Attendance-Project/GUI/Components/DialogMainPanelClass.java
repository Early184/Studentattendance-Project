package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Models.ClassList;
import Models.Schueler;
import db.Database;


public class DialogMainPanelClass extends JPanel implements ActionListener{
    public JTextField grade;
    public JTextField specialization;
    private Database database;
    private DefaultListModel<String> listModelStudents, listModelClass;
    private JList<String> listOfStudents, classList;
    private JLabel gradeLabel, specLabel;
    public DialogMainPanelClass(){
        this.database = Database.getInstance();
        //Top panel no extra class
        CreationButtonPanel creationButtonPanel = new CreationButtonPanel("save1");
        creationButtonPanel.saveButton.addActionListener(this);
        
        TitleNamePanel titleCreateList = new TitleNamePanel("Liste erstellen");
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.add(titleCreateList);
        northPanel.add(creationButtonPanel);
        
        setLayout(new BorderLayout());
        add(northPanel ,BorderLayout.NORTH);
        
        //add(creationButtonPanel,BorderLayout.CENTER);
        
        //CenterPanel for Textfields
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.DARK_GRAY);
        
        //gradePanel
        gradeLabel = new JLabel("Jahrgang:      ");
        gradeLabel.setForeground(Color.white);
        grade = new JTextField();
        grade.setPreferredSize(new Dimension(120, 25));
        JPanel gradePanel = new JPanel();
        gradePanel.setBackground(Color.darkGray);
        gradeLabel.setLayout(new FlowLayout());
        gradePanel.add(gradeLabel);
        gradePanel.add(grade);
        //grade.getText();  um String wert zu entziehen
        
        //specializationPanel
        specLabel = new JLabel("Fachbereich: ");
        specLabel.setForeground(Color.white);
        specialization = new JTextField();
        specialization.setPreferredSize(new Dimension(120, 25));
        JPanel specPanel = new JPanel();
        specPanel.setLayout(new FlowLayout());
        specPanel.setBackground(Color.darkGray);
        specPanel.add(specLabel);
        specPanel.add(specialization);
        //Hier das selbe
        centerPanel.add(gradePanel, BorderLayout.NORTH);
        centerPanel.add(specPanel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        //ListPanel
        /*DialogListFields listField = new DialogListFields();
        listField.setPreferredSize(new Dimension(400, 390));
        add(listField, BorderLayout.SOUTH);*/
        
        // Student Lists BorderLayout South
        
        JPanel mainListHolderPanel = new JPanel();
        mainListHolderPanel.setPreferredSize(new Dimension(400, 390));

        JPanel studentListHolderPanel = new JPanel();
        studentListHolderPanel.setBackground(Color.DARK_GRAY);
        
        studentListHolderPanel.setPreferredSize(new Dimension(300, 0));

        JPanel selectedStudents = new JPanel();
        selectedStudents.setBackground(Color.DARK_GRAY);
        
        selectedStudents.setPreferredSize(new Dimension(300, 0));

        mainListHolderPanel.setLayout(new BorderLayout());
        
        
        studentListHolderPanel.add(createStudentList());
        selectedStudents.add(createClassList());
        
        //Button with Icon
        SwitchButtonWithPanel switchPanel = new SwitchButtonWithPanel();
        switchPanel.switchButton.addActionListener(this);

        mainListHolderPanel.add(switchPanel, BorderLayout.CENTER);
        mainListHolderPanel.add(studentListHolderPanel, BorderLayout.WEST);
        mainListHolderPanel.add(selectedStudents, BorderLayout.EAST);
        add(mainListHolderPanel, BorderLayout.SOUTH);



        setVisible(true);
    }
    public JList<String> createStudentList(){
        listModelStudents = new DefaultListModel<String>();
        
        for(Schueler Schueler : database.showStudents()){
        listModelStudents.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
        };

        listOfStudents = new JList<String>(listModelStudents);
        listOfStudents.setForeground(Color.white);
        listOfStudents.setBackground(Color.black);
        listOfStudents.setPreferredSize(new Dimension(200,300));
        listOfStudents.setPreferredSize(new Dimension(200,300));
        listOfStudents.setBorder(new EmptyBorder(10,10,10,10));
        return listOfStudents;
    }
    public JList<String> createClassList(){
        listModelClass = new DefaultListModel<>();
        
        for(Schueler Schueler : database.showClassArray()){
            listModelClass.addElement(Schueler.getVorname() + " " + Schueler.getNachname());
        }
        classList = new JList<String>(listModelClass);
        classList.setForeground(Color.white);
        classList.setBackground(Color.black);
        classList.setPreferredSize(new Dimension(200,300));
        classList.setBorder(new EmptyBorder(10,10,10,10));
        return classList;
    }
    public void addStudentToClass(){
    
        String[] studentName = listOfStudents.getSelectedValue().split(" ");
        database.addStudentToClassArray(new Schueler(studentName[0], studentName[1]));
        database.showStudents().remove(listOfStudents.getSelectedIndex());
        //listModelStudents.remove(listOfStudents.getSelectedIndex());
    }
    //funktioniert noch nicht
    public void returnStudentToList(){
        String[] studentName = classList.getSelectedValue().split(" ");
        database.addStudentToStudentArray(new Schueler(studentName[0],studentName[1]));
        //database.deleteStudentFromClass(classList.getSelectedIndex());
        database.showClassArray().remove(classList.getSelectedIndex());
        //classList.remove(listOfStudents.getSelectedIndex());
    }
    public void clearListsAfterSafe(){
        for(Schueler schueler : database.showClassArray()){
            database.addStudentToStudentArray(schueler);
        }
        database.showClassArray().clear();

        database.refreshModelForStudents(listModelClass, database.showClassArray());
        database.refreshModelForStudents(listModelStudents, database.showStudents());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String userSwitchStudent = e.getActionCommand();
        switch (userSwitchStudent) {
            case "switch":
                //studentToClass
                if (listOfStudents.getSelectedIndex() != -1 && classList.getSelectedIndex() == -1){
                    addStudentToClass();
                    database.refreshModelForStudents(listModelClass, database.showClassArray());
                    database.refreshModelForStudents(listModelStudents, database.showStudents());
                    
                }
                //StudentBackToStudentList
                if(listOfStudents.getSelectedIndex() == -1 && classList.getSelectedIndex() != -1 ){
                    returnStudentToList();
                    database.refreshModelForStudents(listModelClass, database.showClassArray());
                    database.refreshModelForStudents(listModelStudents, database.showStudents());
                }
                break;
            case "save1" :
                ClassList classList = new ClassList(grade.getText(), specialization.getText(), database.showClassArray());
                database.getClassListsArray().add(classList);
                database.writeListOfClasses();
                clearListsAfterSafe();
                grade.setText("");
                specialization.setText("");
                database.refreshModelForClassLists(database.getListModelOfClasses(), database.getClassListsArray());
                break;
            default:
                break;
            }
        
        
       
    }
   
}
