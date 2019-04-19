/*
 * File: Time.java
 * Author: Ryan Bohorquez
 * Date:4/18/2019
 * Description: This File creates the Time class which is a simple helper/storage for an 
 *  hour/minute date time instead of trying to use an existing library.
 */

package main.java.flashcourse;

/**
 * Class: Time
 * Description: This is a simple class used to store and get a date/time.
 */
public class Time {

    //Private member variables for storing the time.
    private int hours;
    private int minutes;
    
    /*
     *Working Constructor for the Time class.
     *
     *@param hours hour for the saved time
     *@param minutes minutes for the saved time
     */
    public Time (int hours, int minutes) {
        //Make sure hours is in proper range
        if (hours > 23 || hours < 0) {
            System.out.println("Hours should be between 0 and 23.");
            this.setHours(0);
        } else {
            this.setHours(hours);
        }
        
        //Make sure minutes is in proper range
        if (minutes > 59 || minutes < 0) {
            System.out.println("Minutes should be between 0 and 59.");
            this.setMinutes(0);
        } else {
            this.setMinutes(minutes);
        }
    }
    
    /*
     *Constructor for the time class that just takes minutes (probably wont be used but its possible)
     *
     *@param minutes for the selected time
     */
    public Time(int minutes) {
        if (minutes > 1439 || minutes < 0) {
            System.out.println("Minutes should be between 0 and 1439 (total minutes in a day).");
            this.setHours(0);
            this.setMinutes(0); 
        } else {
            this.setHours(minutes/60);
            this.setMinutes(minutes%60); 
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours > 23 || hours < 0) {
            System.out.println("Hours should be between 0 and 23.");
            this.setHours(0);
        } else {
            this.setHours(hours);
        }
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes > 59 || minutes < 0) {
            System.out.println("Minutes should be between 0 and 59.");
        } else {
            this.setMinutes(minutes);
        }
    }
    
    /* 
     * returns a string for this time in a clean am/pm format
     * 
     * @return String in the correct format for printing.
     */
    public String toString() {
        //output string
        String out = "";
        //tmp var for hours
        int tmp = hours;
        //Am/pm bool
        boolean afternoon = false;
        
        //If this is the afternoon, we need to subtract 12 from hours.
        if (this.hours > 11) {
            tmp -= 12;
            afternoon = true;
        }
        
        //special case for 12:00, when hours == 0
        if (tmp == 0) {
            out += "12";
        } else {
            out += Integer.toString(tmp);
        }
        //Now add minutes
        out += ":";
        
        //special case when minutes is < 10, need to add a 0 to output
        if (minutes < 10) {
            out += "0";
        }
        
        out += Integer.toString(minutes);
        
        //finally append the am/pm
        if (afternoon) {
            out += "pm";
        } else {
            out += "am";
        }
        
        return out;  
    }
}
