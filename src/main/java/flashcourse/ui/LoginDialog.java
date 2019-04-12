package main.java.flashcourse.ui;

/**
 * This class creates a Login page so that various
 * types of users can login to access certain
 * Flash-Course Planning Tool resources.
 *
 * Completion time: 1.5 hours
 *
 * @author Rebecca Rodriguez
 * @version 1.0 (4/8/19)
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginDialog extends JFrame implements ActionListener{
    JLabel l1, l2, l3;
    JTextField u1;
    JButton btn1;
    JPasswordField p1;

    LoginDialog() {
        JFrame frame = new JFrame("Login");
        l1 = new JLabel("Flash-Course Planning Tool Login");
        l1.setForeground(Color.black);
        l1.setFont(new Font("SansSerif", Font.BOLD, 20));

        l2 = new JLabel("Username: ");
        l3 = new JLabel("Password: ");
        u1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");

        l1.setBounds(100, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        u1.setBounds(200, 70, 200, 30);
        p1.setBounds(200, 110, 200, 30);
        btn1.setBounds(150, 160, 100, 30);

        frame.add(l1);
        frame.add(l2);
        frame.add(u1);
        frame.add(l3);
        frame.add(p1);
        frame.add(btn1);

        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    /* Added login page, uncomment to test until setup
     */
    public void actionPerformed(ActionEvent ae)
    {
        String uname = u1.getText();
        String pass = p1.getText();
        if(uname.equals("teacher") && pass.equals("ser316B"))
        {
            LoginOk wel = new LoginOk();
            wel.setVisible(true);
            JLabel label = new JLabel("Welcome:"+uname);
            wel.getContentPane().add(label);
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
                    "Error",JOptionPane.ERROR_MESSAGE); 
        }
    }
    public static void main(String[] args) {
        new LoginDialog();
    } 
} 