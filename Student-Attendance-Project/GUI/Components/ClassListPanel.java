package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import db.Database;

public class ClassListPanel extends JPanel {
    
    
    Database database;
    public ClassListPanel() {
        this.database = Database.getInstance();
        JPanel holderPanel = new JPanel();
        holderPanel.setPreferredSize(new Dimension(400, 400));
        holderPanel.setBackground(Color.gray);
        holderPanel.add(database.createJListForClasses());
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setPreferredSize(new Dimension(400,500));
        add(holderPanel,BorderLayout.CENTER);


       

        
       
       
        
        

    }
    

}
