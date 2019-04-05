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

import main.java.flashcourse.Course;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.date.CalendarDate;
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
    String[] assignee = {Local.getString("Student"), Local.getString("TA"), Local.getString("Teacher")};
    boolean ignoreStartChanged = false;
    boolean ignoreEndChanged = false;
    JPanel panelAssignTo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JPanel panelDueDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel labelDueDate = new JLabel();
    JButton setStartDateB = new JButton();
    JSpinner dueDate;
    JComboBox assigneeCB = new JComboBox(assignee);
    JLabel labelAssignTo = new JLabel();

    //Set up things for the courses dropdown
    JComboBox cbCourses = new JComboBox();  
    JLabel labelCourses = new JLabel();
    JPanel panelCourses = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
	//Forbid to set dates outside the bounds
	CalendarDate dueDateMin = CurrentProject.get().getStartDate();
	CalendarDate dueDateMax = CurrentProject.get().getEndDate();
	
	//Setup the output label to correct user input
	JTextArea labelOutput = new JTextArea(2,18);
	JPanel panelOutput = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
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
        dueDate.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            	// it's an ugly hack so that the spinner can increase day by day
            	SpinnerDateModel sdm = new SpinnerDateModel((Date)dueDate.getModel().getValue(),null,null,Calendar.DAY_OF_WEEK);
            	dueDate.setModel(sdm);

                if (ignoreStartChanged)
                    return;
                ignoreStartChanged = true;
                Date sd = (Date) dueDate.getModel().getValue();
                
				if ((dueDateMax != null) && sd.after(dueDateMax.getDate())) {
					dueDate.getModel().setValue(dueDateMax.getDate());
                    sd = dueDateMax.getDate();
				}
                if ((dueDateMin != null) && sd.before(dueDateMin.getDate())) {
                    dueDate.getModel().setValue(dueDateMin.getDate());
                    sd = dueDateMin.getDate();
                }
                calFrameDueDate.cal.set(new CalendarDate(sd));
                ignoreStartChanged = false;
            }
        });

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
        assigneeCB.setFont(new java.awt.Font("Dialog", 0, 11));
        panelAssignTo.add(labelAssignTo, null);
        
        //Set up courses area
        getCourses();
        labelCourses.setMaximumSize(new Dimension(100, 16));
        labelCourses.setMinimumSize(new Dimension(60, 16));
        labelCourses.setText(Local.getString("Course:"));
        cbCourses.setFont(new java.awt.Font("Dialog", 0, 11));
        panelCourses.add(labelCourses, null);
        panelCourses.add(cbCourses, null);
        
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
        panelAssignTo.add(assigneeCB, null);
        
        assigneeCB.setSelectedItem(Local.getString("Student"));
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
    private void getCourses() {
    	HashMap<String, Course> courses = new HashMap<String, Course>();
    	Course c1 = new Course("SER322");
    	Course c2 = new Course("SER310");
    	Course c3 = new Course("SER352");
    	courses.put(c1.getCourseName(), c1);
    	courses.put(c2.getCourseName(), c2);
    	courses.put(c3.getCourseName(), c3);
    	
    	cbCourses.removeAllItems();
    	for (String key : courses.keySet()) {
    	    cbCourses.addItem(key);
    	}
    	
    	
    }
    
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
	public void setDueDateLimit(CalendarDate min, CalendarDate max) {
		this.dueDateMin = min;
		this.dueDateMax = max;
	}
	
	/*
	 *Attempts to create a new assignment for the user, gives errors if unsuccessful, closes frame if successful
	 *
	 *@param action of clicking the cancel button
	 */
    void createButton_actionPerformed(ActionEvent e) {
	CANCELLED = false;
        this.dispose();
    }

    /*
	 *Discards all changes the user has made
	 *
	 *@param action of clicking the cancel button
	 */
    void cancelButton_actionPerformed(ActionEvent e) {
        this.dispose();
    }
	
    /* Saving in case can be used later
	void chkEndDate_actionPerformed(ActionEvent e) {
		endDate.setEnabled(chkEndDate.isSelected());
		setEndDateB.setEnabled(chkEndDate.isSelected());
		jLabel2.setEnabled(chkEndDate.isSelected());
		if(chkEndDate.isSelected()) {
			Date currentEndDate = (Date) endDate.getModel().getValue();
			Date currentStartDate = (Date) dueDate.getModel().getValue();
			if(currentEndDate.getTime() < currentStartDate.getTime()) {
				endDate.getModel().setValue(currentStartDate);
			}
		}
	}
	*/

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