package Models;

import java.util.ArrayList;

public class ClassList {
    String grade;
    String specialization;
    ArrayList<Schueler> classArray;
    public ClassList(String gradeCon, String specCon, ArrayList<Schueler> classCon){
        this.grade = gradeCon;
        this.specialization = specCon;
        this.classArray = classCon;
    }
    public ArrayList<Schueler> getClassArray() {
        return classArray;
    }
    public String getGrade() {
        return grade;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setClassArray(ArrayList<Schueler> classArray) {
        this.classArray = classArray;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
