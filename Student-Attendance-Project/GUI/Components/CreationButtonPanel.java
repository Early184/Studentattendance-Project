package GUI.Components;


import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class CreationButtonPanel extends JPanel {
    JButton saveButton;
    JButton closeButton;
    JButton deleteButton;
    public CreationButtonPanel(String actionCommand){
        
        saveButton = new JButton("Save");
        saveButton.setFocusPainted(false);
        saveButton.setActionCommand(actionCommand);
        saveButton.setBackground(Color.black);
        saveButton.setForeground(Color.WHITE);
        
        deleteButton = new JButton("Delete");
        deleteButton.setFocusPainted(false);
        deleteButton.setActionCommand("delete");
        deleteButton.setVisible(false);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.black);
  
        closeButton = new JButton("Close");
        closeButton.setActionCommand("close");
        closeButton.setFocusPainted(false);
        closeButton.setBackground(Color.black);
        closeButton.setForeground(Color.WHITE);
        
        setBackground(Color.DARK_GRAY);
        add(saveButton);
        add(deleteButton);
        add(closeButton);
        setLayout(new FlowLayout(1,150,0));
        setVisible(true);
        
        
        
    }

   
   
}
