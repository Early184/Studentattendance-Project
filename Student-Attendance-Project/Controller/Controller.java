package Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import GUI.Components.AttendancePanel;
import GUI.Components.mainPanel;
import Models.Schueler;
import db.Database;
import main.main;

public class Controller {
    private AttendancePanel aPanel;
    private mainPanel mainPanel;
    private Database db;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private String formattedTime;
    private String formattedDate;
   

    public Controller (AttendancePanel aPanel){
        this.aPanel = aPanel;
        this.db = Database.getInstance();
    }
    public Controller (mainPanel mainPanel){
        this.mainPanel = mainPanel;
        this.db = Database.getInstance();
    }
    public void formatCurrentDateAndTime(){
        currentDate = LocalDate.now();
        currentTime = LocalTime.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        formattedDate = currentDate.format(dateFormatter);
        formattedTime = currentTime.format(timeFormatter);
    }
    public void writeAttendanceData(){
        formatCurrentDateAndTime();
        try {
            BufferedWriter bW = new BufferedWriter(new FileWriter(String.format("Student-Attendance-Project/db/%s.csv", formattedDate)));
            bW.write(String.format("Tagesliste ;für Anwesenheit; am %s;\rKlasse 1:;%s;   ; %d", formattedDate, mainPanel.classListPanel.JListOfClasses.getSelectedValue(), aPanel.getClassAttendance().getHours()));
            bW.write("Anwesend:;\r  ;Vorname;Nachname");
            for(Schueler schueler : aPanel.getClassAttendance().getPresent()){
                bW.write(String.format("  ;%s;%s\r", schueler.getVorname(),schueler.getNachname()));
            }
            bW.write("Abwesend:;\r  ;Vorname;Nachname");
            for(Schueler schueler : aPanel.getClassAttendance().getAbsent()){
                bW.write(String.format("  ;%s;%s\r", schueler.getVorname(),schueler.getNachname()));
            }
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
