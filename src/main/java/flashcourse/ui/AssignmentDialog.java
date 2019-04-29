package main.java.flashcourse.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
//import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.java.flashcourse.*;
import main.java.flashcourse.Assignment;
import main.java.flashcourse.Course;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.util.Local;

import javax.swing.JCheckBox;
import main.java.memoranda.ui.*;
import java.util.HashMap;

/**
 * Class: AssignmentDialog
 * Description: AssignmentDialog handles setting up the dialog box when creating a new assignment
 *  It give the user proper feedback when attempting to create an assignment and lets them know when
 *  it is successful.
 */
public class AssignmentDialog extends JDialog {
	private static CurrentCourse currentCourse = CurrentCourse.getInstance();
	
	
	//Main panel for everything to go into
    JPanel panelMain = new JPanel(new BorderLayout());
    
    //Panel everything not buttons
    JPanel panelInputFields = new JPanel(new BorderLayout());
    
    //Create buttons for create/cancel and the panel they belong in
    JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton buttonCancel = new JButton();
    JButton buttonCreate = new JButton();
    
    //Borders around area user will enter information
    Border borderNotTitle;
    Border borderInfoArea;
    
    //Sets up the panel and label for the header of this window
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel header = new JLabel();
    public boolean CANCELLED = true;
    
    //Setup panel, border, and labels for title and description fields
    JPanel panelTitleDesc = new JPanel(new GridBagLayout());
    Border borderTitleDesc;
    Border borderHeader;
    JPanel panelInputGrid = new JPanel(new GridLayout(2, 2));
    JTextField titleField = new JTextField();
    JTextArea descriptionField = new JTextArea();
    JScrollPane descriptionScrollPane = new JScrollPane(descriptionField);
    JLabel jLabelDescription = new JLabel();

    //Set up label and frames for all other input boxes
    Border borderInputBoxes;
    CalendarFrame calFrameDueDate = new CalendarFrame();
    String[] assignee = {Local.getString("Select"), Local.getString("Student"), Local.getString("TA"), Local.getString("Teacher")};
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel panelAssignTo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel panelDueDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel labelDueDate = new JLabel();
    JButton setStartDateB = new JButton();
    JSpinner dueDate;
    JComboBox cbAssignee = new JComboBox(assignee);
    JLabel labelAssignTo = new JLabel();

    //Set up things for the courses dropdown
    //JComboBox cbCourses = new JComboBox();  
    JLabel labelCourses = new JLabel();
    JPanel panelCourses = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
	//Forbid to set dates outside the bounds
	CalendarDate dueDateMin = currentCourse.get().getCourseStartDate();
	CalendarDate dueDateMax = currentCourse.get().getCourseEndDate();
	
	//Setup the output label to correct user input
	JTextArea labelOutput = new JTextArea(2,18);
	JPanel panelOutput = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	//TODO Remove this, testing purposes only
	//ArrayList<Course> courses = new ArrayList<Course>();
	
	//TODO Remove this, when we have a place to actually store assignments
	//ArrayList<Assignment> assignments = new ArrayList<Assignment>();
	
