package main.java.flashcourse;

import java.util.Collection;
import java.util.Vector;

import main.java.memoranda.CourseListener;

public class CurrentCourse {

	private static Course currentCourse = null;
    private static Vector courseListeners = new Vector();

    public static Course get() {
        return currentCourse;
    }

    public static void set(Course course, boolean toSaveCurrentNote) {
        courseChanged(course, toSaveCurrentNote);
        currentCourse = course;
    }

    public static void reset() {
//    	 set toSave to true to mimic status quo behaviour only. the appropriate setting could be false
        set(null, true);
    }

    public static void addCourseListener(CourseListener nl) {
        courseListeners.add(nl);
    }

    public static Collection getChangeListeners() {
        return courseListeners;
    }

    private static void courseChanged(Course course, boolean toSaveCurrentNote) {
        for (int i = 0; i < courseListeners.size(); i++) {
            ((CourseListener)courseListeners.get(i)).courseChange(course,toSaveCurrentNote);
		}
    }
}
