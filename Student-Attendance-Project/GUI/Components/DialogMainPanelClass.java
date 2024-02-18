package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Schueler;
import db.Database;


public class DialogMainPanelClass extends JPanel {
    public JTextField grade;
    public JTextField specialization;
    private Database database;
    private DefaultListModel<String> listModelStudents, listModelClass;
    private JList<String> listOfStudents, classList;
    public DialogMainPanelClass(){
        this.database = Database.getInstance();
        //Top panel no extra class
        CreationButtonPanel creationButtonPanel = new CreationButtonPanel("Platzhalter");
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
        
        //gradePanel
        JLabel gradeLabel = new JLabel("Jahrgang:      ");
        grade = new JTextField();
        grade.setPreferredSize(new Dimension(120, 25));
        JPanel gradePanel = new JPanel();
        gradeLabel.setLayout(new FlowLayout());
        gradePanel.add(gradeLabel);
        gradePanel.add(grade);
        //grade.getText();  um String wert zu entziehen
        
        //specializationPanel
        JLabel specLabel = new JLabel("Fachbereich: ");
        specialization = new JTextField();
        specialization.setPreferredSize(new Dimension(120, 25));
        JPanel specPanel = new JPanel();
        specPanel.setLayout(new FlowLayout());
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
        studentListHolderPanel.setBackground(Color.black);
        studentListHolderPanel.setPreferredSize(new Dimension(300, 0));

        JPanel selectedStudents = new JPanel();
        selectedStudents.setBackground(Color.gray);
        selectedStudents.setPreferredSize(new Dimension(300, 0));

        mainListHolderPanel.setLayout(new BorderLayout());
        
        studentListHolderPanel.add(createStudentList());
        selectedStudents.add(createClassList());
        
        //Button with Icon
        SwitchButtonWithPanel switchPanel = new SwitchButtonWithPanel();

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
        return listOfStudents;
    }
    public JList<String> createClassList(){
        listModelClass = new DefaultListModel<>();

        for(Schueler Schueler : database.showClassArray()){
            listModelClass.addElement(Schueler.getVorname() + " " + Schueler.getNachname());
        }
        classList = new JList<String>(listModelClass);
        return classList;
    }
    public void addStudentToClass(){
        String[] studentName = classList.getSelectedValue().split(" ");
        database.addStudentToClassArray(new Schueler(studentName[0], studentName[1]));
    }
   
}
