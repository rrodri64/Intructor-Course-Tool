
package main.java.memoranda.ui;
import main.java.flashcourse.Course;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;

import java.awt.Container;

import java.awt.Desktop;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;


public class DocumentDisplayTeacher extends JPanel implements ActionListener {

    JFileChooser chooser;
    static private final String newline = "\n";
    JButton chooseFile;
    JButton saveFile;
    JTextArea log;
    Desktop deskTop;
    Course SER321;

    Course SER222;

    
    public DocumentDisplayTeacher() {
        super(new BorderLayout());
        
        SER321 = new Course("SER321");

        SER222 = new Course("SER222");

        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        chooser = new JFileChooser();
        deskTop = Desktop.getDesktop();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooseFile = new JButton("Select file...");
        chooseFile.addActionListener(this);
        
        saveFile = new JButton("Save File to Class");
        saveFile.addActionListener(this);
        
        JPanel buttonPanel = new JPanel();

        JPanel linkPanel = new JPanel();

        buttonPanel.add(chooseFile);
        buttonPanel.add(saveFile);
        
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);

        add(linkPanel, BorderLayout.SOUTH);

    }
    
    public  void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseFile) {
            int returnVal = chooser.showOpenDialog(DocumentDisplayTeacher.this);
            
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    deskTop.open(file);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                log.append("Opening: " + file.getName() + "." + newline);

            }
            log.setCaretPosition(log.getDocument().getLength());
        } else if (e.getSource() == saveFile) {
            int returnVal = chooser.showSaveDialog(DocumentDisplayTeacher.this);

          
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
               boolean add = true;
               
                
                    if(add == true) {
                     
                        JLabel hyperlink = new JLabel(file.getName());
                        hyperlink.setForeground(Color.blue.darker());
                        hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        this.add(hyperlink);
                        hyperlink.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                try {
                                    deskTop.open(file);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                                );
                    }
                log.append("Saving: + " + file.getName() + "." + newline);
                

            }
            log.setCaretPosition(log.getDocument().getLength());
        }
    }
    
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imageURL = DocumentDisplayTeacher.class.getResource(path);
            if(imageURL != null) {
                return new ImageIcon(imageURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        
    }
    private static void init() {
        JPanel frame = new JPanel();
       
        
        frame.add(new DocumentDisplayTeacher());
      
        frame.setVisible(true);
        
        
    }
}



//    public static void main(String[] args) {
//          SwingUtilities.invokeLater(new Runnable() {
//              public void run() {
//                  UIManager.put("swing.boldMetal", Boolean.FALSE);
//                  init();
//              }
//          });
//    }

