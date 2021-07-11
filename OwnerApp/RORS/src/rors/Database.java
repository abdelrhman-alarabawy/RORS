/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Abdallah M
 */
public class Database {
    
    
    
    
    
    public Connection connect_to_database()
    {
         try 
         {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } 
         catch (ClassNotFoundException ex) 
         {
             System.out.println(ex.toString());
         }
         
         Connection con=null;
         try {
String url1 = "jdbc:mysql://127.0.0.1:3306/rors";
String server_user = "root";
String server_password = "RORS3904RES33k";
con = DriverManager.getConnection(url1, server_user, server_password);
if (con != null) {
System.out.println("Connected to the database RORS");
}

}
catch (SQLException ex) {
System.out.println("An error occurred. Maybe user/password is invalid");
ex.printStackTrace();
} 
         return con;
    }
}
    

