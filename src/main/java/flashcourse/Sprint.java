package main.java.flashcourse;

import java.util.Calendar;

import main.java.memoranda.date.CalendarDate;


/**
 * Class for holding information about a Sprint, including start and end dates
 * and a day for the retrospective
 * @author Caleb Schafer
 *
 */
public class Sprint {
	private String sprintTitle;
	private CalendarDate sprintStart;
	private CalendarDate sprintEnd;
	private CalendarDate retrospective;
	private final Integer RETRO_DAY = 3;
	private Calendar cal = Calendar.getInstance();
	
	/**
	 * Constructor for a Sprint. Takes the start and end date and adds a generic
	 * retrospective day 3 days after the end of the sprint
	 * @param title Title of sprint (i.e. Sprint 1, 2 etc.)
	 * @param start The start date
	 * @param end The end date
	 */
	public Sprint(String title, CalendarDate start,CalendarDate end) {
		this.sprintTitle = title;
		this.sprintStart = start;
		this.sprintEnd = end;
		
		int day = end.getDay();
		int month = end.getMonth();
		int year = end.getYear();
		
		cal.set(year, month, day);
		cal.add(Calendar.DATE, 3);
		
		this.retrospective = new CalendarDate(cal.get(Calendar.DATE),
				cal.get(Calendar.MONTH),
				cal.get(Calendar.YEAR));
	}
	
	/**
	 * Getter for the title
	 * @return sprint title
	 */
	public String getTitle() {
		return sprintTitle;
	}

	/**
	 * Setter for the title if it needs to be changed
	 * @param sprintTitle the new sprint title
	 */
	public void setTitle(String sprintTitle) {
		this.sprintTitle = sprintTitle;
	}

	/**
	 * Gets the start date of this sprint
	 * @return the CalendarDate version of the start date
	 */
	public CalendarDate getStart() {
		return sprintStart;
	}

	/**
	 * Setter for the sprint start date if the date is changed
	 * @param sprintStart The new start date
	 */
	public void setSprintStart(CalendarDate sprintStart) {
		this.sprintStart = sprintStart;
	}

	/**
	 * Getter for the sprint end date 
	 * @return the CalendarDate version of the end date
	 */
	public CalendarDate getSprintEnd() {
		return sprintEnd;
	}

	/**
	 * Setter for the sprint end date if it needs to be changed
	 * @param sprintEnd the new sprint end date
	 */
	public void setSprintEnd(CalendarDate sprintEnd) {
		this.sprintEnd = sprintEnd;
	}

	/**
	 * Gets the day set for the retrospective day
	 * (This may break other stuff as the day may be outside of calendar bounds currently)
	 * @return the CalendarDate version of the retrospective date
	 */
	public CalendarDate getRetroDay() {
		return retrospective;
	}

	/**
	 * Setter method for the retrospective
	 * @param retrospective 
	 */
	public void setRetrosDay(CalendarDate retrospective) {
		this.retrospective = retrospective;
	}
	
	
}
