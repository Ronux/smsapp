/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsapp;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author jefloresca
 */
public class UserInterface {
    
    private JFrame frame;
    private JPanel panel;
    private FlowLayout layout;
    private JLabel senderLabel;
    private JLabel messageLabel;
    private JTextField senderField;
    private JTextArea messageField;
    private JButton send;
    
    public void prepareUI() {
        layout       = new FlowLayout(FlowLayout.LEFT);
        frame        = new JFrame("SMS App");
        panel        = new JPanel();
        senderLabel  = new JLabel("Sender's Number");
        senderField  = new JTextField(13);
        messageLabel = new JLabel("Your Message: ");
        messageField = new JTextArea(6, 25);
        send         = new JButton("Send");
        final Msg message = new Msg();
        send.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    String number =  senderField.getText();   
                    String messageString =  messageField.getText();
                    
                if(!number.equals("")){
                    if(message.validate(number)){
                        message.sendSMS(number, messageString);
                        Database db = new Database();
                        db.insertLogs(number, messageString);
                        messageField.setText("");
                        senderField.setText("");
                    } 
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Invalid Number!");

                    }
                
                }else{
                       JOptionPane.showMessageDialog(frame, "Phone Number Field cannot be empty");
                }
                }
                catch(Exception error){
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
               System.exit(0);
            }        
        });
 
        panel.add(senderLabel);
        panel.add(senderField);
        panel.add(messageLabel);
        panel.add(messageField);
        panel.add(send);
        frame.add(panel);
        frame.setSize(300,350);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
