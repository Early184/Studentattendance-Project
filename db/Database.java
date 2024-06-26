package db;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import Models.ClassList;
import Models.Schueler;

public class Database {
    private static Database instance;
    
    private ArrayList<Schueler> schuelerArray = new ArrayList<Schueler>();
    private ArrayList<Schueler> klassenArrayCreation = new ArrayList<Schueler>();

    private ArrayList<ClassList> classListsArray = new ArrayList<ClassList>();
    private JList<String> listOfClasses;
    private String studentFile = "db/Students.csv";
    private String classesFile = "db/ListOfClasses.csv";
    private DefaultListModel<String> listModelOfClasses;
    public ArrayList<Schueler> classArrayWrite;
    private int dailyCounter = 0; 
    
    public int addToDailyCounter(int additonOfHours){
        this.dailyCounter += additonOfHours;
        return dailyCounter;
    }
    public int getDailyCounter() {
        return dailyCounter;
    }
    public void setDailyCounter(int dailyCounter) {
        this.dailyCounter = dailyCounter;
    }
    public void setDailyCounterNull (){
        this.dailyCounter = 0;
    }
    private Database(){

    }
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    
    
    public JList<String> getListOfClasses() {
        return listOfClasses;
    }

    public DefaultListModel<String> getListModelOfClasses() {
        return listModelOfClasses;
    }
    
    public ArrayList<ClassList> getClassListsArray() {
        return classListsArray;
    }
    
    public void setClassListsArray(ArrayList<ClassList> classListsArray) {
        this.classListsArray = classListsArray;
    }
    
    
    
    public void readStudents(){
        String line = "";
        try{
            BufferedReader studentReader = new BufferedReader(new FileReader(studentFile));
            while((line = studentReader.readLine()) != null){
                String[] splittedName = line.split(";");
                if(splittedName.length == 2){
                    String vorname = splittedName[0].trim();
                    String nachname = splittedName[1].trim();
                    schuelerArray.add(new Schueler(vorname, nachname));
                } else{
                    System.out.println("Fehlerhaftes Format");
                }
            }
            studentReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    public void writeStudents(){
        
        try{
            FileWriter fileWriter = new FileWriter(studentFile);
            
            for(Schueler schueler: schuelerArray){
                fileWriter.write(schueler.getVorname()+";"+ schueler.getNachname()+";\r");
            }
           
            fileWriter.close();
        }catch(IOException e){
            
        }
    }
   
    public void readClass(){
        try{
            BufferedReader classReader = new BufferedReader(new FileReader(classesFile));
            classReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void writeClass(){
        try{
            FileWriter classWriter = new FileWriter(classesFile);
            classWriter.close();

        }catch(IOException e){
            
        }
    }
    public void readListOfClasses(){
        String line = "";
        try {
            BufferedReader readerListOfClasses = new BufferedReader(new FileReader(classesFile));
            while ((line = readerListOfClasses.readLine()) != null) {
                String[] splittedLine = line.split(";");
                String grade = splittedLine[0];
                String spec = splittedLine[1];
                ArrayList<Schueler> classArray = new ArrayList<>();
                int index = 2;
                while (index < splittedLine.length) {
                    String vorname = splittedLine[index];
                    String nachname = splittedLine[index + 1];
                    classArray.add(new Schueler(vorname, nachname));
                    index += 2;
                }
                
                classListsArray.add(new ClassList(grade, spec, classArray));
            }
            readerListOfClasses.close();
        } catch(IOException e) {
            System.err.println("Fehler beim Lesen");
        }
    }
    public void writeListOfClasses(){
        
        try{
            FileWriter writer = new FileWriter(classesFile);
            for(ClassList classList : classListsArray){
                //classArrayWrite = classList.getClassArray();
                ArrayList<Schueler> classArrayForWrite = new ArrayList<>(classList.getClassArray());
                writer.write(classList.getGrade() + ";"+ classList.getSpecialization() + ";");
                for (Schueler schueler : classArrayForWrite){
                    writer.write(schueler.getVorname() +";" +schueler.getNachname() + ";");
                }
                writer.write("\n");
                
            }
            writer.close();
        }catch(IOException e){
            
        }
        
    }
    public ArrayList<Schueler> showStudents(){
        return schuelerArray;
    }
    public ArrayList<Schueler> showClassArray(){
        return klassenArrayCreation;
    }
    public void addStudentToStudentArray(Schueler schueler){
        schuelerArray.add(schueler);
    }
    public void addStudentToClassArray(Schueler schueler){
        klassenArrayCreation.add(schueler);
    }

    public void deleteStudentFromStudentArray(int index){
        klassenArrayCreation.remove(index);
        
    }
    public void deleteStudentFromClass(int index){
        klassenArrayCreation.remove(index);
    }
    public void refreshModelForStudents(DefaultListModel<String> listModel, ArrayList<Schueler> updateArrayList){
        listModel.clear();
        for(Schueler Schueler : updateArrayList){
                listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
        };
    }
    public void refreshModelForClassLists(DefaultListModel<String> listModel, ArrayList<ClassList> updateArrayList){
        listModel.clear();
        for(ClassList classList : updateArrayList){
                listModel.addElement(classList.getSpecialization()+ "         "+ classList.getGrade());
        };
    }
    public JList<String> createJListForClasses(){
        
        readListOfClasses();

        listModelOfClasses = new DefaultListModel<String>();
        
        for(ClassList classList : getClassListsArray()){
            listModelOfClasses.addElement(classList.getSpecialization() + "         " + classList.getGrade());
        }
        listOfClasses = new JList<>(listModelOfClasses);
        
        listOfClasses.setBackground(Color.BLACK);
        listOfClasses.setForeground(Color.WHITE);
        listOfClasses.setPreferredSize(new Dimension(300,400));
        listOfClasses.setBorder(new EmptyBorder(10,10,10,10));

        

        return listOfClasses;
    }

    
}
    
