/**
 * @author Jessica Tinaza 
 * 
 * File: Course.java
 * April 3, 2019
 * 
 */

package main.java.flashcourse;

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
private Map<String, CalendarDate> lectureTimes;
private Map<String, CalendarDate> holidays;
private Map<String, CalendarDate> freeDays;
private ArrayList<Assignment> assignments;
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
	lectureTimes = new HashMap<>();
	holidays = new HashMap<>();
	freeDays = new HashMap<>();
	assignments = new ArrayList<>();
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

public Map<String, CalendarDate> getLectureDates(){
	return lectureTimes;
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
 * @param lectureName of the lecture that is to be added
 * @param lectureDate of the lecture name that is to be added
 * 
 * Adds lecture times to the lectureTime collection
 */
public void addLectureTimes(String lectureName, CalendarDate lectureDate) {
	lectureTimes.put(lectureName, lectureDate);
}

/**
 * 
 * @param lectureName to be deleted
 * @param lectureDate that corresponds the lectureName to be deleted
 * @return true of false based on success of target lecture time deletion
 * 
 * Deletes target lecture time from LectureTimes collection
 */
public boolean deleteLectureTimes(String lectureName, CalendarDate lectureDate) {
	boolean deleted = false;
	for(String key : lectureTimes.keySet()) {
		if(lectureName.equals(key)) {
			lectureTimes.remove(lectureName);
			deleted = true;
		}
	}
	
	return deleted;
	
}

public Map<String, CalendarDate> getHolidayDates(){
	return holidays;
}

/**
 * 
 * @param holidayName of holiday to be added
 * @param holidayDate of holiday to be added
 * 
 * Adds holiday date to holidays collection
 */
public void addHolidayDates(String holidayName, CalendarDate holidayDate) {
	holidays.put(holidayName, holidayDate);
}

/**
 * 
 * @param holidayName of holiday to be deleted
 * @param holidayDate of holiday to be deleted
 * @return true of false based on target holiday deletion
 * 
 * Deletes target holiday date from holidays collection
 */
public boolean deleteHolidayDate(String holidayName, CalendarDate holidayDate) {
	boolean deleted = false;
	for(String key : holidays.keySet()) {
		if(holidayName.equals(key)) {
			holidays.remove(holidayName);
			deleted = true;
		}
	}
		
	return deleted;
}


public Map<String, CalendarDate> getfreeDays(){
	return freeDays;
}

/**
 * 
 * @param freeDayName to be added 
 * @param freeDayDate to be added
 * 
 * Adds free day date to freeDays collection
 */
public void addfreeDays(String freeDayName, CalendarDate freeDayDate) {
	freeDays.put(freeDayName, freeDayDate);
}

/**
 * 
 * @param freeDayName to be deleted
 * @param freeDayDate to be deleted
 * 
 * Deletes target free day from FreeDays collection
 */
public boolean deleteFreeDay(String freeDayName, CalendarDate freeDayDate) {
	boolean delete = false;
	for(String key : freeDays.keySet()) {
		if(freeDayName.equals(key)) {
			freeDays.remove(freeDayName);
			delete = true;
		}
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
	for(int i = 0; i < assignments.size(); i++) {
		if(!(assignments.get(i).equals(assign))) {
			assignments.add(assign);
			add = true;
		}
	}
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
	for(int i = 0; i < assignments.size(); i++) {
		if((assignments.get(i).equals(assign))) {
			delete = true;
			assignments.remove(assign);
		}
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
   
        return _el.getAttribute("courses") != null;        
    
  
}

public void setMark(boolean mark) {
    Attribute ma = _el.getAttribute("courses");        
    if (ma == null) {
        if (mark)
            _el.addAttribute(new Attribute("courses", "yes"));
        return;
    }
    else if (!mark)
        _el.removeAttribute(ma);
}


}
