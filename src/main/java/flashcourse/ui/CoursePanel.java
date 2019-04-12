package main.java.flashcourse.ui;



import main.java.flashcourse.Course;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class CoursePanel extends JFrame {
	public CoursePanel() {
	Course SER316 = new Course("SER316");
	
	DefaultListModel<Course> listModel = new DefaultListModel<>();
	listModel.addElement(SER316);
	
	JList<Course> courseList = new JList<>(listModel);
	add(new JScrollPane(courseList));
	courseList.setCellRenderer(new CourseCellRenderer());
	

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Current Courses");
	this.setSize(200,200);
	this.setLocationRelativeTo(null);
	this.setVisible(true);

}
}


