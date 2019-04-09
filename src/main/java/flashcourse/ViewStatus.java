package main.java.flashcourse;

import main.java.memoranda.ui.*;

public class ViewStatus {
    boolean teacher;
    boolean student;
    boolean TA;
    
    
    public ViewStatus() {
        teacher = false;
        student = false;
        TA = false;
                
    }
    
    public boolean getTeacherStatus() {
        return teacher;
    }
    
    public boolean getStudentStatus() {
        return student;
    }
    
    public boolean getTAStatus() {
        return TA;
    }
    
    public void setTeacherStatus(boolean teacher) {
        this.teacher = teacher;
    }
    
    public void setStudentStatus(boolean student) {
        this.student = student;
    }
    
    public void setTAStatus(boolean TA) {
        this.TA = TA;
    }
}
