/**
 * @author Jessica Tinaza 
 * 
 * File: Course.java
 * April 3, 2019
 * 
 */

package main.java.flashcourse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.java.memoranda.date.*;
import nu.xom.Attribute;
import nu.xom.Element;

/**
 * 
 * Class: Course
 * 
 * Provides all functionality to create and store Course objects. When created
 * each class is created with empty date fields as described by class member names, and
 * takes in the course name in its constructor.
 *
 */
public class Course {
private String courseName;
private CalendarDate courseStartDate;
private CalendarDate courseEndDate;
private CalendarDate finalExam;
private CalendarDate courseBreakStart;
private CalendarDate courseBreakEnd;
private ArrayList<Lecture> lectures;
private Map<CalendarDate, String> holidays;
private Map<CalendarDate, String> freeDays;
private ArrayList<Assignment> assignments;
private ArrayList<File> documents;
private Element _el;

/**
 * 
 * @param course the course name to be set during creation
 * 
 * Course constructor that takes in the name of a course 
 * and sets all dates and date collections as empty.
 */
public Course(String course) {
    
    courseName = course;
    courseStartDate = new CalendarDate();
    courseEndDate = new CalendarDate();
    finalExam = new CalendarDate();
    courseBreakStart = new CalendarDate();
    courseBreakEnd = new CalendarDate();
    lectures = new ArrayList<>();
    holidays = new HashMap<>();
    freeDays = new HashMap<>();
    assignments = new ArrayList<>();
    documents = new ArrayList<>();
    _el = null;
}

public String getCourseName() {
    return courseName;
}


public void setCourseName(String course) {
    courseName = course;
}

public CalendarDate getCourseStartDate() {
    return courseStartDate;
}

public void setCourseStartDate(CalendarDate start) {
    courseStartDate = start;
}


public CalendarDate getCourseEndDate() {
    return courseEndDate;
    
}

public void setCourseEndDate(CalendarDate end) {
    courseEndDate = end;
}


public CalendarDate getFinalExamDate() {
    return finalExam;
}



public void setFinalExamDate(CalendarDate fExam) {
    finalExam = fExam;
}

public ArrayList<Lecture> getLectureDates(){
    return lectures;
}


public CalendarDate getCourseBreakStart() {
    return courseBreakStart;
}


public void setCourseBreakStart(CalendarDate start) {
    courseBreakStart = start;
}


public CalendarDate getCourseBreakEnd() {
    return courseBreakEnd;
}


public void setCourseBreakEnd(CalendarDate end) {
    courseBreakEnd = end;
}

/**
 * 
 * @param lecture to be added to array list
 * 
 * Adds lecture times to the lectureTime collection
 */
public void addLecture(Lecture lecture) {
    if (!lectures.contains(lecture)) {
        lectures.add(lecture);
    }
}

/**
 * 
 * @param lecture to be deleted
 * @return true of false based on success of target lecture time deletion
 * 
 * Deletes target lecture time from lectures collection
 */
public boolean deleteLecture(Lecture lecture) {
    if (lectures.contains(lecture)) {
        lectures.remove(lecture);
        return true;
    }
    return false;
}

public Map<CalendarDate, String> getHolidayDates(){
    return holidays;
}

/**
 * 
 * @param holidayName of holiday to be added
 * @param holidayDate of holiday to be added
 * 
 * Adds holiday date to holidays collection
 */
public void addHolidayDates(CalendarDate holidayDate, String courseName) {
    holidays.put(holidayDate, courseName);
}

/**
 * 
 * @param holidayName of holiday to be deleted
 * @param holidayDate of holiday to be deleted
 * @return true of false based on target holiday deletion
 * 
 * Deletes target holiday date from holidays collection
 */
public boolean deleteHolidayDate(CalendarDate holidayDate, String courseName) {
    boolean deleted = false;
    for(CalendarDate key : holidays.keySet()) {
        if(holidayDate.equals(key)) {
            holidays.remove(holidayDate);
            deleted = true;
        }
    }
        
    return deleted;
}


public Map<CalendarDate, String> getfreeDays(){
    return freeDays;
}

/**
 * 
 * @param freeDayName to be added 
 * @param freeDayDate to be added
 * 
 * Adds free day date to freeDays collection
 */
public void addFreeDays(CalendarDate freeDayDate, String courseName) {
    freeDays.put(freeDayDate, courseName);
}

/**
 * 
 * @param freeDayName to be deleted
 * @param freeDayDate to be deleted
 * 
 * Deletes target free day from FreeDays collection
 */
public boolean deleteFreeDay(CalendarDate freeDayDate, String courseName) {
    boolean delete = false;
    for(CalendarDate key : freeDays.keySet()) {
        if(freeDayDate.equals(key)) {
            freeDays.remove(freeDayDate);
            delete = true;
        }
    }
    
    return delete;
}

public ArrayList<File> getDocuments(){
	return documents;
}

public boolean addDocument(File file) {
	boolean add = false;
	if(documents.size() == 0) {
		documents.add(file);
		return true;
	}
	
	if(!documents.contains(file)) {
		documents.add(file);
		add = true;
	}
	
	return add;
}

public boolean deleteDocument(File file) {
	boolean delete = false;
	if(documents.contains(file)) {
		documents.remove(file);
		delete = true;
	}
	
	return delete;
}

public ArrayList<Assignment> getAssignments(){
    return assignments;
}

/**
 * 
 * @param assign to be added
 * @return true or false depending on success of adding
 * to collection
 * 
 * Adds assignment to assignments collection
 */
public boolean addAssignment(Assignment assign) {

	boolean add = false;
	if(assignments.size() == 0) {
	    assignments.add(assign);
	    return true;
	}
	
		if(!(assignments.contains(assign))){
			assignments.add(assign);
			add = true;
		
	}
	System.out.println("[DEBUG] "+this.courseName);
	System.out.println("[DEBUG] "+assign.getName());	
	System.out.println("[DEBUG] "+assign.getDescription());	
	return add;
	

}

/**
 * 
 * @param assign to be deleted
 * @return true or false based on target assignment deletion
 * 
 * Deletes target assignment from assignments collection
 */
public boolean deleteAssignment(Assignment assign) {
	boolean delete = false;
	
		if((assignments.contains(assign))) {
			assignments.remove(assign);
			delete = true;
		
	}
	
	return delete;

}

/**
 * @return string value of the courseName 
 * 
 * Used in order to have proper display of courseName in the course selection module of GUI
 */
@Override
public String toString() {
    return courseName;
}

public boolean isMarked() {
   
        return _el.getAttribute("course") != null;        
    
  
}

public void setMark(boolean mark) {
    Attribute ma = _el.getAttribute("course");        
    if (ma == null) {
        if (mark)
            _el.addAttribute(new Attribute("course", "yes"));
        return;
    }
    else if (!mark)
        _el.removeAttribute(ma);
}


}