/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdallah M
 */
public class Waiter {
    
    private Connection con;
    Waiter()
    {
        Database database=new Database();
        con = database.connect_to_database();
    }
   
    public String[] get_restaurants()
    {
        String[] res = new String[20];
        int x=0, count=0;
        String query="select restaurant_location from restaurant";
    try(Statement stmt = con.createStatement()) 
    { 
      int i=0;
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next())
      {
        res[i] = rs.getString("restaurant_location");  
        i++;
      }
      while(res[x]!=null)
      {
          count++;
          x++;
      }
      String[] res1= new String[count];
      for(int k=0; k<res1.length; k++)
          res1[k]=res[k];
              return res1;
    }
    
    catch (SQLException ex) {
System.out.println("An error occurred. Maybe valid user is not working");
ex.printStackTrace();
}
    return res;
}

    public String[] get_tables(String res)
    {
        String[] table = new String[40];
        int x=0, count=0;
        String query="select tid from(restaurant_table inner join restaurant on restaurant_table.rid=restaurant.rid) where restaurant.restaurant_location= ? ";
    try(PreparedStatement pst = con.prepareStatement(query)) 
    { 
      int i=0;
      pst.setString(1, res.toString()); 
      ResultSet rs = pst.executeQuery();
      while (rs.next())

      {
        table[i] = String.valueOf(rs.getInt("tid"));  
        i++;
      }
      while(table[x]!= null)
      {
          count++;
          x++;
      }
      String[] table1= new String[count];
      for(int k=0; k<table1.length; k++)
          table1[k]=table[k];
              return table1;
    }
    
    catch (SQLException ex) {
System.out.println("An error occurred. Maybe get tables");
ex.printStackTrace();
}
    return table;
}

    public void update_state(int table_id, String state) {
     
        String query = "UPDATE restaurant_table SET state = ? WHERE tid = ? ;";
      try(PreparedStatement preparedStmt = con.prepareStatement(query))
      {
          preparedStmt.setString(1, state);
          preparedStmt.setInt   (2,table_id);
 
      preparedStmt.executeUpdate();
      System.out.println("SUSCCUS..!!!");
    }   
      catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    
    
}


