/**
 * TaskTableModel.java         
 * -----------------------------------------------------------------------------
 * Project           Memoranda
 * Package           net.sf.memoranda.ui
 * Original author   Alex V. Alishevskikh
 *                   [alexeya@gmail.com]
 * Created           18.05.2005 15:16:11
 * Revision info     $RCSfile: TaskTableModel.java,v $ $Revision: 1.7 $ $State: Exp $  
 *
 * Last modified on  $Date: 2005/12/01 08:12:26 $
 *               by  $Author: alexeya $
 * 
 * @VERSION@ 
 *
 * @COPYRIGHT@
 * 
 * @LICENSE@ 
 */

package main.java.memoranda.ui;

import javax.swing.event.EventListenerList;
import javax.swing.tree.TreePath;

// import main.java.flashcourse.Task;
import main.java.flashcourse.ASSIGNEDGROUP;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.Project;
import main.java.memoranda.Task;
import main.java.memoranda.date.CurrentDate;
import main.java.memoranda.ui.treetable.AbstractTreeTableModel;
import main.java.memoranda.ui.treetable.TreeTableModel;
import main.java.memoranda.util.Context;
import main.java.memoranda.util.Local;

//import java.util.Hashtable;

/**Class is utilized for application in "Assignments" tab. 
 * JAVADOC:
 * <h1>TaskTableModel</h1>
 * 
 * @version $Id: TaskTableModel.java,v 1.7 2005/12/01 08:12:26 alexeya Exp $
 * @author $Author: alexeya $
 */
public class TaskTableModel extends AbstractTreeTableModel implements TreeTableModel {

    String[] columnNames = {"", Local.getString("Assignments "),
            Local.getString("Due date"), Local.getString("Assigned to"),
            Local.getString("Status ")};

    protected EventListenerList listenerList = new EventListenerList();

    private boolean activeOnly = check_activeOnly();

    /**Constructor for TaskTableModel calls superclass. 
     * JAVADOC: Constructor of <code>TaskTableModel</code>
     */
    public TaskTableModel() {
        super(CurrentProject.get());
    }

    /**Getter for number of columns. 
     * @see main.java.memoranda.ui.treetable.TreeTableModel#getColumnCount()
     */
    public int getColumnCount() {
        return columnNames.length;
    }

    /**Getter for column name.
     * @see main.java.memoranda.ui.treetable.TreeTableModel#getColumnName(int)
     */
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**This method may not always function with a fully populated list of assignments. 
     * @see main.java.memoranda.ui.treetable.TreeTableModel#getValueAt(java.lang.Object,
     *      int)
     */
    public Object getValueAt(Object node, int column) {
        if (node instanceof Project) {
            return null;
        }
        Task t = (Task) node;
        switch (column) {
            case 0:
                return "";
            case 1:
                return t;
            case 2:
                return t.getStartDate().getDate();
            case 3:
                if (t.getEndDate() == null) {
                    return null;
                } else {
                    return t.getEndDate().getDate();
                }        
            case 4:
                return getPriorityString(t.getPriority());
            case 5:
                return getStatusString(t.getStatus(CurrentDate.get()));
            case 6:            
                //return new Integer(t.getProgress());
                return t;
            case TaskTable.TASK_ID:
                return t.getID();
            case TaskTable.TASK:
                return t;
            default:
                break;
        }
        return "";
    }

    String getStatusString(int status) {
        switch (status) {
            case Task.ACTIVE:
                return Local.getString("Active");
            case Task.DEADLINE:
                return Local.getString("Deadline");
            case Task.COMPLETED:
                return Local.getString("Completed");
            case Task.FAILED:
                return Local.getString("Failed");
            case Task.FROZEN:
                return Local.getString("Frozen");
            case Task.LOCKED:
                return Local.getString("Locked");
            case Task.SCHEDULED:
                return Local.getString("Scheduled");
            default:
                break;
        }
        return "";
    }

    String getPriorityString(int p) {
        switch (p) {
            case Task.PRIORITY_NORMAL:
                return Local.getString("Normal");
            case Task.PRIORITY_LOW:
                return Local.getString("Low");
            case Task.PRIORITY_LOWEST:
                return Local.getString("Lowest");
            case Task.PRIORITY_HIGH:
                return Local.getString("High");
            case Task.PRIORITY_HIGHEST:
                return Local.getString("Highest");
            default:
                break;
        }
        return "";
    }

    /**Returns number of children objects. 
     * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
     */
    public int getChildCount(Object parent) {
        if (parent instanceof Project) {
            if (activeOnly()) {
                return CurrentProject.getTaskList().getActiveSubTasks(null, 
                        CurrentDate.get()).size();
            } else {
                return CurrentProject.getTaskList().getTopLevelTasks().size();
            }
        }
        Task t = (Task) parent;
        if (activeOnly()) {
            return CurrentProject.getTaskList().getActiveSubTasks(t.getID(), 
                    CurrentDate.get()).size();
        } else {
            return t.getSubTasks().size();
        }
    }

    /**Getter method for Child object. 
     * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
     */
    public Object getChild(Object parent, int index) {
        if (parent instanceof Project) {
            if (activeOnly()) {
                return CurrentProject.getTaskList().getActiveSubTasks(null, 
                        CurrentDate.get()).toArray()[index];
            } else {
                return CurrentProject.getTaskList().getTopLevelTasks().toArray()[index];
            }
        }
        Task t = (Task) parent;
        if (activeOnly()) {
            return CurrentProject.getTaskList().getActiveSubTasks(t.getID(), 
                    CurrentDate.get()).toArray()[index];
        } else {
            return t.getSubTasks().toArray()[index];
        }
    }

    /**Returns the class associated with each column. 
     * @see main.java.memoranda.ui.treetable.TreeTableModel#getColumnClass(int)
     */
    public Class getColumnClass(int column) {
        try {
            switch (column) {
                case 0:
                    return TaskTable.class;
                case 1:
                    return TreeTableModel.class;
                case 2:
                    return Class.forName("java.util.Date");
                case 3: 
                    return ASSIGNEDGROUP.class;
                case 4:
                    return Class.forName("java.lang.String");
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void fireTreeStructureChanged() {
        fireTreeStructureChanged(this, new Object[]{getRoot()}, new int[0], new Object[0]);
    }


    /**Update cached data.
     * 
     */
    public void fireUpdateCache() {
        activeOnly = check_activeOnly();
    }

    /** This method can check if the task is active. 
     * @return boolean on active or not
     */
    public static boolean check_activeOnly() {
        Object o = Context.get("SHOW_ACTIVE_TASKS_ONLY");
        if (o == null) {
            return false;
        }
        return o.toString().equals("true");
    }

    public boolean activeOnly() {
        return activeOnly;
    }

    /** Indicates if cell is editable or not. 
     * 
     */
    public boolean isCellEditable(Object node, int column) {
        if (column == 6) {
            return true; 
        }
        return super.isCellEditable(node, column); 
    }

}