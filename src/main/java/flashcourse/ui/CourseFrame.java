package main.java.flashcourse.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import main.java.memoranda.ui.ExceptionDialog;

public class CourseFrame extends JInternalFrame {
	
	public CoursePanel showCourses = new CoursePanel();
	Border border1;
	
	public CourseFrame() {
		
		try {
			init();
		}
		
		catch(Exception e) {
			new ExceptionDialog(e);
		}
		
	}
	
	private void init() throws Exception{
		 border1 = BorderFactory.createLineBorder(Color.gray,1);
		 this.setBorder(border1);
		    showCourses.setPreferredSize(new Dimension(this.getContentPane().getWidth(),
		    		this.getContentPane().getHeight()));
		    this.getContentPane().add(showCourses,  BorderLayout.CENTER);
	}

}
