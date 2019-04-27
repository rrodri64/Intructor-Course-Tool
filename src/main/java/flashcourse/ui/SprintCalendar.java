/**SprintCalendar.java is a flashcourse class to manage the main calendar in the sprint tab
 * @author Bryan Culver
 * @Version 1.0 25 April 2019
 */

package main.java.flashcourse.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;

import main.java.memoranda.ui.DailyItemsPanel;
import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.ui.JNCalendarPanel;

/**
 * @author Bryan Culver
 *
 */
public class SprintCalendar extends JPanel {
    DailyItemsPanel parentPanel;
    JNCalendarPanel mainCalendar;
    BorderLayout borderLayout1 = new BorderLayout();
    
    public SprintCalendar(DailyItemsPanel _parentPanel) {
        try {
            parentPanel = _parentPanel;
            jbInit();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
        }
    }

    private void jbInit() {
        mainCalendar = new JNCalendarPanel();
        
        mainCalendar.getJnCalendar().setRowHeight(100); // adjusts the vertical size of the days. 
        this.setLayout(borderLayout1);
        this.add(mainCalendar, BorderLayout.CENTER);
        
        
        
    }

}
