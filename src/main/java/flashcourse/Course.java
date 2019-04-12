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
private Map<CalendarDate, String> lectureTimes;
private Map<CalendarDate, String> holidays;
private Map<CalendarDate, String> freeDays;
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

public Map<CalendarDate, String> getLectureDates(){
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
public void addLectureDates(CalendarDate lectureDate, String courseName) {
	lectureTimes.put(lectureDate, courseName);
}

/**
 * 
 * @param lectureName to be deleted
 * @param lectureDate that corresponds the lectureName to be deleted
 * @return true of false based on success of target lecture time deletion
 * 
 * Deletes target lecture time from LectureTimes collection
 */
public boolean deleteLectureTimes(CalendarDate lectureDate, String courseName) {
	boolean deleted = false;
	for(CalendarDate key : lectureTimes.keySet()) {
		if(lectureDate.equals(key)) {
			lectureTimes.remove(lectureDate);
			deleted = true;
		}
	}
	
	return deleted;
	
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