	/*
	 *Constructor for the AssignmentDialog class. Opens a new Dialog box and waits for input.
	 * Passes info to the superclass constructor
	 *
	 *@param frame the frame to open this dialog box in
	 *@param title the title of the frame
	 */
    public AssignmentDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();            
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }
    
    /*
	 *Initializes the dialog window
	 */
    void jbInit() throws Exception {
    	//Set up window properties
    	this.setResizable(false);
		this.setSize(new Dimension(430,300));
        borderNotTitle = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        borderInfoArea = BorderFactory.createEtchedBorder(Color.white, 
            new Color(142, 142, 142));
        borderTitleDesc = new TitledBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0), Local.getString("Title"), TitledBorder.LEFT, TitledBorder.BELOW_TOP);
        borderHeader = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        borderInputBoxes = BorderFactory.createEtchedBorder(Color.white, new Color(178, 178, 178));
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(borderHeader);
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("Assignment Creator"));
        header.setIcon(new ImageIcon(main.java.flashcourse.ui.AssignmentDialog.class.getResource("/ui/icons/task48.png")));
        
        //Set up cancel button
        buttonCancel.setMaximumSize(new Dimension(100, 26));
        buttonCancel.setMinimumSize(new Dimension(100, 26));
        buttonCancel.setPreferredSize(new Dimension(100, 26));
        buttonCancel.setText(Local.getString("Cancel"));
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelButton_actionPerformed(e);
            }
        });
		
        //set up create button
        buttonCreate.setMaximumSize(new Dimension(100, 26));
        buttonCreate.setMinimumSize(new Dimension(100, 26));
        buttonCreate.setPreferredSize(new Dimension(100, 26));
        buttonCreate.setText(Local.getString("Create"));
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createButton_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(buttonCreate);
        panelMain.setBorder(borderNotTitle);
        panelInputFields.setBorder(borderInfoArea);
       
        
        GridBagLayout gbLayout = (GridBagLayout) panelTitleDesc.getLayout();
        panelTitleDesc.setBorder(borderTitleDesc);
				
        //Set up text fields area
        titleField.setBorder(borderInputBoxes);
        titleField.setPreferredSize(new Dimension(375, 24));
        GridBagConstraints gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbLayout.setConstraints(titleField,gbCon);
        jLabelDescription.setMaximumSize(new Dimension(100, 16));
        jLabelDescription.setMinimumSize(new Dimension(60, 16));
        jLabelDescription.setText(Local.getString("Description"));
        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 1;
        gbCon.anchor = GridBagConstraints.WEST;
        gbLayout.setConstraints(jLabelDescription,gbCon);

        descriptionField.setBorder(borderInputBoxes);
        descriptionField.setPreferredSize(new Dimension(375, 387)); // 3 additional pixels from 384 so that the last line is not cut off
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        gbCon = new GridBagConstraints();
        gbCon.gridwidth = GridBagConstraints.REMAINDER;
        gbCon.weighty = 3;
        descriptionScrollPane.setPreferredSize(new Dimension(375,96));
        gbLayout.setConstraints(descriptionScrollPane,gbCon);
        
        //Set up due date area
        dueDate = new JSpinner(new SpinnerDateModel(new Date(),null,null,Calendar.DAY_OF_WEEK));
        dueDate.setBorder(borderInputBoxes);
        dueDate.setPreferredSize(new Dimension(80, 24));                
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf = (SimpleDateFormat)DateFormat.getDateInstance(DateFormat.SHORT);
		dueDate.setEditor(new JSpinner.DateEditor(dueDate, sdf.toPattern()));
		
        labelDueDate.setText(Local.getString("Due date:"));
        labelDueDate.setMinimumSize(new Dimension(60, 16));
        labelDueDate.setMaximumSize(new Dimension(100, 16));
        setStartDateB.setMinimumSize(new Dimension(24, 24));
        setStartDateB.setPreferredSize(new Dimension(24, 24));
        setStartDateB.setText("");
        setStartDateB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/calendar.png")));
        setStartDateB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDueDateButton_actionPerformed(e);
            }
        });
        
        //Set up assign-to area
        labelAssignTo.setMaximumSize(new Dimension(100, 16));
        labelAssignTo.setMinimumSize(new Dimension(60, 16));
        labelAssignTo.setText(Local.getString("Assign to:"));
        cbAssignee.setFont(new java.awt.Font("Dialog", 0, 11));
        panelAssignTo.add(labelAssignTo, null);
        
        //Set up courses area
        //getCourses();
//        labelCourses.setMaximumSize(new Dimension(100, 16));
//        labelCourses.setMinimumSize(new Dimension(60, 16));
//        labelCourses.setText(Local.getString("Course:"));
//        cbCourses.setFont(new java.awt.Font("Dialog", 0, 11));
//        panelCourses.add(labelCourses, null);
//        panelCourses.add(cbCourses, null);
//        cbCourses.addActionListener (new ActionListener() {
//        	public void actionPerformed (ActionEvent e) {
//        		setDueDateLimit(((JComboBox)e.getSource()).getSelectedItem().toString());
//        	}
//        	
//        });//all of this code is unnecessary since it's now based on the currently selected course
        
        //Setup output field and panel
        panelOutput.add(labelOutput);
        labelOutput.setText("Enter information and click \"Create\"");
        labelOutput.setWrapStyleWord(true);
        labelOutput.setLineWrap(true);
        
        //Setup main panel stuff
        getContentPane().add(panelMain);
        panelMain.add(panelInputFields, BorderLayout.CENTER);
        panelMain.add(panelButtons, BorderLayout.SOUTH);
        panelButtons.add(buttonCreate, null);
        panelButtons.add(buttonCancel, null);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        dialogTitlePanel.add(header, null);
        panelInputFields.add(panelTitleDesc, BorderLayout.NORTH);
        panelTitleDesc.add(titleField, null);
        panelTitleDesc.add(jLabelDescription);
        panelTitleDesc.add(descriptionScrollPane, null);
        panelInputFields.add(panelInputGrid, BorderLayout.CENTER);
        panelInputGrid.add(panelDueDate, null);
        panelDueDate.add(labelDueDate, null);
        panelDueDate.add(dueDate, null);
        panelDueDate.add(setStartDateB, null);

        panelInputGrid.add(panelCourses, null);
        panelInputGrid.add(panelOutput, null);
        panelInputGrid.add(panelAssignTo, null);
        panelAssignTo.add(cbAssignee, null);
        
        cbAssignee.setSelectedItem(Local.getString("Select"));
        calFrameDueDate.cal.addSelectionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ignoreStartChanged)
                    return;
                dueDate.getModel().setValue(calFrameDueDate.cal.get().getCalendar().getTime());
            }
        });
    }

    /*
	 *Gets the list of courses currently created 
	 * TODO actually implement this method, currently returns a dummy list.
	 *
	 */
