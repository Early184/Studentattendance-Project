package GUI.Components;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;




public class SwitchButtonWithPanel extends JPanel {
    
    ImageIcon scaledImage;
    JButton switchButton;
    ImageIcon scaledImageHover;
    ImageIcon scaledImagePressed;

    public SwitchButtonWithPanel(){
        
        
        
        scaledImage = imageScaler(new ImageIcon("GUI/Images/ButtonSwitch.png"), 140, 100);
        scaledImageHover = imageScaler(new ImageIcon("GUI/Images/ButtonHovered.png"),140, 100);
        scaledImagePressed = imageScaler(new ImageIcon("GUI/Images/ButtonPressed.png"),140, 100);
        
        
        switchButton = new JButton();
        switchButton.setIcon(scaledImage);
        switchButton.setBorderPainted(false);
        switchButton.setFocusPainted(false);
        switchButton.setContentAreaFilled(false);
        switchButton.addMouseListener(switchButtonAdapter);
        
        switchButton.setActionCommand("switch");
        
        //place Button in the middle
        //int x = (getWidth() - switchButton.getWidth()) / 2;
        //int y = (getHeight() - switchButton.getHeight()) /2;
        
       
        setBackground(Color.DARK_GRAY);
        add(switchButton);
        //switchButton.setLocation(x, y);
    }
    public ImageIcon imageScaler(ImageIcon image, int width, int height){
        Image originalImage = image.getImage();

        int newWidth = width;
        int newHeight = height;

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }
    
    MouseAdapter switchButtonAdapter = new MouseAdapter() {
        @Override
            public void mousePressed(MouseEvent e){
                switchButton.setIcon(scaledImagePressed);
            };

        @Override
            public void mouseReleased(MouseEvent e){
                switchButton.setIcon(scaledImageHover);
            };

           
        @Override
            public void mouseEntered(MouseEvent e){
                switchButton.setIcon(scaledImageHover);
            };

        @Override
            public void mouseExited(MouseEvent e){
                switchButton.setIcon(scaledImage);
            };
    };

    

}
