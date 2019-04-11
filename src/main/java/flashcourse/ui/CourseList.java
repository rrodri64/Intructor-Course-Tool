/**
 * 
 * @author Jessica Tinaza
 * 
 * File Name: CourseList.java
 * 
 * Date 4/7/19
 */



package main.java.flashcourse.ui;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import main.java.flashcourse.Course;
import main.java.flashcourse.Courses;
import main.java.flashcourse.CurrentCourse;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.Note;
import main.java.memoranda.NoteList;
import main.java.memoranda.CourseListener;
import main.java.memoranda.Project;
import main.java.memoranda.ProjectListener;
import main.java.memoranda.ResourcesList;
import main.java.memoranda.TaskList;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.date.DateListener;
import main.java.memoranda.ui.AppFrame;
import main.java.memoranda.util.Configuration;

/**
 * 
 * This class keeps track of the list of courses that are displayed on
 * the course control panel.
 *
 */
/*$Id: NotesList.java,v 1.9 2005/05/05 16:19:16 ivanrise Exp $*/
public class CourseList extends JList {

    public static final int EMPTY = 0;    
    public static final int ALL = 1;
    public static final int BOOKMARKS = 2;

    //Hard coded classes for testing purposes
   private Course SER321 =  new Course("SER321");
   private Course SER334 = new Course("SER334");
   private Course SER222 = new Course("SER222");

   private Courses courseCollection = new Courses();
  
    boolean sortOrderDesc = false;

    int _type = ALL;

    public CourseList(int type) {
        
        super();
        
        courseCollection.addCourse(SER321);
        courseCollection.addCourse(SER222);
        courseCollection.addCourse(SER334);
		if(Configuration.get("NOTES_SORT_ORDER").toString().equalsIgnoreCase("true")) {
			sortOrderDesc = true;
		}
        _type = type;
        this.setFont(new java.awt.Font("Dialog", 0, 11));
        this.setModel(new NotesListModel());
        CurrentDate.addDateListener(new DateListener() {
            public void dateChange(CalendarDate d) {
                updateUI();
            }
        });
		
        CurrentCourse.addCourseListener(new CourseListener() {
            public void courseChange(Course n, boolean toSaveCurrentNote) {
                updateUI();
            }

        
        });

        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
            }
            public void projectWasChanged() {
                update();
            }
        });
        this.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    public CourseList() {
        this(ALL);
    }

    public void update() {
        if (_type != EMPTY) {
            update(CurrentProject.getNoteList());
		}
        else {
			update(new Courses());
		}
    }

    public void update(NoteList nl) {
        ArrayList<Course> courses = courseCollection.getCourses();
//        courses = (Courses) n1.
//        courseCollection =
//        if (_type == ALL)
//            courseCollection = (Courses) nl.getAllNotes();
//        else
//            courseCollection = (Courses) nl.getMarkedNotes();
        
//        Util.debug("No. of courseCollection in noteList " + courseCollection.size());
        //NotesVectorSorter.sort(courseCollection);
	//	Collections.sort((List<T>) courseCollection);
//		if (sortOrderDesc) {
//			Collections.reverse((List<?>) courseCollection);		    
//		}
        updateUI();
    }

    public void update(Courses ns) {
        courseCollection = ns;
        // NotesVectorSorter.sort(courseCollection);
	//Collections.sort((List<T>) courseCollection);
		if (sortOrderDesc) {
			Collections.reverse((List<?>) courseCollection);		    
		}		
        updateUI();
    }
    

    public Course getCourse(String courseName) {
        Course result = null;
        ArrayList<Course> courseLookUp = courseCollection.getCourses();
        for(Course c : courseLookUp) {
            if(c.getCourseName().equals(courseName)) {
                result = c;
            }
        }
        
        return result;
       
    }
    public Course getCourse(int index){
        ArrayList<Course> indexCourse = courseCollection.getCourses();
        
        Course course = indexCourse.get(index);
        System.out.println(course.toString());
        return course;
    }
    
    public Courses getCourses(int index) {
        return courseCollection;
    }
    
    public Courses getCourses() {
        return courseCollection;
    }
    
    void invertSortOrder() {
        sortOrderDesc = !sortOrderDesc;
    }


    /*$Id: NotesList.java,v 1.9 2005/05/05 16:19:16 ivanrise Exp $*/
public class NotesListModel extends AbstractListModel {

        public NotesListModel() {
            update();
        }

        public Object getElementAt(int i) {
            ArrayList<Course> indexCourse = courseCollection.getCourses();
          
            Course course = indexCourse.get(i);
            return course.toString();
        }

        public int getSize() {
            return courseCollection.numOfCourses();
        }

    }



    ImageIcon bookmarkIcon = new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/lightning.png"));

    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer()  {

     public Component getListCellRendererComponent(
       JList list,
       Object value,            // value to display
       int index,               // cell index
       boolean isSelected,      // is the cell selected
       boolean cellHasFocus)    // the list and the cell have the focus
     {
         JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
         String s = value.toString();
         label.setText(s);
         //Note currentNote = CurrentProject.getNoteList().getActiveNote();
		 Course currentCourse = CurrentCourse.get();
//         if (currentNote != null) {
//            if (getNote(index).getId().equals(currentNote.getId()))
//                label.setFont(label.getFont().deriveFont(Font.BOLD));
//         }
//         if (getNote(index).isMarked())
            label.setIcon(bookmarkIcon);
         //setIcon();
       /*if (isSelected) {
             setBackground(list.getSelectionBackground());
           setForeground(list.getSelectionForeground());
       }
         else {
           setBackground(list.getBackground());
           setForeground(list.getForeground());
       }
       setEnabled(list.isEnabled());
       setFont(list.getFont());
         setOpaque(true);*/
         label.setToolTipText(s);
         return label;
     }
    };

 }


}