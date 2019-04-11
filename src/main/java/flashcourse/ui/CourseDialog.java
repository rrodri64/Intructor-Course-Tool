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

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Frame;

public class CourseDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField courseNameInput;
    private JTextField startDateInput;
    private JTextField endDateInput;
    private JTextField finalDate;
    private JTextField breakStartInput;
    private JTextField breakEndDate;
    private JTextField holidays;
    private JTextField textField_1;
    public Date date;
    public boolean CANCELLED = true;
    public CourseList courseList;

    /**
     * Launch the application.
     */


    /**
     * Create the dialog.
     */
    public CourseDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();            
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    
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
            JLabel titleLabel = new JLabel("Course Creator");
            titleLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
            GridBagConstraints gbc_titleLabel = new GridBagConstraints();
            gbc_titleLabel.fill = GridBagConstraints.VERTICAL;
            gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
            gbc_titleLabel.gridx = 1;
            gbc_titleLabel.gridy = 0;
            contentPanel.add(titleLabel, gbc_titleLabel);
        }
        {
            JLabel courseName = new JLabel("Course Name:");
            GridBagConstraints gbc_courseName = new GridBagConstraints();
            gbc_courseName.anchor = GridBagConstraints.EAST;
            gbc_courseName.insets = new Insets(0, 0, 5, 5);
            gbc_courseName.gridx = 0;
            gbc_courseName.gridy = 1;
            contentPanel.add(courseName, gbc_courseName);
        }
        {
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
            JLabel courseStartDate = new JLabel("Start Date:");
            GridBagConstraints gbc_courseStartDate = new GridBagConstraints();
            gbc_courseStartDate.anchor = GridBagConstraints.EAST;
            gbc_courseStartDate.insets = new Insets(0, 0, 5, 5);
            gbc_courseStartDate.gridx = 0;
            gbc_courseStartDate.gridy = 2;
            contentPanel.add(courseStartDate, gbc_courseStartDate);
        }
        {
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
            JLabel endDate = new JLabel("End Date:");
            GridBagConstraints gbc_endDate = new GridBagConstraints();
            gbc_endDate.anchor = GridBagConstraints.EAST;
            gbc_endDate.insets = new Insets(0, 0, 5, 5);
            gbc_endDate.gridx = 0;
            gbc_endDate.gridy = 3;
            contentPanel.add(endDate, gbc_endDate);
        }
        {
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
            JLabel finalExamDate = new JLabel("Final Exam Date:");
            GridBagConstraints gbc_finalExamDate = new GridBagConstraints();
            gbc_finalExamDate.anchor = GridBagConstraints.EAST;
            gbc_finalExamDate.insets = new Insets(0, 0, 5, 5);
            gbc_finalExamDate.gridx = 0;
            gbc_finalExamDate.gridy = 4;
            contentPanel.add(finalExamDate, gbc_finalExamDate);
        }
        {
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
            JLabel courseBreakStart = new JLabel("Course Break Start Date:");
            GridBagConstraints gbc_courseBreakStart = new GridBagConstraints();
            gbc_courseBreakStart.anchor = GridBagConstraints.EAST;
            gbc_courseBreakStart.insets = new Insets(0, 0, 5, 5);
            gbc_courseBreakStart.gridx = 0;
            gbc_courseBreakStart.gridy = 5;
            contentPanel.add(courseBreakStart, gbc_courseBreakStart);
        }
        {
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
            JLabel lectureTimes = new JLabel("Lecture Times(Separate with comas)");
            GridBagConstraints gbc_lectureTimes = new GridBagConstraints();
            gbc_lectureTimes.anchor = GridBagConstraints.EAST;
            gbc_lectureTimes.insets = new Insets(0, 0, 5, 5);
            gbc_lectureTimes.gridx = 0;
            gbc_lectureTimes.gridy = 6;
            contentPanel.add(lectureTimes, gbc_lectureTimes);
        }
        {
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
            JLabel Holidays = new JLabel("Holidays(Separate with comas)");
            GridBagConstraints gbc_Holidays = new GridBagConstraints();
            gbc_Holidays.anchor = GridBagConstraints.EAST;
            gbc_Holidays.insets = new Insets(0, 0, 5, 5);
            gbc_Holidays.gridx = 0;
            gbc_Holidays.gridy = 7;
            contentPanel.add(Holidays, gbc_Holidays);
        }
        {
            holidays = new JTextField();
            holidays.setColumns(10);
            GridBagConstraints gbc_holidays = new GridBagConstraints();
            gbc_holidays.insets = new Insets(0, 0, 5, 0);
            gbc_holidays.fill = GridBagConstraints.HORIZONTAL;
            gbc_holidays.gridx = 1;
            gbc_holidays.gridy = 7;
            contentPanel.add(holidays, gbc_holidays);
        }
        {
            JLabel freeDays = new JLabel("Free Days (Separate with comas)");
            GridBagConstraints gbc_freeDays = new GridBagConstraints();
            gbc_freeDays.anchor = GridBagConstraints.EAST;
            gbc_freeDays.insets = new Insets(0, 0, 0, 5);
            gbc_freeDays.gridx = 0;
            gbc_freeDays.gridy = 8;
            contentPanel.add(freeDays, gbc_freeDays);
        }
        {
            textField_1 = new JTextField();
            textField_1.setColumns(10);
            GridBagConstraints gbc_textField_1 = new GridBagConstraints();
            gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField_1.gridx = 1;
            gbc_textField_1.gridy = 8;
            contentPanel.add(textField_1, gbc_textField_1);
        }
        {
            JPanel buttonPane = new JPanel();
            getContentPane().add(buttonPane, BorderLayout.EAST);
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
            {
                JButton createButton = new JButton("Create Class ");
                createButton.setHorizontalAlignment(SwingConstants.LEFT);
                createButton.setActionCommand("CreateClass");
                buttonPane.add(createButton);
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
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
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
        
        void addCourseButton_actionPerformed(ActionEvent e) throws ParseException {
           
            
            //Date date =new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/YY");
            Course newCourse = new Course(courseNameInput.getText());
            
            try {
            date = simple.parse(startDateInput.getText());
            newCourse.setCourseStartDate(new CalendarDate(date));
            date = simple.parse(endDateInput.getText());
            newCourse.setCourseEndDate(new CalendarDate(date));
            date = simple.parse(finalDate.getText());
            newCourse.setFinalExamDate(new CalendarDate(date));
            date = simple.parse(breakStartInput.getText());
            newCourse.setCourseBreakStart(new CalendarDate(date));
            date = simple.parse(breakEndDate.getText());
            newCourse.setCourseBreakEnd(new CalendarDate(date));
            this.dispose();
            }
            catch(ParseException p) {
                System.out.println("Empty Field");
                this.dispose();
            }
            
        }
        
        public String getCourseName() {
            return courseNameInput.getText();
        }

}
