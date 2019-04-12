/**
 * @author Jessica Tinaza
 * File Name: CourseTest.java
 * Description: This class has several tests for testing the storing and
 * manipulating data within a course.
 */


package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.flashcourse.Course;
import main.java.flashcourse.ASSIGNEDGROUP;
import main.java.flashcourse.Assignment;

import main.java.memoranda.date.CalendarDate;

public class CourseTest {
     Course SER321;
     Course SER222;
     Course SER421;
     CalendarDate date1;
     CalendarDate date2;
     Assignment assign1;
     Assignment assign2;
    
    @Before
    public void setUp() throws Exception {
         SER321 = new Course("SER321");
         SER222 = new Course("SER222");
         SER421 = new Course("SER421");
         date1 = new CalendarDate(05,15,19);
         date2 = new CalendarDate(06,14,19);
         assign1 = new Assignment(SER222, date1, ASSIGNEDGROUP.Student, "AssignTest", "Please complete");
         assign2 = new Assignment(SER222, date1, ASSIGNEDGROUP.Student, "AssignTest", "Please complete");
         
         
    }
    
    
    @Test
    //All other data manipulation methods that use HashMap for adding
    //are written the same way, so this test covers those as well
    public void testingAddDates() {
        SER321.addLectureDates(date1, "SER321");
        SER321.addLectureDates(date2, "SER321");
        assertTrue(SER321.getLectureDates().size()==2);
       
    }
    
    @Test
    //All other data manipulation methods that use HashMap for deleting
    //are written the same way, so this test covers those as well
    public void testingDeleteDates() {
        SER222.addHolidayDates(date1, "SER222");
        assertTrue(SER222.getHolidayDates().size()==1);
        SER222.deleteHolidayDate(date1, "SER222");
        assertTrue(SER222.getHolidayDates().size()==0);
    }
    
    @Test
    //Tests for adding assignments
    public void testingAddAssignments() {
       //Test for adding one course
        assertTrue(SER421.addAssignment(assign1)==true);
        
        //Test for adding duplicate course
        assertTrue(SER421.addAssignment(assign1) == false);
        
        //Test for adding a second course
        assertTrue(SER421.addAssignment(assign2) == true);
    }
    
    @Test
    //Tests for deleting assignments
    public void testingDeleteAssignments() {
       //Add course to be deleted
        assertTrue(SER421.addAssignment(assign1)==true);
        
       //Test for deleting assignment
        assertTrue(SER421.deleteAssignment(assign1)==true);
        
        //Test for deleting assignment that does not exist
        assertTrue(SER421.deleteAssignment(assign1) == false);
        
       
    }



}


