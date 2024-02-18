package GUI.Components;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SwitchButtonWithPanel extends JPanel{
    public SwitchButtonWithPanel(){
        setPreferredSize(new Dimension(200, 390));
        ImageIcon switchImage = new ImageIcon("Student-Attendance-Project/GUI/Images/doppelPfeil.png");
        ImageIcon scaledImage = imageScaler(switchImage, 140, 100);
        
        JButton switchButton = new JButton();
        switchButton.setIcon(scaledImage);
        switchButton.setBorderPainted(false);
        switchButton.setFocusPainted(false);
        switchButton.setContentAreaFilled(false);
        //place Button in the middle
        int x = (getWidth() - switchButton.getWidth()) / 2;
        int y = (getHeight() - switchButton.getHeight()) /2;
        switchButton.setLocation(x, y);
       
        
        add(switchButton);
    }
    public ImageIcon imageScaler(ImageIcon image, int width, int height){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    public void buttonLocation(JButton button){
        
    }
}
