/**AssignmentTest.java is designed to test the functionality and correctness of Assignment.java
 * @author Bryan Culver
 * @version 1.0 10 Apr 2019
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import main.java.flashcourse.Assignment;
import main.java.flashcourse.Course;
import main.java.flashcourse.ASSIGNEDGROUP;
import main.java.memoranda.date.CalendarDate;

/**
 * @author Bryan Culver
 *
 */
public class AssignmentTest {
    Course ser316;
    Course ser230;
    Course ser334;
    Course cse110;
    CalendarDate startSpringA19;
    CalendarDate startSpringB19;
    CalendarDate endSpringA19;
    CalendarDate endSpringB19;
    CalendarDate aWeek1;
    CalendarDate aWeek2;
    CalendarDate aWeek3;
    CalendarDate aWeek4;
    CalendarDate aWeek5;
    CalendarDate aWeek6;
    CalendarDate aWeek7;
    CalendarDate bWeek1;
    CalendarDate bWeek2;
    CalendarDate bWeek3;
    CalendarDate bWeek4;
    CalendarDate bWeek5;
    CalendarDate bWeek6;
    CalendarDate bWeek7;
    ASSIGNEDGROUP Student;
    ASSIGNEDGROUP Teacher;
    ASSIGNEDGROUP TA;
    String weekOneA;
    String finalA;
    String finalB;
    String projectA;
    String projectB;
    String weekTwoA;
    String weekThreeA;
    String weekFourA;
    String weekFiveA;
    String weekSixA;
    String weekSevenA;
    String weekOneB;
    String weekTwoB;
    String weekThreeB;
    String weekFourB;
    String weekFiveB;
    String weekSixB;
    String weekSevenB;
    String descChar;
    String descSymbol;
    String descBlank;
    String descSpace;
    Assignment assign1;
    Assignment assign2;
    Assignment assign3;
    Assignment assign4;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        ser316 = new Course("SER316");
        ser230 = new Course("SER230");
        ser334 = new Course("SER334");
        cse110 = new Course("CSE110");
        
        weekOneA = "Session A Week 1 Assignment";
        weekTwoB = "Session B Week 2 Assignment";
        
        descSpace = " ";
        descChar = "Characters description.";
        
        assign1 = new Assignment(ser316, startSpringA19, TA, weekOneA, descChar);
        assign2 = new Assignment(ser230, startSpringB19, Student, weekTwoB, descSpace);
        assign3 = new Assignment(null, startSpringB19, Student, weekTwoB, descSpace);
    }

    @Test
    public void getCourseTest() {
        Course getSer316 = assign1.getCourse();
        Course getSer230 = assign2.getCourse();
        Course getCse110 = assign3.getCourse();
        assertTrue(getSer316 == ser316); // Checks that method returns proper course from constructor
        assertTrue(getSer230 == ser230); 
        assertFalse(getSer316 == null); // verify course still in Assignment
        assertTrue(getCse110 == null); // check if Assignment can be created without Course
    }
    
    @Test
    public void setCourseTest() {
        assign1.setCourse(ser334);
        assign2.setCourse(null);
        assign3.setCourse(ser230);
        assign4.setCourse(cse110); // assign4 is declared but not instantiated. 
        assertTrue(assign1.getCourse() == ser334); // Tests normal function
        assertTrue(assign2.getCourse() == null); // Assign null course to Assignment
        assertTrue(assign3.getCourse() == ser230); // Tests filling null spot 
        assign1.setCourse(ser334);
        assertTrue(assign1.getCourse() == ser334); // Test assign to same course
        assertTrue(assign4.getCourse() == cse110); // Test assignment setter without constructor
    }
    
    @Test 
    public void removeCourseTest() {
        assign1.removeCourse(); // normal removal
        assign3.removeCourse(); // tests removal of null course
        assign4.removeCourse(); // tests empty assignment
        assertTrue(assign4.getCourse() == null);
        assertFalse(assign1.getCourse() == ser316);
        assertTrue(assign3.getCourse() == null);
    }

}
