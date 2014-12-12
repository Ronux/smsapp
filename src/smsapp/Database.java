/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smsapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jefloresca
 */
class Database {
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public void readDataBase()
    {
      try
      {
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection("jdbc:mysql://localhost/sec?"
              + "user=root&password=");
          // statements allow to issue SQL queries to the database
         statement = connect.createStatement();
         // resultSet gets the result of the SQL query
         resultSet = statement.executeQuery("SELECT * FROM sec.users");
         writeResultSet(resultSet);
        
      }
      catch(Exception e)
      {
         System.out.print(e); 
      } 
        
    }
    
    public void insertLogs(String number, String message) throws Exception
    {
      try
      {
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection("jdbc:mysql://localhost/sec?"
              + "user=root&password=");
         
        //Insert Logs
        preparedStatement = connect.prepareStatement("INSERT INTO sec.smslogs VALUES (default, ?, ?)");
        preparedStatement.setString(1, number);
        preparedStatement.setString(2, message);
        preparedStatement.executeUpdate();
        
        try
        {
           System.out.printf("Log successfully inserted."); 
        }
        catch(Exception ex)
        {
            throw ex;
        }
        
       
      }
      catch(Exception e)
      {
         System.out.print(e); 
      }  
    }
    
    private void writeResultSet(ResultSet resultSet) throws SQLException {
    // resultSet is initialised before the first data set
    while (resultSet.next()) {
      // it is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g., resultSet.getSTring(2);
      String id = resultSet.getString("id");
      String name = resultSet.getString("name");
      String email = resultSet.getString("email");
      String password = resultSet.getString("password");
      String enc_type = resultSet.getString("enc_type");
      System.out.println("ID: " + id);
      System.out.println("Name: " + name);
      System.out.println("Email: " + email);
      System.out.println("Password: " + password);
      System.out.println("ENC Type: " + enc_type);
    }
  }
   
}
