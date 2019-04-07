package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.flashcourse.Course;
import main.java.flashcourse.Courses;

public class CoursesTest {
	 Course SER316;
	 Course SER230;
	 Course SER334;
	 Course SER316A;
	 Courses courses;
	
	@Before
	public void setUp() throws Exception {
		 SER316 = new Course("SER316");
		 SER316A = new Course("SER316");
		 SER230 = new Course("SER230");
		 SER334 = new Course("SER334");
		 courses = new Courses();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void addTest() {
		courses.addCourse(SER316);
		courses.addCourse(SER230);
		courses.addCourse(SER334);
		assertTrue(courses.numOfCourses() == 3);//check all 3 are properly in there
		assertFalse(courses.addCourse(SER316));//try to add a course again
		assertTrue(courses.addCourse("SER330"));//add a course with only the name, should create a new blank one
		assertTrue(courses.numOfCourses() == 4);//check that all 4 courses are in there
	}
	
	@Test
	public void getTest() {
		courses.addCourse(SER316);
		courses.addCourse(SER230);
		courses.addCourse(SER334);
		assertTrue(courses.getCourse("SER316").equals(SER316));//checking the course returned is the same as the one added
		assertTrue(courses.getCourse("SER222") == null);//make sure it doesn't return something that isn't there
	}
	
	@Test
	public void hasTest() {
		courses.addCourse(SER316);
		courses.addCourse(SER230);
		courses.addCourse(SER334);
		assertTrue(courses.hasCourse("SER230"));//checking seraching for a Course works
		assertFalse(courses.hasCourse("SER315"));//checking searching doesn't return something not there
		assertTrue(courses.hasCourse(SER230));//checking searching for specific Course object works
	}
	
	@Test
	public void removeTest() {
		courses.addCourse(SER316);
		courses.addCourse(SER230);
		courses.addCourse(SER334);
		
		assertTrue(courses.removeCourse("SER316"));//remove a course by string
		assertFalse(courses.removeCourse("SER316"));//shouldn't be able to remove a course already removed
		assertTrue(courses.numOfCourses() == 2);//checking size is proper
		assertTrue(courses.removeCourse(SER334));//testing removal of Course as an object
		assertTrue(courses.numOfCourses() == 1);
	}
	



}
