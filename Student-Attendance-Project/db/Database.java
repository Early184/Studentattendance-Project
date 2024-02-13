package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import Models.Schueler;

public class Database {
    private static Database instance;
    
    private ArrayList<Schueler> schuelerArray = new ArrayList<Schueler>();
    private ArrayList<Schueler> klassenArray = new ArrayList<Schueler>();
    private ArrayList<ArrayList <Schueler>> klassenListeArray = new ArrayList<ArrayList <Schueler>>();
    private Database(){

    }
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public void readStudents(){
        try{
            BufferedReader studentReader = new BufferedReader(new FileReader("Students.csv"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    public void writeStudents(){
        
        try{
            FileWriter fileWriter = new FileWriter("db/Students.csv");
            for(Schueler schueler: schuelerArray){
                fileWriter.write(schueler.getVorname()+";"+ schueler.getNachname()+";\r");
            }
           
            fileWriter.close();
        }catch(IOException e){
            
        }
    }
   
    public void readClasses(){
        try{
            BufferedReader classReader = new BufferedReader(new FileReader("Classes.csv"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void writeClasses(){
        try{
            FileWriter classWriter = new FileWriter("Classes.csv");
        }catch(IOException e){
            
        }
    }
    public void readListOfClasses(){
        try{
            BufferedReader readerListOfClasses = new BufferedReader(new FileReader("ListOfClasses.csv"));
        }catch(IOException e){
            
        }
    }
    public void writeListOfClasses(){
        try{
            FileWriter writeListOfClasses = new FileWriter("ListOfClasses.csv");
        }catch(IOException e){
            
        }
    }
    public ArrayList<Schueler> showStudents(){
        return schuelerArray;
    }
    public void addStudent(Schueler schueler){
        schuelerArray.add(schueler);
    }
    public void deleteStudent(Schueler schueler){
        schuelerArray.remove(schueler);
    }
    public void refreshModel(DefaultListModel listModel, Database database){
        listModel.clear();
        for(Schueler Schueler : database.showStudents()){
                listModel.addElement(Schueler.getVorname()+ " "+ Schueler.getNachname());
        };
    }

    
}
    
