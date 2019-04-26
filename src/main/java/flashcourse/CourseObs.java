package main.java.flashcourse;

import java.util.ArrayList;

public class CourseObs {
	private static String currentS;
	private static Course currentC;
	private ArrayList panels;
	//private WorkPanel;
	//private static Courses allCourses;
	private static CourseObs instance = null;
	
	
	private CourseObs() {
		currentS = "";
		//add more 
	}
	
	//making it a singleton
	public static CourseObs getCourseObs() {
		if(instance == null) {
			return new CourseObs();
		}
		else {
			return instance;
		}
	}
	/**
	 * returns  the current Course for use
	 * @return
	 */
	public static Course getCourse() {
		return currentC;
	}
	
	/**
	 * returns the name of the current course
	 * @return
	 */
	public static String getString() {
		return currentS;
	}
	
	public static void update(Course c) {
		currentC = c;
	}
	
	public static void update(String s) {
		currentS = s;
	}
	
	
	//could use to notify panels that the course has changed
//	public static void notifyPanels() {
//		
//	}
	
}

