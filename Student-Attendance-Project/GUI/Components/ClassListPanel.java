package GUI.Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import GUI.Fonts;
import Models.Schueler;
import Models.Schuelerliste;

public class ClassListPanel extends JPanel {
    
    JList<String> showList;

    public ClassListPanel() {
        JPanel holderPanel = new JPanel();
        holderPanel.setBounds(0,0 ,400,400 );
        holderPanel.setBackground(Color.white);
        
        //Eine Klasse Namens AWE mit einem Array
        Schuelerliste studentList = new Schuelerliste("AWE", new ArrayList<Schueler>());
        Schuelerliste studentList2 = new Schuelerliste("AWE2", new ArrayList<Schueler>());
        Schuelerliste studentList3 = new Schuelerliste("AWE3", new ArrayList<Schueler>());
        //Liste für die Klassen
        ArrayList<Schuelerliste> SchulListe = new ArrayList<Schuelerliste>();
        SchulListe.add(studentList3);
        SchulListe.add(studentList2);
        SchulListe.add(studentList);
        //Default list model wird für die übergabe an eine JList gebraucht
        DefaultListModel<String> listModel = new DefaultListModel<>();
        setBackground(Color.white);
        setPreferredSize(new Dimension(400,670));
        
        for(Schuelerliste schuelerliste : SchulListe){
            listModel.addElement(schuelerliste.name);
        }
        JPanel JListHolderPanel= new JPanel();
        JList<String> showList = new JList<String>(listModel);
        showList.setFont(Fonts.Font1);
        
        showList.setSize(600,600);
        
        JListHolderPanel.setPreferredSize(new Dimension(600, 600));
        JListHolderPanel.add(showList);


        holderPanel.add(JListHolderPanel,BorderLayout.NORTH);
        add(holderPanel);

        
       
       
        /*JTextField classA = new JTextField();
        classA.setSize(200,0);
        classA.setHorizontalAlignment(SwingConstants.CENTER);
        JTextField classB = new JTextField();
        classB.setSize(200,0);;
        classB.setHorizontalAlignment(SwingConstants.CENTER);
        
        JTextArea classListArea = new JTextArea();
        classListArea.setEditable(false);
        classListArea.setPreferredSize(new Dimension(1000, 680));
        classListArea.setLayout(new GridLayout(30,1));
        classListArea.add(classA);
        classListArea.add(classB);
        
        JScrollPane scrollPane = new JScrollPane(classListArea);
        
        
        add(scrollPane);*/
        

    }
}
