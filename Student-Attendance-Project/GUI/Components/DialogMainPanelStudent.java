package GUI.Components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Models.Schueler;
import db.Database;



public class DialogMainPanelStudent extends JPanel implements ActionListener{
    public JTextField firstName;
    public JTextField lastName;
<<<<<<< HEAD
    private Database database;
    private DefaultListModel<String> listModel;
    private JList<String> showList;
=======
    private final Database database = Database.getInstance();
    private final DefaultListModel<String> listModel;
    private final JList<String> showList;
>>>>>>> origin/main
    public CreationButtonPanel creationButtonPanel;
    
    public DialogMainPanelStudent(){
        //Top panel no extra class
    this.database = Database.getInstance();

    creationButtonPanel = new CreationButtonPanel("saveStudent");
    creationButtonPanel.saveButton.addActionListener(this);

    


    creationButtonPanel.deleteButton.addActionListener(this);
    creationButtonPanel.deleteButton.setVisible(true);
   
        
    TitleNamePanel titleCreateList = new TitleNamePanel("Studenten anlegen");
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new GridLayout(2,1));
    northPanel.add(titleCreateList);
    northPanel.add(creationButtonPanel);
    
    
    
    KeyListener enterSave = new KeyListener() {
        @Override
        public void keyReleased(KeyEvent e){
            
        }
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_ENTER) {
                String firstName1 = firstName.getText().trim();
                String lastName1 = lastName.getText().trim();

                // Abfrage Blank / Whitespace / Number
                if (firstName1.isBlank() || lastName1.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter a first AND a last name", " ", JOptionPane.INFORMATION_MESSAGE);

                } else if (containsSpaceOrNumbers(firstName1) || containsSpaceOrNumbers(lastName1)){
                    JOptionPane.showMessageDialog(null, "No spaces, numbers or special characters allowed", " ", JOptionPane.INFORMATION_MESSAGE);
                }

                else {
                    database.addStudent(new Schueler(firstName1, lastName1));

                    listModel.clear();
                    for (Schueler schueler : database.showStudents()) {
                        listModel.addElement(schueler.getVorname() + " " + schueler.getNachname());
                    }
                }
            }
        }
        @Override
        public void keyTyped(KeyEvent e){
            
        }

    };
    //nicht funktionell
    
    setLayout(new BorderLayout());
    add(northPanel ,BorderLayout.NORTH);
    
    //add(creationButtonPanel,BorderLayout.CENTER);
    
    //CenterPanel for Textfields
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout());
    
    //firstNamePanel
    JLabel firstNameLabel = new JLabel("Vorname:      ");
    firstName = new JTextField();
    firstName.addKeyListener(enterSave);
    firstName.setPreferredSize(new Dimension(120, 25));
    
    JPanel firstNamePanel = new JPanel();
    firstNameLabel.setLayout(new FlowLayout());
    firstNamePanel.add(firstNameLabel);
    firstNamePanel.add(firstName);
    //grade.getText();  um String wert zu entziehen
    
    //lastNamePanel
    JLabel lastNameLabel = new JLabel("Nachname:   ");
    lastName = new JTextField();
    lastName.addKeyListener(enterSave);
    lastName.setPreferredSize(new Dimension(120, 25));
    JPanel lastNamePanel = new JPanel();
    lastNamePanel.setLayout(new FlowLayout());
    lastNamePanel.add(lastNameLabel);
    lastNamePanel.add(lastName);
    //Hier das selbe
    
    centerPanel.add(firstNamePanel, BorderLayout.NORTH);
    centerPanel.add(lastNamePanel, BorderLayout.SOUTH);
    add(centerPanel, BorderLayout.CENTER);

    //ListPanel
    listModel = new DefaultListModel<String>();
    for(Schueler Schueler : database.showStudents()){
        listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
    }
        JPanel listHolderPanel = new JPanel();
    showList = new JList<String>(listModel);
    showList.addKeyListener(new KeyListener() {
        @Override
        public void keyReleased(KeyEvent e){
            
        }
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_DELETE){
                
                database.showStudents().remove(showList.getSelectedIndex());
                database.refreshModel(listModel, database);
            }
        }
        @Override
        public void keyTyped(KeyEvent e){
            
        }

    });
 
    showList.setPreferredSize(new Dimension(400,350));
    showList.setBackground(Color.white);
   
    showList.setVisible(true);
    listHolderPanel.add(showList);
    listHolderPanel.setPreferredSize(new Dimension(400, 390));
    
    listHolderPanel.setVisible(true);
    add(listHolderPanel, BorderLayout.SOUTH);

    setVisible(true);

    }
    //Boolean checking for WhiteSpace or Numbers
    private boolean containsSpaceOrNumbers(String str) {
        for (char c : str.trim().toCharArray()) {
            if (Character.isWhitespace(c) || Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String userchoice = e.getActionCommand();

        switch (userchoice) {
            case "saveStudent":
                String firstName1 = firstName.getText();
                String lastName1= lastName.getText();

                //Abfrage Blank / WhiteSpace / Numbers
                if (firstName1.isBlank() || lastName1.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Error: Please enter a first AND a last name", " ", JOptionPane.INFORMATION_MESSAGE);

                } else if (containsSpaceOrNumbers(firstName1) || containsSpaceOrNumbers(lastName1)){
                    JOptionPane.showMessageDialog(null, "No spaces, numbers or special characters allowed", " ", JOptionPane.INFORMATION_MESSAGE);
                }

                else {
                    database.addStudent(new Schueler(firstName1, lastName1));

                    listModel.clear();
                    for (Schueler schueler : database.showStudents()) {
                        listModel.addElement(schueler.getVorname() + " " + schueler.getNachname());
                    }
                }


                

                break;
            case "delete":
                int selectedStudent = showList.getSelectedIndex();
                if(selectedStudent != -1){
                    database.showStudents().remove(selectedStudent);
                    
                }
                
                database.refreshModel(listModel, database);
                break;
            case "close":
                
                break;
        }
    }
    
}
