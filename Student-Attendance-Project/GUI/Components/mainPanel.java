package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;



public class mainPanel extends JPanel{
    private MainFrame frame;
    public mainPanel(MainFrame frame) {
        this.frame = frame;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        TitleNamePanel title = new TitleNamePanel("Hauptmenü");
        add(title, BorderLayout.NORTH);
        ButtonPanel buttonPanel = new ButtonPanel(frame);
        add(buttonPanel,BorderLayout.CENTER);
        
        
        
        ClassListPanel classListPanel = new ClassListPanel();
        add(classListPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    
    
}
