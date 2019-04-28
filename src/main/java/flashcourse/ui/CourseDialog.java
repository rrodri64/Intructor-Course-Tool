/**
 * 
 * @author Jessica Tinaza 
 * 
 * File Name: CourseDialog.java
 * 
 * Date: 4/10/2019
 * 
 */

package main.java.flashcourse.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.flashcourse.Course;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.flashcourse.ui.CourseList;

import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Frame;

/**
 * 
 * This class creates a dialog box when a teacher user creates 
 * a new class. All fields that make up a course are to be filled 
 * in by the teacher.
 *
 */
public class CourseDialog extends JDialog {

    //Panel for content to go on
    private final JPanel contentPanel = new JPanel();
    
    //All text fields for user input
    private JTextField courseNameInput;
    private JTextField startDateInput;
    private JTextField endDateInput;
    private JTextField finalDate;
    private JTextField breakStartInput;
    private JTextField breakEndDate;
    private JTextField holidays;
    private JTextField freeDaysInput;
    private JTextField lectures;
    public Date date;
    public CourseList courseList;
    public Course newCourse;

   /**
    * 
    * @param frame that this will be built
    * @param title of the dialog 
    */
    public CourseDialog(Frame frame, String title) {
        super(frame, title, true);
        newCourse = null;
        try {
            jbInit();            
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
        /**
         * Initialize all components and action handling of dialog
         */
        private void jbInit() {
      
        setBounds(100, 100, 796, 324);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gridBagLayout);
        {
            JLabel titleLabel = new JLabel("Course Creator (dd/MM/YY)");
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
            GridBagConstraints gbc_titleLabel = new GridBagConstraints();
            gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
            gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
            gbc_titleLabel.gridx = 1;
            gbc_titleLabel.gridy = 0;
            contentPanel.add(titleLabel, gbc_titleLabel);
        }
        {
            //Course name label 
            JLabel courseName = new JLabel("Course Name:");
            GridBagConstraints gbc_courseName = new GridBagConstraints();
            gbc_courseName.anchor = GridBagConstraints.EAST;
            gbc_courseName.insets = new Insets(0, 0, 5, 5);
            gbc_courseName.gridx = 0;
            gbc_courseName.gridy = 1;
            contentPanel.add(courseName, gbc_courseName);
        }
        {
            //Course name input
            courseNameInput = new JTextField();
            GridBagConstraints gbc_courseNameInput = new GridBagConstraints();
            gbc_courseNameInput.insets = new Insets(0, 0, 5, 0);
            gbc_courseNameInput.fill = GridBagConstraints.HORIZONTAL;
            gbc_courseNameInput.gridx = 1;
            gbc_courseNameInput.gridy = 1;
            contentPanel.add(courseNameInput, gbc_courseNameInput);
            courseNameInput.setColumns(10);
        }
        {
            //Course start day label
            JLabel courseStartDate = new JLabel("Start Date:");
            GridBagConstraints gbc_courseStartDate = new GridBagConstraints();
            gbc_courseStartDate.anchor = GridBagConstraints.EAST;
            gbc_courseStartDate.insets = new Insets(0, 0, 5, 5);
            gbc_courseStartDate.gridx = 0;
            gbc_courseStartDate.gridy = 2;
            contentPanel.add(courseStartDate, gbc_courseStartDate);
        }
        {
            //Course start day input
            startDateInput = new JTextField();
            GridBagConstraints gbc_startDateInput = new GridBagConstraints();
            gbc_startDateInput.insets = new Insets(0, 0, 5, 0);
            gbc_startDateInput.fill = GridBagConstraints.HORIZONTAL;
            gbc_startDateInput.gridx = 1;
            gbc_startDateInput.gridy = 2;
            contentPanel.add(startDateInput, gbc_startDateInput);
            startDateInput.setColumns(10);
        }
        {
            //Course end date label
            JLabel endDate = new JLabel("End Date:");
            GridBagConstraints gbc_endDate = new GridBagConstraints();
            gbc_endDate.anchor = GridBagConstraints.EAST;
            gbc_endDate.insets = new Insets(0, 0, 5, 5);
            gbc_endDate.gridx = 0;
            gbc_endDate.gridy = 3;
            contentPanel.add(endDate, gbc_endDate);
        }
        {
            //Course end date input
            endDateInput = new JTextField();
            endDateInput.setColumns(10);
            GridBagConstraints gbc_endDateInput = new GridBagConstraints();
            gbc_endDateInput.insets = new Insets(0, 0, 5, 0);
            gbc_endDateInput.fill = GridBagConstraints.HORIZONTAL;
            gbc_endDateInput.gridx = 1;
            gbc_endDateInput.gridy = 3;
            contentPanel.add(endDateInput, gbc_endDateInput);
        }
        {
            //Course final exam date label
            JLabel finalExamDate = new JLabel("Final Exam Date:");
            GridBagConstraints gbc_finalExamDate = new GridBagConstraints();
            gbc_finalExamDate.anchor = GridBagConstraints.EAST;
            gbc_finalExamDate.insets = new Insets(0, 0, 5, 5);
            gbc_finalExamDate.gridx = 0;
            gbc_finalExamDate.gridy = 4;
            contentPanel.add(finalExamDate, gbc_finalExamDate);
        }
        {
            //Course final exam input
            finalDate = new JTextField();
            finalDate.setColumns(10);
            GridBagConstraints gbc_finalDate = new GridBagConstraints();
            gbc_finalDate.insets = new Insets(0, 0, 5, 0);
            gbc_finalDate.fill = GridBagConstraints.HORIZONTAL;
            gbc_finalDate.gridx = 1;
            gbc_finalDate.gridy = 4;
            contentPanel.add(finalDate, gbc_finalDate);
        }
        {
            //Course break start date label
            JLabel courseBreakStart = new JLabel("Course Break Start Date:");
            GridBagConstraints gbc_courseBreakStart = new GridBagConstraints();
            gbc_courseBreakStart.anchor = GridBagConstraints.EAST;
            gbc_courseBreakStart.insets = new Insets(0, 0, 5, 5);
            gbc_courseBreakStart.gridx = 0;
            gbc_courseBreakStart.gridy = 5;
            contentPanel.add(courseBreakStart, gbc_courseBreakStart);
        }
        {
            //Course break start date input
            breakStartInput = new JTextField();
            breakStartInput.setColumns(10);
            GridBagConstraints gbc_breakStartInput = new GridBagConstraints();
            gbc_breakStartInput.insets = new Insets(0, 0, 5, 0);
            gbc_breakStartInput.fill = GridBagConstraints.HORIZONTAL;
            gbc_breakStartInput.gridx = 1;
            gbc_breakStartInput.gridy = 5;
            contentPanel.add(breakStartInput, gbc_breakStartInput);
        }
        {
            //Course break end label
            JLabel breakEndDate = new JLabel("Course Break End Date");
            GridBagConstraints gbc_breakEndDate = new GridBagConstraints();
            gbc_breakEndDate.anchor = GridBagConstraints.EAST;
            gbc_breakEndDate.insets = new Insets(0, 0, 5, 5);
            gbc_breakEndDate.gridx = 0;
            gbc_breakEndDate.gridy = 6;
            contentPanel.add(breakEndDate, gbc_breakEndDate);
        }
        {
            //Course break end date input
            breakEndDate = new JTextField();
            breakEndDate.setColumns(10);
            GridBagConstraints gbc_breakEndDate = new GridBagConstraints();
            gbc_breakEndDate.insets = new Insets(0, 0, 5, 0);
            gbc_breakEndDate.fill = GridBagConstraints.HORIZONTAL;
            gbc_breakEndDate.gridx = 1;
            gbc_breakEndDate.gridy = 6;
            contentPanel.add(breakEndDate, gbc_breakEndDate);
        }
        
        
        {
            //Course holidays label
            JLabel Holidays = new JLabel("Holidays(Separate with comas)");
            GridBagConstraints gbc_Holidays = new GridBagConstraints();
            gbc_Holidays.anchor = GridBagConstraints.EAST;
            gbc_Holidays.insets = new Insets(0, 0, 5, 5);
            gbc_Holidays.gridx = 0;
            gbc_Holidays.gridy = 8;
            contentPanel.add(Holidays, gbc_Holidays);
        }
        {
            //Course holidays text field
            holidays = new JTextField();
            holidays.setColumns(10);
            GridBagConstraints gbc_holidays = new GridBagConstraints();
            gbc_holidays.insets = new Insets(0, 0, 5, 0);
            gbc_holidays.fill = GridBagConstraints.HORIZONTAL;
            gbc_holidays.gridx = 1;
            gbc_holidays.gridy = 8;
            contentPanel.add(holidays, gbc_holidays);
        }
        {
            //Course free days label
            JLabel freeDays = new JLabel("Free Days (Separate with comas)");
            GridBagConstraints gbc_freeDays = new GridBagConstraints();
            gbc_freeDays.anchor = GridBagConstraints.EAST;
            gbc_freeDays.insets = new Insets(0, 0, 0, 5);
            gbc_freeDays.gridx = 0;
            gbc_freeDays.gridy = 9;
            contentPanel.add(freeDays, gbc_freeDays);
        }
        {
            //Course free days input
            freeDaysInput = new JTextField();
            freeDaysInput.setColumns(10);
            GridBagConstraints gbc_freeDaysInput = new GridBagConstraints();
            gbc_freeDaysInput.fill = GridBagConstraints.HORIZONTAL;
            gbc_freeDaysInput.gridx = 1;
            gbc_freeDaysInput.gridy = 9;
            contentPanel.add(freeDaysInput, gbc_freeDaysInput);
        }
        {
            //Button panel
            JPanel buttonPanel = new JPanel();
            getContentPane().add(buttonPanel, BorderLayout.EAST);
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
            {
                //Create Class button
                JButton createButton = new JButton("Create Class ");
                createButton.setHorizontalAlignment(SwingConstants.LEFT);
                createButton.setActionCommand("CreateClass");
                buttonPanel.add(createButton);
                getRootPane().setDefaultButton(createButton);
                
                createButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            addCourseButton_actionPerformed(e);
                        } catch (ParseException e1) {
                            
                            e1.printStackTrace();
                        }
                    }
                });
            }
            {
                //Cancel Button
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPanel.add(cancelButton);
                cancelButton.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                       cancelButton_actionPerformed(e);
                    }
                });
            }
        }
    }
        
        void cancelButton_actionPerformed(ActionEvent e) {
            this.dispose();
        }
        
        /**
         * 
         * @param e action event of pressing create course button
         * @throws ParseException Catches bad parsing from user input
         */
        void addCourseButton_actionPerformed(ActionEvent e) throws ParseException {
            
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YY");
            
            String courseName = courseNameInput.getText();
            newCourse = new Course(courseName);
            
            //Date inputs
            String holidaysInput = holidays.getText();
            String freeDays = freeDaysInput.getText();
            //String lectureDates = lectures.getText();
            
            //Tokenizers for separating out comma separated list of dates
            StringTokenizer holidayToken = new StringTokenizer(holidaysInput, ",");
            StringTokenizer freeToken = new StringTokenizer(freeDays, ",");
            
            //Set holiday dates 
            while(holidayToken.hasMoreTokens()) {
                date = simple.parse(holidayToken.nextToken());
                newCourse.addHolidayDates(new CalendarDate(date), courseName);
            }
            
            //Set free days dates
            while(freeToken.hasMoreTokens()) {
                date = simple.parse(freeToken.nextToken());
                newCourse.addFreeDays(new CalendarDate(date), courseName);
            }
            
            //Set the start date
            date = simple.parse(startDateInput.getText());
            newCourse.setCourseStartDate(new CalendarDate(date));
            
            //Set end date
            date = simple.parse(endDateInput.getText());
            newCourse.setCourseEndDate(new CalendarDate(date));
            
            //Set final exam date
            date = simple.parse(finalDate.getText());
            newCourse.setFinalExamDate(new CalendarDate(date));
            
            //Set break start
            date = simple.parse(breakStartInput.getText());
            newCourse.setCourseBreakStart(new CalendarDate(date));
            
            //Set break end
            date = simple.parse(breakEndDate.getText());
            newCourse.setCourseBreakEnd(new CalendarDate(date));
            
            //Close dialog
            this.dispose();
           
            
        }
        
        /**
         * 
         * @return returns the string of the course created to 
         * be added to the course selection module
         */
        public String getCourseName() {
            return courseNameInput.getText();
        }
        
        //Testing Method to make sure dates are storing properly
        public int getDateCount(Map<CalendarDate, String> dates) {
            return dates.size();
        }
        
        /**
         * 
         * @return the newly created course with all attributes created
         */
        public Course getCourse() {
            return newCourse;
        }

}
