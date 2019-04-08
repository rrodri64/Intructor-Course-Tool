package main.java.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.util.AppList;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Local;
import main.java.memoranda.util.MimeType;
import main.java.memoranda.util.MimeTypesList;
import main.java.memoranda.util.Util;

import java.io.*;

/*$Id: ResourcesPanel.java,v 1.13 2007/03/20 08:22:41 alexeya Exp $*/
public class DocumentsPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JToolBar toolBar = new JToolBar();
    ResourcesTable resourcesTable = new ResourcesTable();
    JButton removeResB = new JButton();
    JScrollPane scrollPane = new JScrollPane();
    JButton refreshB = new JButton();
    JButton uploadB = new JButton();
  JPopupMenu resPPMenu = new JPopupMenu();
  JMenuItem ppRun = new JMenuItem();
  JMenuItem ppRemoveRes = new JMenuItem();
  JMenuItem ppNewRes = new JMenuItem();
  JMenuItem ppRefresh = new JMenuItem();
  JMenuItem ppUpload = new JMenuItem();
  
  JFileChooser chooser;
  Desktop deskTop;

    public DocumentsPanel() {
        try {
            jbInit();
        }
        catch (Exception ex) {
           new ExceptionDialog(ex);
        }
    }
    void jbInit() throws Exception {
        
        chooser = new JFileChooser();
        deskTop = Desktop.getDesktop();
        toolBar.setFloatable(false);
        this.setLayout(borderLayout1);
        
        
        uploadB.setIcon(
                new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Upload.png")));
        uploadB.setEnabled(true);
        uploadB.setMaximumSize(new Dimension(24, 24));
        uploadB.setMinimumSize(new Dimension(24, 24));
        uploadB.setToolTipText(Local.getString("Upload Document"));
        uploadB.setRequestFocusEnabled(false);
        uploadB.setPreferredSize(new Dimension(24, 24));
        uploadB.setFocusable(false);
        uploadB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadB_actionPerformed(e);
            }
        });
        
        uploadB.setBorderPainted(false);
        
       
        resourcesTable.setMaximumSize(new Dimension(32767, 32767));
        resourcesTable.setRowHeight(24);
        toolBar.addSeparator(new Dimension(8, 24));
        toolBar.addSeparator(new Dimension(8, 24));
        removeResB.setBorderPainted(false);
        removeResB.setFocusable(false);
        removeResB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeResB_actionPerformed(e);
            }
        });
        removeResB.setPreferredSize(new Dimension(24, 24));
        removeResB.setRequestFocusEnabled(false);
        removeResB.setToolTipText(Local.getString("Remove resource"));
        removeResB.setMinimumSize(new Dimension(24, 24));
        removeResB.setMaximumSize(new Dimension(24, 24));
        removeResB.setIcon(
            new ImageIcon(
                main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Delete.png")));
        removeResB.setEnabled(false);
        scrollPane.getViewport().setBackground(Color.white);
        toolBar.addSeparator(new Dimension(8, 24));
        toolBar.addSeparator(new Dimension(8, 24));


        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        resourcesTable.addMouseListener(ppListener);

        resourcesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = (resourcesTable.getRowCount() > 0) && (resourcesTable.getSelectedRow() > -1);

                removeResB.setEnabled(enbl); ppRemoveRes.setEnabled(enbl);
                ppRun.setEnabled(enbl);
            }
        });
        refreshB.setBorderPainted(false);
        refreshB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshB_actionPerformed(e);
            }
        });
        refreshB.setFocusable(false);
        refreshB.setPreferredSize(new Dimension(24, 24));
        refreshB.setRequestFocusEnabled(false);
        refreshB.setToolTipText(Local.getString("Refresh"));
        refreshB.setMinimumSize(new Dimension(24, 24));
        refreshB.setMaximumSize(new Dimension(24, 24));
        refreshB.setEnabled(true);
        refreshB.setIcon(
            new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Refresh.png")));
        resPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
    ppRun.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRun.setText(Local.getString("Open resource")+"...");
    ppRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRun_actionPerformed(e);
            }
        });
    ppRun.setEnabled(false);

    ppRemoveRes.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveRes.setText(Local.getString("Remove resource"));
    ppRemoveRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveRes_actionPerformed(e);
            }
        });
    ppRemoveRes.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Delete.png")));
    ppRemoveRes.setEnabled(false);
    ppNewRes.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewRes.setText(Local.getString("New resource")+"...");
    ppNewRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewRes_actionPerformed(e);
            }
        });
    ppNewRes.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Search.png")));

    ppRefresh.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRefresh.setText(Local.getString("Refresh"));
    ppRefresh.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ppRefresh_actionPerformed(e);
      }
    });
    ppRefresh.setIcon(new ImageIcon(main.java.memoranda.ui.AppFrame.class.getResource("/ui/icons/Refresh.png")));

    toolBar.add(uploadB, null);
    toolBar.addSeparator();
        toolBar.add(removeResB, null);
        toolBar.addSeparator();
        toolBar.add(refreshB, null);
        this.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getViewport().add(resourcesTable, null);
        this.add(toolBar, BorderLayout.NORTH);
    resPPMenu.add(ppRun);
    resPPMenu.addSeparator();
    resPPMenu.add(ppNewRes);
    resPPMenu.add(ppRemoveRes);
    resPPMenu.addSeparator();
    resPPMenu.add(ppRefresh);
	
		// remove resources using the DEL key
		resourcesTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(resourcesTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveRes_actionPerformed(null);
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});
    }

    protected void newResB_actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    void uploadB_actionPerformed(ActionEvent e) {
        TeacherDocumentDialog dlg = new TeacherDocumentDialog(App.getFrame(), Local.getString("Course Document Manager"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        if (dlg.localFileRB.isSelected()) {
            String fpath = dlg.pathField.getText();
            MimeType mt = MimeTypesList.getMimeTypeForFile(fpath);
            if (mt.getMimeTypeId().equals("__UNKNOWN")) {
                mt = addResourceType(fpath);
                if (mt == null)
                    return;
            }
            if (!checkApp(mt))
                return;
            // if file if projectFile, than copy the file and change url.
           
            else
            	CurrentProject.getResourcesList().addResource(fpath);            	     	
            
            resourcesTable.tableChanged();
        }
        else {
            if (!Util.checkBrowser())
                return;
         //   CurrentProject.getResourcesList().addResource(dlg.urlField.getText(), true, false);
            resourcesTable.tableChanged();
        }
    }

    void removeResB_actionPerformed(ActionEvent e) {
        int[] toRemove = resourcesTable.getSelectedRows();
        String msg = "";
        if (toRemove.length == 1)
            msg =
                Local.getString("Remove the shortcut to resource")
                    + "\n'"
                    + resourcesTable.getModel().getValueAt(toRemove[0], 0)
                    + "'";

        else
            msg = Local.getString("Remove") + " " + toRemove.length + " " + Local.getString("shortcuts");
        msg +=
            "\n"
            + Local.getString("Are you sure?");
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove resource"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        for (int i = 0; i < toRemove.length; i++) {        	
        		CurrentProject.getResourcesList().removeResource(
                        ((Resource) resourcesTable.getModel().getValueAt(toRemove[i], ResourcesTable._RESOURCE)).getPath());
        }
        resourcesTable.tableChanged();
    }

    MimeType addResourceType(String fpath) {
        ResourceTypeDialog dlg = new ResourceTypeDialog(App.getFrame(), Local.getString("Resource type"));
        Dimension dlgSize = new Dimension(420, 300);
        dlg.setSize(dlgSize);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.ext = MimeTypesList.getExtension(fpath);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return null;
        int ix = dlg.getTypesList().getSelectedIndex();
        MimeType mt = (MimeType) MimeTypesList.getAllMimeTypes().toArray()[ix];
        mt.addExtension(MimeTypesList.getExtension(fpath));
        CurrentStorage.get().storeMimeTypesList();
        return mt;
    }

    boolean checkApp(MimeType mt) {
        String appId = mt.getAppId();
        AppList appList = MimeTypesList.getAppList();
        File d;
        if (appId == null) {
            appId = Util.generateId();
            d = new File("/");
        }
        else {
            File exe = new File(appList.getFindPath(appId) + "/" + appList.getExec(appId));
            if (exe.isFile())
                return true;
            d = new File(exe.getParent());
            while (!d.exists())
                d = new File(d.getParent());
        }
        SetAppDialog dlg =
            new SetAppDialog(
                App.getFrame(),
                Local.getString(Local.getString("Select the application to open files of type")+" '" + mt.getLabel() + "'"));
        Dimension dlgSize = new Dimension(420, 300);
        dlg.setSize(dlgSize);
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
        dlg.setDirectory(d);
        dlg.appPanel.argumentsField.setText("$1");
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return false;
        File f = new File(dlg.appPanel.applicationField.getText());

        appList.addOrReplaceApp(
            appId,
            f.getParent().replace('\\', '/'),
            f.getName().replace('\\', '/'),
            dlg.appPanel.argumentsField.getText());
        mt.setApp(appId);
        /*appList.setFindPath(appId, chooser.getSelectedFile().getParent().replace('\\','/'));
        appList.setExec(appId, chooser.getSelectedFile().getName().replace('\\','/'));*/
        CurrentStorage.get().storeMimeTypesList();
        return true;
    }
    

    void runApp(String fpath) {
        MimeType mt = MimeTypesList.getMimeTypeForFile(fpath);
        if (mt.getMimeTypeId().equals("__UNKNOWN")) {
            mt = addResourceType(fpath);
            if (mt == null)
                return;
        }
        if (!checkApp(mt))
            return;
        String[] command = MimeTypesList.getAppList().getCommand(mt.getAppId(), fpath);
        if (command == null)
            return;
        /*DEBUG*/
        System.out.println("Run: " + command[0]);
        try {
            Runtime.getRuntime().exec(command);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Failed to run an external application <br><code>"
                    +command[0]+"</code>", "Check the application path and command line parameters for this resource type " +
                    		"(File-&gt;Preferences-&gt;Resource types).");
        }
    }

    void runBrowser(String url) {
        Util.runBrowser(url);
    }

    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if ((e.getClickCount() == 2) && (resourcesTable.getSelectedRow() > -1)) {
                String path = (String) resourcesTable.getValueAt(resourcesTable.getSelectedRow(), 3);
                File fileFromPath = new File(path);
                if (path.length() >0) {
                    try {
                        deskTop.open(fileFromPath);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else
                    runBrowser((String) resourcesTable.getValueAt(resourcesTable.getSelectedRow(), 0));
            }
            //editTaskB_actionPerformed(null);
        }

                public void mousePressed(MouseEvent e) {
                    maybeShowPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    maybeShowPopup(e);
                }

                private void maybeShowPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        resPPMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

    }
    void refreshB_actionPerformed(ActionEvent e) {
        resourcesTable.tableChanged();
    }

  void ppRun_actionPerformed(ActionEvent e) {
    
    String path = (String) resourcesTable.getValueAt(resourcesTable.getSelectedRow(), 3);
    File fileFromPath = new File(path);
                if (path.length() >0)
                    try {
                        deskTop.open(fileFromPath);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                else
                    runBrowser((String) resourcesTable.getValueAt(resourcesTable.getSelectedRow(), 0));
  }
  void ppRemoveRes_actionPerformed(ActionEvent e) {
    removeResB_actionPerformed(e);
  }
  void ppNewRes_actionPerformed(ActionEvent e) {
    newResB_actionPerformed(e);
  }

  void ppRefresh_actionPerformed(ActionEvent e) {
     resourcesTable.tableChanged();
  }
  
  /**
   * Copy a file to the directory of the current project
   * @param srcStr The path of the source file.
   * @param destStr The destination path.
   * @return The new path of the file.
   */
  String copyFileToProjectDir(String srcStr) {
	  
	  String JN_DOCPATH = Util.getEnvDir();	    
	  
	  String baseName;
	  int i = srcStr.lastIndexOf( File.separator );
		if ( i != -1 ) {
			baseName = srcStr.substring(i+1);
		} else
			baseName = srcStr;
		
	  String destStr = JN_DOCPATH + CurrentProject.get().getID() 
	  				   + File.separator + "_projectFiles" + File.separator + baseName;
	  
	  File f = new File(JN_DOCPATH + CurrentProject.get().getID() + File.separator + "_projectFiles");
	  if (!f.exists()) {
		  f.mkdirs();
	  }	  
	  System.out.println("[DEBUG] Copy file from: "+srcStr+" to: "+destStr);
	  
	  try {
         FileInputStream in = new FileInputStream(srcStr);
         FileOutputStream out = new FileOutputStream(destStr);
         byte[] buf = new byte[4096];
         int len;
         while ((len = in.read(buf)) > 0) {
           out.write(buf, 0, len);
         }
         out.close();
         in.close();
       } 
	   catch (IOException e) {
         System.err.println(e.toString());
       }
		     
  return destStr;
  }
}