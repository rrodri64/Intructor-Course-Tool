/*
 * File: Lecture.java
 * Author: Ryan Bohorquez
 * Date:4/18/2019
 * Description: This file is used for the creation and storing of lectures. 
 */

package main.java.flashcourse;

import main.java.memoranda.date.*;

/**
 * Class: Lecture
 * Description: Lecture extends all the functionality from Task and adds the ability to set 
 *  a start time and end time, as well as the course its for.
 */
public class Lecture extends Task{

    //Member variables
    private Course course;
    private Time startTime;
    private Time endTime;
    
    /*
     *Constructor for the Lecture class, intializes local variables based on parameters and calls addAssignment on the passed course
     *
     *@param course the Course to be added to
     *@param dueDate the CalenderDate to set as the dueDate
     *@param assignedGroup the ASSIGNEDGROUP this lecture is assigned to
     *@param name the name of this lecture for printing purposes.
     *@param description the description for this lecture
     *@param time time of day for this lecture
     */
    public Lecture(Course course, CalendarDate dueDate, ASSIGNEDGROUP assignedGroup, String name, String description, Time startTime, Time endTime) {
        super(dueDate, assignedGroup, name, description);
        setCourse(course);
        setStartTime(startTime);
        setEndTime(endTime);
    }
    
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course newCourse) {
        //If this lecture already has a course assigned, remove this lecture from that course.
        this.removeCourse();
        this.course = newCourse;
        newCourse.addLecture(this);
    }
    
    public Time getStartTime() {
        return startTime;
    }
        
    void setStartTime(Time time) {
        this.startTime = time;
    }
    
    /*
     *removeCourse removes this lecture from its course and sets it to null. Checks to 
     * make sure it isnt already null.
     */
    public void removeCourse() {
        //make sure course isnt already null before attempting to access it
        if(course != null) {
            course.deleteLecture(this);
            course = null;
        }
    }
    
    /*
     *toString returns the name of this lecture
     */
    public String toString() {
        return this.getName();
    }

    /**
     * @return the endTime
     */
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}