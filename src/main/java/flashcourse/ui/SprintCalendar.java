/**SprintCalendar.java is a flashcourse class to manage the main calendar in the sprint tab
 * @author Bryan Culver
 * @Version 1.0 25 April 2019
 */

package main.java.flashcourse.ui;

import javax.swing.JPanel;

import main.java.memoranda.ui.DailyItemsPanel;
import main.java.memoranda.ui.ExceptionDialog;

/**
 * @author Bryan Culver
 *
 */
public class SprintCalendar extends JPanel {
    DailyItemsPanel parentPanel;
    
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
        // TODO Auto-generated method stub
        
    }

}
