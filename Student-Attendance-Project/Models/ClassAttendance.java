package Models;

import java.util.ArrayList;

public class ClassAttendance {
    private ArrayList<Schueler> present;
    private ArrayList<Schueler> absent;
    private int hours;
    
    public ArrayList<Schueler> getPresent() {
        return present;
    }
    public void setPresent(ArrayList<Schueler> present) {
        this.present = present;
    }
    public ArrayList<Schueler> getAbsent() {
        return absent;
    }
    public void setAbsent(ArrayList<Schueler> absent) {
        this.absent = absent;
    }
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    
    
}
