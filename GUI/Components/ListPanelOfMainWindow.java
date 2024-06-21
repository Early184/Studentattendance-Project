package GUI.Components;

import java.awt.BorderLayout;


import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;


import db.Database;

public class ListPanelOfMainWindow extends JPanel {
    DefaultListModel<String> listModelForClasses;
    JList<String> listOfClasses;
    Database database;
    public ListPanelOfMainWindow(){
        this.database = Database.getInstance();
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());

        //JTextField testField = new JTextField();
        //testField.setPreferredSize(new Dimension(400,350));
        //add(testField, BorderLayout.NORTH);
        listModelForClasses = new DefaultListModel<String>();
        listOfClasses = new JList<String>(listModelForClasses);
        mainPanel.add(listOfClasses);
        
        

        setVisible(true);
    }
}
