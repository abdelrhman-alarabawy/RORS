/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Abdallah M
 */
public class Login {
    private String username,userpassword;
    private Connection con;
    Login(String user,String pass)
    {
        this.username=user;
        this.userpassword=pass;
        Database database=new Database();
        this.con =database.connect_to_database();
        
    }
    public void close_conection()
    {
        try{con.close();}
        catch(SQLException e){e.toString();}
    }
   
    public boolean validate_username()
    {
        boolean valid =false;
        String DB_username;
        String query="select owner_username from owner";
try(Statement stmt = con.createStatement()) 
    {
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()&&!valid)
      {
        //DB_username = rs.getString("owner_username");
        DB_username = rs.getString("owner_username");
        System.out.println(DB_username);
        if(this.username.equals(DB_username))
            valid=true;
      }
}

catch (SQLException ex) {
System.out.println("An error occurred. Maybe valid user is not working");
ex.printStackTrace();
}
        
        return valid;
    }
    

     public boolean validate_password()
    {
        boolean valid =false;
        String DB_password,DB_username;
        String query="select owner_username,owner_password from owner";
        System.out.println(query);
       
        try(Statement stmt = con.createStatement()) 
    {
      ResultSet rs = stmt.executeQuery(query);
      while(rs.next()&&!valid){
          
          DB_username = rs.getString("owner_username");
          DB_password = rs.getString("owner_password");

      System.out.println(DB_password);
        
        if(this.userpassword.equals(DB_password)&&DB_username.equals(this.username))
            valid=true;
      }
}

catch (SQLException ex) {
System.out.println("An error occurred. Maybe valid password is not workink");
ex.printStackTrace();
}
        
        return valid;
}
    
     public String get_owner_type()
    {
        boolean found=false;
        String type =null;

        String query="select owner_username,owner_type from owner";
try(Statement stmt = con.createStatement()) 
    {
      ResultSet rs = stmt.executeQuery(query);
      while(rs.next()&&!found)  
      { if(this.username.equals(rs.getString("owner_username")))
           {
               type = rs.getString("owner_type");
               System.out.println(type);
               found=true;
            }
      }
    }
catch (SQLException ex) {
System.out.println("An error occurred. Maybe get user type is not working");
ex.printStackTrace();
}
        
        return type;
    }
}
