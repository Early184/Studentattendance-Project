package GUI.Components;

import javax.swing.JDialog;

public class ListCreationDialog extends JDialog{
    private MainFrame frame;
   
    public ListCreationDialog(MainFrame frameCon){
        this.frame = frameCon;
        
        setSize(800,600);
        setLocationRelativeTo(frame);
        setResizable(false);
        setModal(true);
       
        
        
        
    }
}
