package main.java.memoranda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.memoranda.date.*;
public class Course {
private CalendarDate courseStartDate;
private CalendarDate courseEndDate;
private CalendarDate finalExam;
private CalendarDate courseBreakStart;
private CalendarDate courseBreakEnd;
private Map<String, CalendarDate> lectureTimes;
private Map<String, CalendarDate> holidays;
private Map<String, CalendarDate> freeDays;
//private ArrayList<Assignments> assignments;


public Course() {
	courseStartDate = new CalendarDate();
	courseEndDate = new CalendarDate();
	finalExam = new CalendarDate();
	courseBreakStart = new CalendarDate();
	courseBreakEnd = new CalendarDate();
	lectureTimes = new HashMap<>();
	holidays = new HashMap<>();
	freeDays = new HashMap<>();
	//assignments = new ArrayList<>();
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

public void addLectureTimes(String lectureName, CalendarDate lectureDate) {
	lectureTimes.put(lectureName, lectureDate);
}

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

public void addHolidayDates(String holidayName, CalendarDate holidayDate) {
	lectureTimes.put(holidayName, holidayDate);
}

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

public void addfreeDays(String freeDayName, CalendarDate freeDayDate) {
	freeDays.put(freeDayName, freeDayDate);
}

public void deleteFreeDay(String freeDayName, CalendarDate freeDayDate) {
	for(String key : freeDays.keySet()) {
		if(freeDayName.equals(key)) {
			lectureTimes.remove(freeDayName);
		}
	}
		
	
}

//public ArrayList<Assignments> getAssignments(){
//	return assignments;
//}

//public boolean addAssignment(Assignment assign) {
//	boolean add = false;
//	for(int i = 0; i < assignments.length(); i++) {
//		if(!(assignments.get(i).contains(assign))) {
			//assignemnts.add(assign);
//			add = true;
//		}
//	}
//	return add;
//	
//}

//public boolean deleteAssignment(Assignment assign) {
//	boolean delete = false;
//	for(int i = 0; i < assignments.length(); i++) {
//		if((assignments.get(i).contains(assign))) {
//			delete = true;
//			assignments.remove(assign);
//		}
//	}
//	
//	return delete;
//}


}
