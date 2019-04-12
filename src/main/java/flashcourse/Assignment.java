/*
 * File: Assignment.java
 * Author: Ryan Bohorquez
 * Date:4/3/2019
 * Description: This file is used for the creation and storing of assignments. The assignment class holds all the
 *  information for a class, and the ASSIGNEDGROUP enum is used determine who an assignment is for. 
 */

package main.java.flashcourse;

import main.java.memoranda.date.*;

/**
 * Class: Assignment
 * Description: Assignment extends all the functionality from Task and adds the ability to store a course
 *  as well as modify its value. By default an assignment needs to be created with a course in mind, but it
 *  can be removed and have null for its course.
 */
public class Assignment extends Task{

	//Member variables
	private Course course;
	
	/*
	 *Constructor for the Assignment class, intializes local variables based on parameters and calls addAssignment on the passed course
	 *
	 *@param course the Course to be added to
	 *@param dueDate the CalenderDate to set as the dueDate
	 *@param assignedGroup the ASSIGNEDGROUP this task is assigned to
	 *@param name the name of this assignment for printing purposes.
	 */
	public Assignment(Course course, CalendarDate dueDate, ASSIGNEDGROUP assignedGroup, String name, String description) {
		super(dueDate, assignedGroup, name, description);
		setCourse(course);
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	public void setCourse(Course newCourse) {
		//If this assignment already has a class assigned, remove this assignment from that classes assignment list.
		this.removeCourse();
		this.course = newCourse;
		newCourse.addAssignment(this);
	}
	
	/*
	 *removeCourse removes this assignment from its course and sets it to null. Checks to 
	 * make sure it isnt already null.
	 */
	public void removeCourse() {
		//make sure course isnt already null before attempting to access it
		if(course != null) {
			course.deleteAssignment(this);
			course = null;
		}
	}
	
	/*
     *toString returns the name of this assignment
     */
    public String toString() {
        return this.getName();
    }
}