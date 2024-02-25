package GUI.Components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleNamePanel extends JPanel {
    public TitleNamePanel(String title) {
        JLabel headline = new JLabel();
        headline.setForeground(Color.white);
        headline.setText(title);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(0, 50));
        add(headline);
    }
}
