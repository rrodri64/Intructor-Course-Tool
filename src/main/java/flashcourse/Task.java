/*
 * File: Task.java
 * Author: Ryan Bohorquez
 * Date:4/3/2019
 * Description: This file declares the Task abstract class that other classes will extend from
 */

package main.java.flashcourse;

import main.java.memoranda.date.CalendarDate;

/**
 * Class: Task
 * Description: Task is an abstract class that will hold informtion that Lectures, assignments, and tasks will all have in common.
 *  It includes getters and setters for the common variables.
 */
public abstract class Task {

	//Private member variables that should be identical in all classes.
	private CalendarDate date;
	private ASSIGNEDGROUP assignedGroup;
	private String name;
	private String description;
	
	/*
	 *Constructor for the Task class, intializes local variables based on parameters
	 *
	 *@param date the CalenderDate to set as the date
	 *@param assignedGroup the ASSIGNEDGROUP this task is assigned to
	 *@param name the name of this assignment for printing purposes.
	 *@param description the description of the Task
	 */
	public Task(CalendarDate date, ASSIGNEDGROUP assignedGroup, String name, String description) {
		this.date = date;
		this.assignedGroup = assignedGroup;
		this.name = name;
		this.description = description;
	}

	public CalendarDate getdate() {
		return this.date;
	}
	
	public void setdate(CalendarDate date) {
		this.date = date;
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
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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
