/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;

/**
 *
 * @author jefloresca
 */
public class Msg{
    
    private String url;
    
    public boolean validate(String number){
        
       long isValid;
       try{
           
         isValid = Long.parseLong(number);
         return true;
           
       }catch(Exception e){
           
           return false;
       }
    }
    
    public void sendSMS(String Number,String Message) throws  UnsupportedEncodingException{
        
        url = "http://joenesms.azurewebsites.net/process.php";
        String data = URLEncoder.encode("number", "UTF-8")
                         + "=" + URLEncoder.encode(Number, "UTF-8");
 
            data += "&" + URLEncoder.encode("message", "UTF-8") + "="
                        + URLEncoder.encode(Message, "UTF-8"); 
            
        BufferedReader reader=null;
        String text="";
        try{
             URL uri = new URL(url);
             URLConnection conn = uri.openConnection(); 
             conn.setDoOutput(true);
             OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
             wr.write( data );
             wr.flush(); 
             
             reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             StringBuilder sb = new StringBuilder();
             String line = null;
          
            // Read Server Response
            while((line = reader.readLine()) != null)
              {
                     // Append server response in string
                     sb.append(line + "\n");
              }
              
              text = sb.toString();
          }
          catch(Exception ex)
          {
               
          }
          finally
          {
              try
              {
                  reader.close();
              }
 
              catch(Exception ex) {
              }
          }
          JOptionPane.showMessageDialog(null, text);
      }  
}
