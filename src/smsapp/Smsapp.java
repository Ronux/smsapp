/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsapp;
/**
 *
 * @author jefloresca
 */

public class Smsapp {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Register reg = new Register();
        SendSms sms = new SendSms();
        sms.setVisible(true);
        //reg.setVisible(true);
        
    }
}