//    private void getCourses() {//this whole method is unnecessary now
////    	Course c1 = new Course("SER322");
////    	Course c2 = new Course("SER310");
////    	Course c3 = new Course("SER352");
//
////    	courses.put(c1.getCourseName(), c1);
////    	courses.put(c2.getCourseName(), c2);
////    	courses.put(c3.getCourseName(), c3);
//    	Courses temp = CourseList.getCourses();
//    	courses = new ArrayList<Course>(temp.getCourses());
//    	
////    	cbCourses.removeAllItems();
////    	cbCourses.addItem("Select");
////    	for (Course c : courses) {
////    	    cbCourses.addItem(c.toString());
////    	}
////    	cbCourses.setSelectedItem("Select");
//    }
    
    /*
	 *Sets the due date of the assignment being created
	 *
	 *@param d CalenderDate to set due date to
	 */
	public void setDueDate(CalendarDate d) {
		this.dueDate.getModel().setValue(d.getDate());
	}
	
	/*
	 *Sets the min and max possible dates for the due date of this assignment. The min should be the current day or
	 * course start date if it hasnt started, the max should be the end of the selected course
	 *
	 *@param min minimum date for a due date
	 *@param max maximum date for a due date
	 */
	public void setDueDateLimit(String course) {
		Course c = currentCourse.get();
		this.dueDateMin = c.getCourseStartDate();
		this.dueDateMax = c.getCourseEndDate();
	}
	
	/*
	 *Attempts to create a new assignment for the user, gives errors if unsuccessful, closes frame if successful
	 *
	 *@param action of clicking the cancel button
	 */
    void createButton_actionPerformed(ActionEvent e) {
    	CalendarDate selectedDate = new CalendarDate(new SimpleDateFormat("dd/MM/yyyy").format(dueDate.getValue()));
    	//Gross hack to get an actual current date because the CurrentDate.get() method returns a 0 based index month
    	CalendarDate currentDate = new CalendarDate(CurrentDate.get().getDay(), CurrentDate.get().getMonth()+1, CurrentDate.get().getYear());
    	Course c = currentCourse.get();//sorry :(

    	//Check for all possible error in users selections
		if (titleField.getText().equals("")) {
			labelOutput.setText("Please enter a title for the assignment");
		} else if (descriptionField.getText().equals("")) {
			labelOutput.setText("Please enter a description for the assignment");
		} else if (c.toString().equals("Select")) {
			labelOutput.setText("Please select what class this assignment is for");
		} else if (cbAssignee.getSelectedItem().toString().equals("Select")) {
			labelOutput.setText("Please select who this assignment is for");
		} else if (dueDateMax.before(currentDate)){
			labelOutput.setText("This class has already ended");
		} else  if (selectedDate.before(currentDate)){
			labelOutput.setText("This date has already passed");
		} else if (selectedDate.before(dueDateMin)) {
			labelOutput.setText("This due date is before this class starts");
		} else  if (dueDateMax.before(selectedDate)){
			labelOutput.setText("This due date is after the class ends");
		} else {
			//If all checks succeed, create the new assignment.
			c.addAssignment(new Assignment(
					c,
					selectedDate,
					ASSIGNEDGROUP.valueOf(cbAssignee.getSelectedItem().toString()),
					titleField.getText(),
					descriptionField.getText()));
			this.dispose();
		}
    }

    /*
	 *Discards all changes the user has made
	 *
	 *@param action of clicking the cancel button
	 */
    void cancelButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }

    /*
	 *Opens the mini calendar frame for date selection
	 *
	 *@param action of clicking on the calender icon to open mini calendar
	 */
    void setDueDateButton_actionPerformed(ActionEvent e) {
        calFrameDueDate.setLocation(setStartDateB.getLocation());
        calFrameDueDate.setSize(200, 200);
        this.getLayeredPane().add(calFrameDueDate);
        calFrameDueDate.show();
    }
}