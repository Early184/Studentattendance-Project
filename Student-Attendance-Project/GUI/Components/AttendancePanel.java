package GUI.Components;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Controller.Controller;
import Models.ClassAttendance;
import Models.ClassList;
import Models.Schueler;
import db.Database;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AttendancePanel extends JDialog implements ActionListener{
    private JButton exportButton, closeButton;
    private JPanel titleAndButtonHolderPanel, buttonPanel, centerPanel, centerRightPanel, centerLeftPanel, southPanel;
    private JLabel titleLabel, presentLabel, absentLabel, counterLabel;
    private SwitchButtonWithPanel switchButtonPanel;
    private JRadioButton twoHours,fourHours,eightHours;
    private ButtonGroup buttonGroup;
    private JList<String> absentList, presentList;
    public DefaultListModel<String> absentListModel, presentListModel;
    private ArrayList<Schueler> selectedClass;
    private ClassAttendance classAttendance;
    private Database db;
    private Controller controller;
    
   

    public ClassAttendance getClassAttendance() {
        return classAttendance;
    }


    public AttendancePanel(DefaultListModel<String> listModel, ArrayList<Schueler> selectedClass){
        this.selectedClass = selectedClass;
        this.db = Database.getInstance();
        controller = new Controller(this);

        setSize(800, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setModal(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
       
       
       
        
        
        exportButton = new JButton("Exportieren");
        exportButton.setBackground(Color.BLACK);
        exportButton.setForeground(Color.WHITE);
        exportButton.setFocusPainted(false);
        exportButton.setActionCommand("Export");
        exportButton.addActionListener(this);

        closeButton = new JButton("Schließen");
        closeButton.setBackground(Color.BLACK);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setActionCommand("Close");
        closeButton.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.gray);
        buttonPanel.setLayout(new FlowLayout(1, 200, 10));
        buttonPanel.setPreferredSize(new Dimension(700,50));
        buttonPanel.add(exportButton);
        buttonPanel.add(closeButton);
        
        titleLabel = new JLabel("Anwesenheit prüfen");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", 1, 20));
        titleLabel.setSize(200, 50);

        titleAndButtonHolderPanel = new JPanel();
        titleAndButtonHolderPanel.setLayout(new FlowLayout(1 ,25, 15));
        titleAndButtonHolderPanel.setPreferredSize(new Dimension(0, 100));
        titleAndButtonHolderPanel.setBackground(Color.gray);

        //Panel for absentStudents
        centerLeftPanel = new JPanel();
        centerLeftPanel.setBackground(Color.darkGray);
        centerLeftPanel.setPreferredSize(new Dimension(300,0));
        centerLeftPanel.setLayout(new FlowLayout());
        absentLabel = new JLabel("Abwesend");
        absentLabel.setForeground(Color.WHITE);
        absentListModel = listModel;
        absentList = new JList<String>(absentListModel);
        absentList.setPreferredSize(new Dimension(290, 300));
        absentList.setBackground(Color.BLACK);
        absentList.setForeground(Color.WHITE);
        centerLeftPanel.add(absentLabel);
        centerLeftPanel.add(absentList);


        //Panel for present Students
        centerRightPanel = new JPanel();
        centerRightPanel.setBackground(Color.darkGray);
        centerRightPanel.setPreferredSize(new Dimension(300, 0));
        centerRightPanel.setLayout(new FlowLayout());
        presentLabel = new JLabel("Anwesend");
        presentLabel.setForeground(Color.WHITE);
        presentListModel = new DefaultListModel<String>();
        presentList = new JList<String>(presentListModel);
        presentList.setPreferredSize(new Dimension(290, 300));
        presentList.setBackground(Color.BLACK);
        presentList.setForeground(Color.WHITE);
        centerRightPanel.add(presentLabel);
        centerRightPanel.add(presentList);

        //MiddlePanel for switching students
        switchButtonPanel = new SwitchButtonWithPanel();
        switchButtonPanel.switchButton.setActionCommand("Switch");
        switchButtonPanel.switchButton.addActionListener(this);

        //Wrapper of lists etc.
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(centerLeftPanel, BorderLayout.WEST);
        centerPanel.add(centerRightPanel, BorderLayout.EAST);
        centerPanel.add(switchButtonPanel, BorderLayout.CENTER);

        southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(0, 100));
        southPanel.setBackground(Color.gray);
        southPanel.setLayout(new FlowLayout(1, 80, 50));

        //CounterLabel
        counterLabel = new JLabel(String.format("Anzahl Stunden: %d/8", db.getDailyCounter()));
        
        // Radio Buttons
        fourHours = new JRadioButton();
        fourHours.setText("4 Stunden");
        fourHours.setBackground(Color.BLACK);
        fourHours.setForeground(Color.WHITE);
        fourHours.setFocusPainted(false);
        twoHours = new JRadioButton();
        twoHours.setText("2 Stunden");
        twoHours.setBackground(Color.BLACK);
        twoHours.setForeground(Color.WHITE);
        twoHours.setFocusPainted(false);
        eightHours = new JRadioButton();
        eightHours.setText("8 Stunden");
        eightHours.setBackground(Color.BLACK);
        eightHours.setForeground(Color.WHITE);
        eightHours.setFocusPainted(false);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(twoHours);
        buttonGroup.add(fourHours);
        buttonGroup.add(eightHours);
        
        southPanel.add(twoHours);
        southPanel.add(fourHours);
        southPanel.add(eightHours);
        southPanel.add(counterLabel);

        titleAndButtonHolderPanel.add(titleLabel);
        titleAndButtonHolderPanel.add(buttonPanel);
        
        add(southPanel, BorderLayout.SOUTH);
        add(titleAndButtonHolderPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    
    public JList<String> getAbsentList() {
        return absentList;
    }


    public void setAbsentList(JList<String> absentList) {
        this.absentList = absentList;
    }


    public JList<String> getPresentList() {
        return presentList;
    }


    public void setPresentList(JList<String> presentList) {
        this.presentList = presentList;
    }


    public DefaultListModel<String> getAbsentListModel() {
        return absentListModel;
    }


    public void setAbsentListModel(DefaultListModel<String> absentListModel) {
        this.absentListModel = absentListModel;
    }


    public DefaultListModel<String> getPresentListModel() {
        return presentListModel;
    }


    public void setPresentListModel(DefaultListModel<String> presentListModel) {
        this.presentListModel = presentListModel;
    }
    public void queryForExports(){
        if(twoHours.isSelected() && db.getDailyCounter() < 8){
            db.addToDailyCounter(2);
            
            classAttendance = new ClassAttendance();
            
            for(int i = 0; i < presentListModel.getSize(); i++){
                String[] currentName = presentListModel.get(i).split(" ");
                classAttendance.getPresent().add(new Schueler(currentName[0], currentName[1]));
            }
            for(int i = 0; i < absentListModel.getSize(); i++){
                String[] currentName = absentListModel.get(i).split(" ");
                classAttendance.getAbsent().add(new Schueler(currentName[0], currentName[1]));
            }
            classAttendance.setHours(2);
            controller.writeAttendanceData();
        }
        else if(fourHours.isSelected() && db.getDailyCounter() < 8){
            
            if(db.getDailyCounter() + 4 > 8){
                JOptionPane.showMessageDialog(this, "Dies überschreitet die tägliche Stundenanzahl", "Meldung", JOptionPane.ERROR_MESSAGE);
                
            }else{
                db.addToDailyCounter(4);

                classAttendance = new ClassAttendance();
            
                for(int i = 0; i < presentListModel.getSize(); i++){
                    String[] currentName = presentListModel.get(i).split(" ");
                    classAttendance.getPresent().add(new Schueler(currentName[0], currentName[1]));
                }
                for(int i = 0; i < absentListModel.getSize(); i++){
                    String[] currentName = absentListModel.get(i).split(" ");
                    classAttendance.getAbsent().add(new Schueler(currentName[0], currentName[1]));
                }
                classAttendance.setHours(4);
                controller.writeAttendanceData();
            }
            
            
        }
        else if(eightHours.isSelected() && db.getDailyCounter() < 8){
            if(db.getDailyCounter()+ 8 > 8){
                JOptionPane.showMessageDialog(this, "Dies überschreitet die tägliche Stundenanzahl", "Meldung", JOptionPane.ERROR_MESSAGE);
                
            }
            else{
                db.addToDailyCounter(8); 

                classAttendance = new ClassAttendance();
            
                for(int i = 0; i < presentListModel.getSize(); i++){
                    String[] currentName = presentListModel.get(i).split(" ");
                    classAttendance.getPresent().add(new Schueler(currentName[0], currentName[1]));
                }
                for(int i = 0; i < absentListModel.getSize(); i++){
                    String[] currentName = absentListModel.get(i).split(" ");
                    classAttendance.getAbsent().add(new Schueler(currentName[0], currentName[1]));
                }
                classAttendance.setHours(8);
                controller.writeAttendanceData();
            }
            
            
        }
        counterLabel.setText(String.format("Anzahl Stunden: %d/8", db.getDailyCounter()));
        if(db.getDailyCounter() == 8){
            JOptionPane.showMessageDialog(this, "Die Datei für einen 8 Stunden Tag wurde nun fertig gestellt, fahren sie fort um eine weitere zu erstellen.", "Meldung", JOptionPane.INFORMATION_MESSAGE);
            db.setDailyCounterNull();
            counterLabel.setText(String.format("Anzahl Stunden: %d/8", db.getDailyCounter()));
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    
    
        String userSwitchStudent = e.getActionCommand();
        switch (userSwitchStudent) {
            case "Switch":
                //studentToClass
                if (absentList.getSelectedIndex() != -1 && presentList.getSelectedIndex() == -1){
                    presentListModel.addElement(absentList.getSelectedValue());
                    absentListModel.remove(absentList.getSelectedIndex());
                   
                }
                //StudentBackToStudentList
                if(absentList.getSelectedIndex() == -1 && presentList.getSelectedIndex() != -1 ){
                    absentListModel.addElement(presentList.getSelectedValue());
                    presentListModel.remove(presentList.getSelectedIndex());

                }
                break;
            case "Export" :
                queryForExports();
                
                
            case "Close" :

                break;
            default:
                break;
            }
    }
}
