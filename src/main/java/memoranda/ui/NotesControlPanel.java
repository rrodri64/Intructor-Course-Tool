package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
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
import main.java.flashcourse.Courses;
import main.java.memoranda.CurrentCourse;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.Note;
import main.java.memoranda.ui.NotesList;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.util.Configuration;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;

/*$Id: NotesControlPanel.java,v 1.16 2005/05/05 16:19:16 ivanrise Exp $*/
public class NotesControlPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
 //  SearchPanel searchPanel = new SearchPanel();
   NotesListPanel notesListPanel = new NotesListPanel();
    //BookmarksPanel bookmarksListPanel = new BookmarksPanel();
    JTabbedPane tabbedPane = new JTabbedPane();
    JToolBar toolBar = new JToolBar();

    NotesList notesList = null;

    FlowLayout flowLayout1 = new FlowLayout();
    JButton ppOpenB = new JButton();
    JPanel buttonsPanel = new JPanel();
    JMenuItem ppAddBkmrk = new JMenuItem();
    JMenuItem ppClearNote = new JMenuItem();
//    JMenuItem ppInvertSort = new JMenuItem();
	JCheckBoxMenuItem ppInvertSort = new JCheckBoxMenuItem();
    JPopupMenu notesPPMenu = new JPopupMenu();
    JMenuItem ppOpenNote = new JMenuItem();
    JMenuItem ppRemoveCourse = new JMenuItem();
    
   
    
    public NotesControlPanel() {
        
       
        
        try {
            jbInit();

        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    void jbInit() throws Exception {
        tabbedPane.setFont(new java.awt.Font("Dialog", 1, 10));
        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                tabbedPane_stateChanged(e);
            }
        });
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
        this.setLayout(borderLayout1);
        toolBar.setRequestFocusEnabled(false);
        toolBar.setFloatable(false);

        flowLayout1.setAlignment(FlowLayout.RIGHT);
        flowLayout1.setVgap(0);
        ppOpenB.setMaximumSize(new Dimension(34, 20));
        ppOpenB.setMinimumSize(new Dimension(24, 10));
        ppOpenB.setOpaque(false);
        ppOpenB.setPreferredSize(new Dimension(24, 20));
        ppOpenB.setBorderPainted(false);
        ppOpenB.setFocusPainted(false);
        ppOpenB.setMargin(new Insets(0, 0, 0, 0));
        ppOpenB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppOpenB_actionPerformed(e);
            }
        });
        ppOpenB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/nopen.png")));
        buttonsPanel.setMinimumSize(new Dimension(70, 22));
        buttonsPanel.setOpaque(false);
        //buttonsPanel.setPreferredSize(new Dimension(80, 22));
        buttonsPanel.setRequestFocusEnabled(false);
        buttonsPanel.setLayout(flowLayout1);
        ppAddBkmrk.setFont(new java.awt.Font("Dialog", 1, 11));
        ppAddBkmrk.setText(Local.getString("Set bookmark"));
        ppAddBkmrk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppAddBkmrk_actionPerformed(e);
            }
        });
        ppAddBkmrk.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/addbookmark.png")));
       
        notesPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
        ppOpenNote.setFont(new java.awt.Font("Dialog", 1, 11));
        ppOpenNote.setText(Local.getString("Go to note"));
        ppOpenNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppOpenNote_actionPerformed(e);
            }
        });
        ppOpenNote.setEnabled(false);

        
		
        ppRemoveCourse.setFont(new java.awt.Font("Dialog", 1, 11));
        ppRemoveCourse.setText(Local.getString("Remove Course"));
        ppRemoveCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveCourse_actionPerformed(e);
            }
        });
        ppRemoveCourse.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/removebookmark.png")));
        ppRemoveCourse.setEnabled(true);
        tabbedPane.add(notesListPanel, Local.getString("Courses"));
        //tabbedPane.add(bookmarksListPanel, Local.getString("Bookmarks"));
        //tabbedPane.add(searchPanel, Local.getString("Search"));
        this.add(toolBar, BorderLayout.NORTH);
        buttonsPanel.add(ppOpenB, null);
        toolBar.add(buttonsPanel, null);
        toolBar.addSeparator();        
        this.add(tabbedPane, BorderLayout.CENTER);

        PopupListener lst = new PopupListener();
        notesListPanel.notesList.addMouseListener(lst);
       // bookmarksListPanel.notesList.addMouseListener(lst);
       // searchPanel.notesList.addMouseListener(lst);
        ListSelectionListener lsl = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
		ppSetEnabled();
            }
        };
        notesListPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
       // bookmarksListPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
       // searchPanel.notesList.getSelectionModel().addListSelectionListener(lsl);
        notesList = notesListPanel.notesList;
        notesPPMenu.add(ppOpenNote);
        notesPPMenu.add(ppInvertSort);
        notesPPMenu.addSeparator();        
        notesPPMenu.add(ppAddBkmrk);
        notesPPMenu.add(ppRemoveCourse);
        notesPPMenu.addSeparator();
        notesPPMenu.add(ppClearNote);

		// remove notes using the DEL key
		KeyListener delNotes = new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_DELETE) {
					ppRemoveCourse_actionPerformed(null);
				}
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		};
		
		notesListPanel.notesList.addKeyListener(delNotes);
	//	bookmarksListPanel.notesList.addKeyListener(delNotes);
	//	searchPanel.notesList.addKeyListener(delNotes);
    }

    public void refresh() {
        notesListPanel.notesList.update();
      //  bookmarksListPanel.notesList.update();
    }

    void tabbedPane_stateChanged(ChangeEvent e) {
	if(notesList!=null) notesList.clearSelection();
	 notesList = notesListPanel.notesList;
	ppAddBkmrk.setEnabled(false);
	ppRemoveCourse.setEnabled(false);
    }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2)
                setActiveNote();
        }

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

    void setActiveNote() {
        Course course = (Course) notesList.getCourse(notesList.getSelectedIndex());
        CurrentDate.set(course.getCourseStartDate());
		CurrentCourse.set(course,true);
    }
   

    void ppOpenB_actionPerformed(ActionEvent e) {
        notesPPMenu.show(
            toolBar,
            (int) ppOpenB.getLocation().getX(),
            (int) ppOpenB.getLocation().getY() + 24);
    }

    void ppAddBkmrk_actionPerformed(ActionEvent e) {
        for (int i = 0; i < notesList.getSelectedIndices().length; i++) {
            Course course = (Course) notesList.getCourse(notesList.getSelectedIndices()[i]);
            course.setMark(true);
        }
        notesList.updateUI();
     //   bookmarksListPanel.notesList.update();
	ppSetEnabled();
    }

   
        
	
    void ppOpenNote_actionPerformed(ActionEvent e) {
        setActiveNote();
    }

    void ppInvertSort_actionPerformed(ActionEvent e) {
		Configuration.put(
			"NOTES_SORT_ORDER",
			new Boolean(ppInvertSort.isSelected()));
		Configuration.saveConfig();
        notesList.invertSortOrder();
        notesList.update();
    }

    void ppRemoveCourse_actionPerformed(ActionEvent e) {
        String msg;
        if (notesList.getSelectedIndices().length > 1)
            msg =
                Local.getString(Local.getString("Clear"))
                    + " "
                    + notesList.getSelectedIndices().length
                    + " "
                    + Local.getString("courses")
                    + "\n"
                    + Local.getString("Are you sure?");
        else
            msg =
                Local.getString("Clear course")
                    + "\n'"
                    + ((Course) notesList.getCourse(notesList.getSelectedIndex())).toString()
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
        for (int i = 0; i < notesList.getSelectedIndices().length; i++) {
            Course course = (Course) notesList.getCourse(notesList.getSelectedIndices()[i]);
             notesList.getCourses(i).removeCourse(course);
        }
       notesListPanel.notesList.update();
	ppSetEnabled();
        notesList.updateUI();
	notesList.clearSelection();
	((AppFrame)App.getFrame()).workPanel.dailyItemsPanel.editorPanel.editor.requestFocus();	
    }

    void ppSetEnabled() {
    boolean enbl = (notesList.getModel().getSize() > 0) && (notesList.getSelectedIndex() > -1);

    ppRemoveCourse.setEnabled(enbl
    				|| notesList.getSelectedIndices().length > 1);
    ppAddBkmrk.setEnabled(enbl 
    				|| notesList.getSelectedIndices().length > 1);
    ppOpenNote.setEnabled(enbl);
    ppClearNote.setEnabled(enbl);
    }
}