package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DialogListFields extends JPanel {
    public DialogListFields(){
        
        JPanel mainPanel = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());

        JTextField testField = new JTextField();
        testField.setPreferredSize(new Dimension(400,350));
        add(testField, BorderLayout.NORTH);
        
        setVisible(true);
    }
}
