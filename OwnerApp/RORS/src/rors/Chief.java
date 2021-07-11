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
public class Chief {
    private Connection con;
    Chief()
    {
        Database database=new Database();
        con=database.connect_to_database();
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
    
}
