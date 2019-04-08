package main.java.flashcourse.ui;

import main.java.flashcourse.Course;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;

import main.java.memoranda.ui.ExceptionDialog;
import main.java.memoranda.util.Local;

/*$Id: AddResourceDialog.java,v 1.12 2007/03/20 06:21:46 alexeya Exp $*/

/*
 * @author Jessica Tinaza 
 * This class has been edited to support adding teacher support to add and display documents.
 * The teacher is able to select a file, preview the file, and upload the file to a specified 
 * class.
 */
public class TeacherDocumentDialog extends JDialog {
    Course SER321 = new Course("SER321");
    JPanel dialogTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel header = new JLabel();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    JPanel areaPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc;
    public JRadioButton localFileRB = new JRadioButton();
    JLabel jLabel1 = new JLabel();
    public JTextField pathField = new JTextField();
    JButton browseB = new JButton();
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
    JButton uploadB = new JButton();
    JButton cancelB = new JButton();
    JButton previewB = new JButton();
    public boolean CANCELLED = true;
    JFileChooser chooser = new JFileChooser();
    Desktop deskTop = Desktop.getDesktop();
    File file;
  

    public TeacherDocumentDialog(Frame frame, String title) {
        super(frame, title, true);
        try {
            jbInit();
            pack();
        }
        catch (Exception ex) {
            new ExceptionDialog(ex);
            ex.printStackTrace();
        }
    }

	/**
	 * setup user interface and init dialog
	 */
	 
    void jbInit() throws Exception {
		this.setResizable(false);
        dialogTitlePanel.setBackground(Color.WHITE);
        dialogTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        header.setFont(new java.awt.Font("Dialog", 0, 20));
        header.setForeground(new Color(0, 0, 124));
        header.setText(Local.getString("New Document"));
        header.setIcon(new ImageIcon(main.java.flashcourse.ui.TeacherDocumentDialog.class.getResource(
            "/ui/icons/resource48.png")));
        dialogTitlePanel.add(header);
        this.getContentPane().add(dialogTitlePanel, BorderLayout.NORTH);
        
        buttonGroup1.add(localFileRB);
       
        localFileRB.setSelected(true);
        localFileRB.setText(Local.getString("Local file"));
        localFileRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                localFileRB_actionPerformed(e);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(localFileRB, gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridwidth = 2;
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.insets = new Insets(10, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
      
        
        jLabel1.setText(Local.getString("Path")+": ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 20, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(jLabel1, gbc);
        pathField.setMinimumSize(new Dimension(4, 24));
        pathField.setPreferredSize(new Dimension(250, 24));
        pathField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(CaretEvent e) {
                pathField_caretUpdate(e);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        areaPanel.add(pathField, gbc);
        browseB.setText(Local.getString("Browse"));
        browseB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseB_actionPerformed(e);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
        areaPanel.add(browseB, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
      
      
        gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.insets = new Insets(5, 20, 5, 15);
        gbc.anchor = GridBagConstraints.WEST;
       
      gbc = new GridBagConstraints();
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 0, 15);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
       
        this.getContentPane().add(areaPanel, BorderLayout.CENTER);
        
        //Add preview button to dialog box
        previewB.setEnabled(false);
        previewB.setMaximumSize(new Dimension(100, 26));
        previewB.setMinimumSize(new Dimension(100, 26));
        previewB.setPreferredSize(new Dimension(100, 26));
        previewB.setText(Local.getString("Preview"));
        previewB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previewB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(previewB);
        
        //Edit upload button
        uploadB.setEnabled(false);
        uploadB.setMaximumSize(new Dimension(100, 26));
        uploadB.setMinimumSize(new Dimension(100, 26));
        uploadB.setPreferredSize(new Dimension(100, 26));
        uploadB.setText(Local.getString("Upload"));
        uploadB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uploadB_actionPerformed(e);
            }
        });
        this.getRootPane().setDefaultButton(uploadB);
        cancelB.setMaximumSize(new Dimension(100, 26));
        cancelB.setMinimumSize(new Dimension(100, 26));
        cancelB.setPreferredSize(new Dimension(100, 26));
        cancelB.setText(Local.getString("Cancel"));
        cancelB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelB_actionPerformed(e);
            }
        });
        buttonsPanel.add(previewB);
        buttonsPanel.add(uploadB);
        buttonsPanel.add(cancelB);
        
		enableFields();
        this.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

    //If preview is selected, the file will be opened with most appropriate app
	protected void previewB_actionPerformed(ActionEvent e) {	
            try {
                deskTop.open(file);
            } catch (IOException e1) {
               
                e1.printStackTrace();
            }
         
          
        }
        
   

    /**
	 * set CANCELLED variable to false so we can know the user 
	 * pressed the ok buton and close this dialog.
	 */
	 
    void uploadB_actionPerformed(ActionEvent e) {
        CANCELLED = false;
		this.dispose();
    }

	/**
	 * close the dialog window
	 */
	 
    void cancelB_actionPerformed(ActionEvent e) {
        this.dispose();
    }

	/**
	 * enable localRB fields. Request focus for the text field 
	 * so the user can start typing and set the pathField text selected
	 */
	 
    void localFileRB_actionPerformed(ActionEvent e) {
		enableFields();
        checkOkEnabled();
		pathField.select(0,pathField.getText().length());
		pathField.requestFocus();
	}

	
	
	 
    /*
     * Handle Browse Button action. 
     */
    void browseB_actionPerformed(ActionEvent e) {
       
            int returnVal = chooser.showOpenDialog(TeacherDocumentDialog.this);
            
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                file = chooser.getSelectedFile();
                pathField.setText(file.getAbsolutePath());
               
               
              
            }
          
    }

	/**
	 * disable the ok button if pathField is empty
	 */
	 
    void pathField_caretUpdate(CaretEvent e) {
        checkPreviewEnabled();
        checkOkEnabled();
    }

	
    //Only allow preview to be usable after a file has been selected
    void checkPreviewEnabled() {
        previewB.setEnabled(
                (localFileRB.isSelected() && pathField.getText().length() > 0)
                );
    }
    
	/**
	 * do not enable the ok button until the text field is not empty.
	 */
	 
    void checkOkEnabled() {        
         uploadB.setEnabled(
            (localFileRB.isSelected() && pathField.getText().length() > 0)
            //(inetShortcutRB.isSelected() && urlField.getText().length() > 0)
         );
    }

	/** 
	 * enable and disable fields when user selects the radio buttons options
	 */
	 
	void enableFields() {
		 pathField.setEnabled(localFileRB.isSelected());
		 jLabel1.setEnabled(localFileRB.isSelected());
		 browseB.setEnabled(localFileRB.isSelected());
		
	}
}