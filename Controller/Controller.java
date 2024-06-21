package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import GUI.Components.AttendanceWindow;
import GUI.Components.MainFramePanel;
import Models.Schueler;
import db.Database;

public class Controller {
    private AttendanceWindow aPanel;
    private MainFramePanel mainPanel;
    private Database db;
    private LocalDate currentDate;
    private LocalTime currentTime;
    private String formattedTime;
    private String formattedDate;
   
    public Controller (AttendanceWindow aPanel, MainFramePanel mainPanel){
        this.aPanel = aPanel;
        this.db = Database.getInstance();
        this.mainPanel = mainPanel;
    }
    
    public void formatCurrentDateAndTime(){
        currentDate = LocalDate.now();
        currentTime = LocalTime.now(ZoneId.of("Europe/Berlin"));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");

        formattedDate = currentDate.format(dateFormatter);
        formattedTime = currentTime.format(timeFormatter);
    }
    public void writeAttendanceData(){
        formatCurrentDateAndTime();
        
        String curClass = mainPanel.classListPanel.JListOfClasses.getSelectedValue().replaceAll("\\s+", " ");
        String currentData = String.format("db/%s,%s Uhr.csv",  formattedDate, formattedTime);
        try {
            BufferedWriter bW = new BufferedWriter(new FileWriter(currentData,true));
            BufferedReader bR = new BufferedReader(new FileReader(currentData));
            if(bR.readLine() == null){
                String content = String.format(String.format("Anwesenheit am; %s;\n", formattedDate));
                bW.write(content);
            }
            bW.write(String.format("Klasse;%s;   ;%d Stunden\n", curClass, aPanel.getClassAttendance().getHours()));
            bR.close();
            bW.write("Anwesend;Vorname;Nachname\n");
            for(Schueler schueler : aPanel.getClassAttendance().getPresent()){
                bW.write(String.format("        ;%s;%s\n", schueler.getVorname(),schueler.getNachname()));
            }
            bW.write("Abwesend:\n");
            for(Schueler schueler : aPanel.getClassAttendance().getAbsent()){
                bW.write(String.format("        ;%s;%s\n", schueler.getVorname(),schueler.getNachname()));
            }
            bW.write("\n");
            bW.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
