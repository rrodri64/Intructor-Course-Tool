package main.java.flashcourse.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.flashcourse.Course;
import main.java.memoranda.ui.App;
import main.java.memoranda.ui.AppFrame;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.util.Local;

/*$Id: NotesControlPanel.java,v 1.16 2005/05/05 16:19:16 ivanrise Exp $*/
public class CourseControlPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
 //  SearchPanel searchPanel = new SearchPanel();
   CourseListPanel courseListPanel = new CourseListPanel();
    //BookmarksPanel bookmarksListPanel = new BookmarksPanel();
    JTabbedPane tabbedPane = new JTabbedPane();
    JToolBar toolBar = new JToolBar();

    CourseList courseList = null;
   
    FlowLayout flowLayout1 = new FlowLayout();
    JButton courseMgtB = new JButton();
    JPanel buttonsPanel = new JPanel();
    JMenuItem ppAddBkmrk = new JMenuItem();
    JMenuItem ppClearNote = new JMenuItem();
//    JMenuItem ppInvertSort = new JMenuItem();
    JCheckBoxMenuItem ppInvertSort = new JCheckBoxMenuItem();
    JPopupMenu notesPPMenu = new JPopupMenu();
    JPopupMenu courseControlMenu = new JPopupMenu();
    JMenuItem ppOpenNote = new JMenuItem();
    JMenuItem ppRemoveCourse = new JMenuItem();
    JMenuItem removeCourseFromSelector = new JMenuItem();
    JMenuItem addCourse = new JMenuItem();
    
   
    
    public CourseControlPanel() {
        
        try {
            jbInit();

        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        tabbedPane.setFont(new java.awt.Font("Dialog", 1, 10));
       
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
        this.setLayout(borderLayout1);
        toolBar.setRequestFocusEnabled(false);
        toolBar.setFloatable(false);

        flowLayout1.setAlignment(FlowLayout.RIGHT);
        flowLayout1.setVgap(0);
        courseMgtB.setMaximumSize(new Dimension(34, 20));
        courseMgtB.setMinimumSize(new Dimension(24, 10));
        courseMgtB.setOpaque(false);
        courseMgtB.setPreferredSize(new Dimension(24, 20));
        courseMgtB.setBorderPainted(false);
        courseMgtB.setFocusPainted(false);
        courseMgtB.setMargin(new Insets(0, 0, 0, 0));
        courseMgtB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                courseMgtB_actionPerformed(e);
            }
        });
        courseMgtB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/nopen.png")));
        buttonsPanel.setMinimumSize(new Dimension(70, 22));
        buttonsPanel.setOpaque(false);
        //buttonsPanel.setPreferredSize(new Dimension(80, 22));
        buttonsPanel.setRequestFocusEnabled(false);
        buttonsPanel.setLayout(flowLayout1);
        
        addCourse.setFont(new java.awt.Font("Dialog", 1, 11));
        addCourse.setText(Local.getString("Add New Course"));
        addCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse_actionPerformed(e);
            }
        });
        
       
        
        addCourse.setIcon(
                new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/addbookmark.png")));
        addCourse.setEnabled(true);
        
        removeCourseFromSelector.setFont(new java.awt.Font("Dialog", 1, 11));
        removeCourseFromSelector.setText(Local.getString("Remove Course"));
        removeCourseFromSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveCourse_actionPerformed(e);
            }
        });
        removeCourseFromSelector.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Delete.png")));
        removeCourseFromSelector.setEnabled(true);
       
        ppRemoveCourse.setFont(new java.awt.Font("Dialog", 1, 11));
        ppRemoveCourse.setText(Local.getString("Remove Course"));
        ppRemoveCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveCourse_actionPerformed(e);
            }
        });
        ppRemoveCourse.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Delete.png")));
        ppRemoveCourse.setEnabled(true);
        tabbedPane.add(courseListPanel, Local.getString("Courses"));
        //tabbedPane.add(bookmarksListPanel, Local.getString("Bookmarks"));
        //tabbedPane.add(searchPanel, Local.getString("Search"));
        this.add(toolBar, BorderLayout.NORTH);
        buttonsPanel.add(courseMgtB, null);
        toolBar.add(buttonsPanel, null);
        toolBar.addSeparator();        
        this.add(tabbedPane, BorderLayout.CENTER);

        PopupListener lst = new PopupListener();
        courseListPanel.courseList.addMouseListener(lst);
       // bookmarksListPanel.notesList.addMouseListener(lst);
       // searchPanel.notesList.addMouseListener(lst);
       
       // notesListPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
       // bookmarksListPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
       // searchPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
        courseList = courseListPanel.courseList;
     
        notesPPMenu.add(ppRemoveCourse);
        
        
        courseControlMenu.add(removeCourseFromSelector);
        courseControlMenu.add(addCourse);

        // remove notes using the DEL key
        KeyListener delNotes = new KeyListener() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_DELETE) {
                    ppRemoveCourse_actionPerformed(null);
                }
            }
            public void keyReleased(KeyEvent e){}
            public void keyTyped(KeyEvent e){} 
        };
        
        courseListPanel.courseList.addKeyListener(delNotes);
    //  bookmarksListPanel.notesList.addKeyListener(delNotes);
    //  searchPanel.notesList.addKeyListener(delNotes);
    }

    protected void addCourse_actionPerformed(ActionEvent e) {
        CourseDialog dlg = new CourseDialog(App.getFrame(), Local.getString("Create a new Course"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
    
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        
             Course SER111 = new Course("SER111");
             courseList.getCourses().addCourse(SER111);
        
            
       courseListPanel.courseList.update();
    ppSetEnabled();
        courseList.updateUI();
    courseList.clearSelection();
    ((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.editorPanel.editor.requestFocus(); 
    }
        

    public void refresh() {
        courseListPanel.courseList.update();
      //  bookmarksListPanel.notesList.update();
    }

    void tabbedPane_stateChanged(ChangeEvent e) {
    if(courseList!=null) courseList.clearSelection();
     courseList = courseListPanel.courseList;
    ppAddBkmrk.setEnabled(false);
    ppRemoveCourse.setEnabled(false);
    }

    class PopupListener extends MouseAdapter {
        

        public void mousePressed(MouseEvent e) {
             maybeShowPopup(e);
         }
        
         public void mouseReleased(MouseEvent e) {
             maybeShowPopup(e);
         }
        
         private void maybeShowPopup(MouseEvent e) {
             if (e.isPopupTrigger()) {
                 notesPPMenu.show(e.getComponent(), e.getX(), e.getY());
             }
         }
    }

  
    void courseMgtB_actionPerformed(ActionEvent e) {
        courseControlMenu.show(
            toolBar,
            (int) courseMgtB.getLocation().getX(),
            (int) courseMgtB.getLocation().getY() + 24);
    }

    void ppRemoveCourse_actionPerformed(ActionEvent e) {
        String msg;
        if (courseList.getSelectedIndices().length > 1)
            msg =
                Local.getString(Local.getString("Clear"))
                    + " "
                    + courseList.getSelectedIndices().length
                    + " "
                    + Local.getString("courses")
                    + "\n"
                    + Local.getString("Are you sure?");
        else
            msg =
                Local.getString("Clear course")
                    + "\n'"
                    + ((Course) courseList.getCourse(courseList.getSelectedIndex())).toString()
                    + "'\n"
                    + Local.getString("Are you sure?");

        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Delete Course"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        for (int i = 0; i < courseList.getSelectedIndices().length; i++) {
            Course course = (Course) courseList.getCourse(courseList.getSelectedIndices()[i]);
             courseList.getCourses(i).removeCourse(course);
        }
       courseListPanel.courseList.update();
    ppSetEnabled();
        courseList.updateUI();
    courseList.clearSelection();
    ((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.editorPanel.editor.requestFocus(); 
    }

    void ppSetEnabled() {
    boolean enbl = (courseList.getModel().getSize() > 0) && (courseList.getSelectedIndex() > -1);

    ppRemoveCourse.setEnabled(enbl
                    || courseList.getSelectedIndices().length > 1);
    
    }
}