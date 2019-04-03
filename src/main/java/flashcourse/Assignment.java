/*
 * File: Assignment.java
 * Author: Ryan Bohorquez
 * Date:4/3/2019
 * Description: This file is used for the creation and storing of assignments. The assignment class holds all the
 *  information for a class, and the ASSIGNEDGROUP enum is used determine who an assignment is for.
 */

package main.java.flashcourse;

import main.java.memoranda.date.*;

public class Assignment {

	//Member variables
	private Course course;
	private CalendarDate dueDate;
	private ASSIGNEDGROUP assignedGroup;
	private String name;
	
	/*
	 *Constructor for the Assignment class, intializes local variables based on parameters and calls addAssignment on the passed course
	 *
	 *@param course the Course to be added to
	 *@param dueDate the CalenderDate to set as the dueDate
	 *@param assignedGroup the ASSIGNEDGROUP this task is assigned to
	 *@param name the name of this assignment for printing purposes.
	 */
	public Assignment(Course course, CalendarDate dueDate, ASSIGNEDGROUP assignedGroup, String name) {
		course.addAssignment(this);
		this.dueDate = dueDate;
		this.assignedGroup = assignedGroup;
		this.name = name;
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
	
	public CalendarDate getDueDate() {
		return this.dueDate;
	}
	
	public void setDueDate(CalendarDate date) {
		this.dueDate = date;
	}
	
	public ASSIGNEDGROUP getAssignedGroup() {
		return this.assignedGroup;
	}
	
	public void setAssignedGroup(ASSIGNEDGROUP group) {
		this.assignedGroup = group;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name = newName;
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
}

/**
 * Enum: ASSIGNEDGROUP
 * Description: This enum is used to set who an assignment is for. This is because Teachers should be able to 
 *  assign only themselves stuff, TA's stuff, or for students.
 */
enum ASSIGNEDGROUP {
	Student, TA, Teacher;
}