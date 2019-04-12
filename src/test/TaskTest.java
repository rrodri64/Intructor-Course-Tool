/*
 * File: TaskTest.java
 * Author: Ryan Bohorquez
 * Date:4/11/2019
 * Description: This file tests the fucntionality of the test class since many classes will extend from this.
 *  Its important the functionality in this class is fully working so errors dont extend into other classes.
 */

package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.flashcourse.ASSIGNEDGROUP;
import main.java.flashcourse.Task;
import main.java.memoranda.date.CalendarDate;

/**
 * Class: TaskTest
 * Description: Sets up tests for functionality testing in the task class, as well as testing 
 *  if Assignment inherits correctly.
 */
public class TaskTest {
     TaskTester task1;
     TaskTester task2;
     CalendarDate task1Due = new CalendarDate(24,8,1995);
     CalendarDate task2Due = new CalendarDate(23,8,1995);
    
    @Before
    public void setUp() throws Exception {
         
    }
    
    /*
     *Tests the constructor for Task. Makes sure everything is initialized how it should be.
     */
    @Test
    public void testConstructor() {
        task1 = new TaskTester(task1Due, ASSIGNEDGROUP.Student, "Task1", "Complete homework 1");
        assertEquals(task1.getdate(),task1Due);
        assertEquals(task1.getAssignedGroup(), ASSIGNEDGROUP.Student);
        assertTrue(task1.getName().equals("Task1"));
        assertTrue(task1.getDescription().equals("Complete homework 1"));
    }
    
    /*
     *Tests the getter and setter functionality in the Task superclass. First uses the constructor to set up
     * wrong information, then sets and checks them using the accessor methods.
     */
    @Test
    public void testGettersAndSetters() {
        task2 = new TaskTester(task1Due, ASSIGNEDGROUP.Student, "Task1", "Complete homework 1");
        task2.setAssignedGroup(ASSIGNEDGROUP.TA);
        task2.setdate(task2Due);
        task2.setDescription("Complete homework 2");
        task2.setName("Task2");
        assertEquals(task2.getdate(),task2Due);
        assertEquals(task2.getAssignedGroup(), ASSIGNEDGROUP.TA);
        assertTrue(task2.getName().equals("Task2"));
        assertTrue(task2.getDescription().equals("Complete homework 2"));
        
    }
    
    /**
     * Class: TaskTester
     * Description: Extends Task so that its functionality may be tested. Simply calls the super constructor.
     */
    private class TaskTester extends Task {

        /*
         *Constructor TaskTester, used to test out or Task class
         *
         *@param course the Course to be added to
         *@param dueDate the CalenderDate to set as the dueDate
         *@param assignedGroup the ASSIGNEDGROUP this task is assigned to
         *@param name the name of this assignment for printing purposes.
         */
        public TaskTester(CalendarDate date, ASSIGNEDGROUP assignedGroup, String name, String description) {
            super(date, assignedGroup, name, description);
        }
        
    }
}


