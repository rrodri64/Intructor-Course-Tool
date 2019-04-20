/*
 * File: LectureTest.java
 * Author: Ryan Bohorquez
 * Date:4/18/2019
 * Description: This file tests the functionality of the lecture class ensuring the new methods not in task
 *  work as intended, as well as the constructor
 */

package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.flashcourse.ASSIGNEDGROUP;
import main.java.flashcourse.Course;
import main.java.flashcourse.Lecture;
import main.java.flashcourse.Time;
import main.java.memoranda.date.CalendarDate;

/**
 * Class: LectureTest
 * Description: Sets up tests for functionality testing in the lecture class. Tests all non-getter/setter methods
 */
public class LectureTest {
    /*
     *Tests the constructor for Lecture, makes sure it sets up everything as intended
     */
    @Test
    public void testConstructor() {
        CalendarDate lec1Date = new CalendarDate(6,2,2019);
        Course ser334 = new Course("SER 334");
        Lecture lec1 = new Lecture(ser334, lec1Date, ASSIGNEDGROUP.Student, "Lecture1", "Hello world training", new Time(2,24), new Time(3,24));
        assertEquals(lec1.getdate(), lec1Date);
        assertEquals(lec1.getAssignedGroup(), ASSIGNEDGROUP.Student);
        assertTrue(lec1.getName().equals("Lecture1"));
        assertTrue(lec1.getDescription().equals("Hello world training"));
        assertTrue(lec1.getStartTime().toString().equals("2:24am"));
        assertTrue(lec1.getEndTime().toString().equals("3:24am"));
    }

    /*
     *Tests setting a course to an Lecture that already has a different course. The Lecture should
     * be removed from the first course and added to the second
     */
    @Test
    public void testSetCourseWithExistingCourse() {
        CalendarDate lec1Date = new CalendarDate(6,2,2019);
        Course ser334 = new Course("SER 334");
        Course ser315 = new Course("SER 315");
        Lecture lec1 = new Lecture(ser334, lec1Date, ASSIGNEDGROUP.Student, "Task1", "Complete homework 1", new Time(2,24), new Time(3,24));
        lec1.setCourse(ser315);
        assertFalse(ser334.getLectureDates().contains(lec1));
        assertTrue(ser315.getLectureDates().contains(lec1));
    }
}
