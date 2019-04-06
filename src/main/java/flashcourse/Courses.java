package main.java.flashcourse;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.HashMap;

/**
 * Class to hold a collection of Courses and manage changes to the collection
 * @author Caleb Schafer
 * File: Courses.java
 * 5 April 2019
 */
public class Courses {
	private ArrayList<Course> courses;
	
	/**
	 * Ctor to create an empty collection
	 */
	public Courses() {
		courses = new ArrayList<Course>();
	}
	
	/**
	 * Ctor to create a collection with an initial course
	 * @param course
	 */
	public Courses(Course course) {
		courses = new ArrayList<Course>();
		courses.add(course);
	}
	
	/**
	 * Getter for the collection of Course
	 * @return List of Course
	 */
	public ArrayList<Course> getCourses() {
		return this.courses;
	}
	
	/**
	 * Add a Course to the collection
	 * @param course the Course to be added
	 * @return true if the Course doesn't already exist in the collection, and can be added
	 */
	public boolean addCourse(Course course) {
		if(courses.contains(course)) {
			return false;
		}
		else {
			courses.add(course);
			return true;
		}
	}
	
	/**
	 * Create a new empty Course and add it to the collection
	 * @param name The name of the Course to be created and added
	 * @return true if successful, false if a course of that name already exists
	 */
	public boolean addCourse(String name) {
		if(this.hasCourse(name)) {
			return false;
		}
		Course course = new Course(name);
		this.addCourse(course);
		return true;
	}
	
	
	/**
	 * Check if the collection has a Course with the matching name
	 * @param name Name to search for
	 * @return true if the course is contained
	 */
	public boolean hasCourse(String name) {
		for(Course c : courses) {
			if(c.getCourseName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if a specific Course object is in the collection
	 * @param course The Course to check for
	 * @return true if the Course is in the collection
	 */
	public boolean hasCourse(Course course) {
		if(courses.contains(course)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Removes a Course from the collection based on the name of the course
	 * returns null if the course doesn't exist
	 * @param name Name of the course to be removed
	 * @return the course that has been removed
	 */
	public Course removeCourse(String name) {
		for(Course c : courses) {
			if(c.getCourseName().equals(name)) {
				Course temp = c;
				courses.remove(c);
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Removes a specific Course object from the collection
	 * @param course
	 * @return 
	 */
	public boolean removeCourse(Course course) {
		if(courses.contains(course)) {
			courses.remove(course);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Create an iterator for the list of Course
	 * @return iterator
	 */
	public Iterator<Course> getCoursesIterator() {
		return this.courses.iterator();
	}
	
	
	
}
