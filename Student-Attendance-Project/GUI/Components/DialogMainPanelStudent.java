package GUI.Components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import Models.Schueler;
import db.Database;



public class DialogMainPanelStudent extends JPanel implements ActionListener{
    public JTextField firstName;
    public JTextField lastName;
    private Database database = Database.getInstance();
    private DefaultListModel<String> listModel;
    private JList<String> showList;
    public CreationButtonPanel creationButtonPanel;
    
    public DialogMainPanelStudent(){
        //Top panel no extra class
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
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_ENTER){
                String firstName1 = firstName.getText();
                String lastName1= lastName.getText();
                System.out.println("Hallo");
                database.addStudent(new Schueler(firstName1, lastName1));
                    
                listModel.clear();
                for(Schueler Schueler : database.showStudents()){
                        listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
                };
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
    };
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
            

            if(key == KeyEvent.VK_ENTER){
                String firstName1 = firstName.getText();
                String lastName1= lastName.getText();
                System.out.println("Hallo");
                database.addStudent(new Schueler(firstName1, lastName1));
                    
                listModel.clear();
                for(Schueler Schueler : database.showStudents()){
                        listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
                };
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
    @Override
    public void actionPerformed(ActionEvent e) {
        String userchoice = e.getActionCommand();

        switch (userchoice) {
            case "saveStudent":
                String firstName1 = firstName.getText();
                String lastName1= lastName.getText();
        
                database.addStudent(new Schueler(firstName1, lastName1));
                
                listModel.clear();
                for(Schueler Schueler : database.showStudents()){
                    listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
                };
                

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
