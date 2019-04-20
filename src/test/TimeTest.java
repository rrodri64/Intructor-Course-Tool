/*
 * File: TaskTest.java
 * Author: Ryan Bohorquez
 * Date:4/18/2019
 * Description: This file tests the fucntionality of the time class.
 */

package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.flashcourse.Time;

/**
 * Class: TimeTest
 * Description: Sets up tests for functionality testing in the Time class to enable the storage of an
 *  easy day time for outputting.
 */
public class TimeTest {
     Time time;
    
    @Before
    public void setUp() throws Exception {
         
    }
    
    /*
     *Tests the constructors for time, to make sure both are functioning correctly.
     */
    @Test
    public void testConstructors() {
        time = new Time(2, 24);
        assertEquals(2, time.getHours());
        assertEquals(24, time.getMinutes());
        time = new Time(72);
        assertEquals(1, time.getHours());
        assertEquals(12, time.getMinutes());
        time = new Time(25, 70);
        assertEquals(0, time.getHours());
        assertEquals(0, time.getMinutes());
        time = new Time(-23);
        assertEquals(0, time.getHours());
        assertEquals(0, time.getMinutes());
    }
    
    /*
     *Tests the to string method to make sure it is formatting am and pm correctly
     */
    @Test
    public void testToString() {
        time = new Time(2, 24);
        assertTrue(time.toString().equalsIgnoreCase("2:24am"));
        time.setHours(0);
        time.setMinutes(1);
        assertTrue(time.toString().equalsIgnoreCase("12:01am"));
        time.setHours(23);
        time.setMinutes(0);
        assertTrue(time.toString().equalsIgnoreCase("11:00pm"));
    }
    
}


