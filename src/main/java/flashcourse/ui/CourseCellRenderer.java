package main.java.flashcourse.ui;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import main.java.flashcourse.Course;


public class CourseCellRenderer extends JLabel implements ListCellRenderer<Course> {
	
		public CourseCellRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getListCellRendererComponent(JList<? extends Course> list, Course course, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(course.getCourseName());
			
			if(isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getForeground());
			}
			else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			
			return this;
		}
		
		
		
	

}
