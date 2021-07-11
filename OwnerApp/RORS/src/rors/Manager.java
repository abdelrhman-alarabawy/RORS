/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rors;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abdallah M
 */
public class Manager {
    
    private Connection con;
    private Database database;
    
    Manager()
    {
      database=new Database();
    }
    
    
    public String[] get_Items()
    {
     Connection con =database.connect_to_database();
        String[] item = new String[200];
        int x=0, count=0;
        String query="SELECT item_name FROM menu;";
        
try(Statement stmt = con.createStatement()) 
    {
      int i=0;
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next())
      {
        item[i] = rs.getString("item_name").toString();  
        i++;
      }
      while(item[x]!=null)
      {
          count++;
          x++;
      }
      String[] item1= new String[count];
      for(int k=0; k<item1.length; k++)
          item1[k]=item[k];
      con.close();
      return item1;

}

catch (SQLException ex) {
System.out.println("An error occurred. Maybe valid user is not working");
ex.printStackTrace();
}
    return item;
    }
    

    public String[] get_item_info(String item)
    {
        
        Connection con =database.connect_to_database();
        String[] info = new String[3];
        
        String query="SELECT item_name,menu_name,unit_price FROM menu where item_name= ? ";
    try(PreparedStatement pst = con.prepareStatement(query)) 
    {
      pst.setString(1, item); 
      ResultSet rs = pst.executeQuery();
      while (rs.next())
      {
       info[0]=rs.getString("item_name");
       info[1]=rs.getString("menu_name");
       info[2]=String.valueOf(rs.getInt("unit_price"));
      }
      con.close();
     return info;
    }
    
    catch (SQLException ex) {
System.out.println("An error occurred. Maybe get tables");
ex.printStackTrace();
}
    return info;
    } 

    void update_price(int price, String item_name) {
        con= database.connect_to_database();
      
        int item_id=0;
        try
        {
            String prequery = "select iid from menu where item_name=? ";
            PreparedStatement preparedStmt = con.prepareStatement(prequery);
            preparedStmt.setString(1,item_name);
              // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
            item_id = rs.getInt("iid");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String query = "UPDATE menu SET unit_price = ? WHERE iid = ? ;";
      try(PreparedStatement preparedStmt = con.prepareStatement(query))
      {
          preparedStmt.setInt(1, price);
          preparedStmt.setInt   (2,item_id);
 
      preparedStmt.executeUpdate();
      System.out.println("SUSCCUS..!!!");
    }   
      catch (SQLException ex) {
            System.out.println(ex.toString());
        }
      
    }   

    void delete_record(String item_name) {
        
        Connection con =database.connect_to_database();
        int item_id = 0;
        try
        {
            String prequery = "select iid from menu where item_name=? ";
            PreparedStatement preparedStmt = con.prepareStatement(prequery);
            preparedStmt.setString(1,item_name);
              // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            while(rs.next()){
            item_id = rs.getInt("iid");
            }
             try{
             String query = "delete from menu where iid = ?";
      PreparedStatement pst = con.prepareStatement(query);
      pst.setInt(1,item_id);

      // execute the preparedstatement
      pst.execute();
      
      con.close();
        } catch (SQLException ex) {
            System.out.println("THERE WAS ERROR DELETING RECORD");
        }
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          
    }

   public void addrecord(String item_name, String menu_name, int menu_id, int price, String description) 
   {
        
        con=database.connect_to_database();
        int item_id=-1;
        String query="SELECT MAX(iid) FROM menu";
        try(Statement stmt = con.createStatement()) 
    { 
     
      ResultSet rs = stmt.executeQuery(query);
    while(rs.next()){
        item_id = rs.getInt("MAX(iid)");  
    }
      
      item_id++;
        
    }   catch (SQLException ex) {
            System.out.println(ex.toString());
        }
       
      try{       // the mysql insert statement
      String sql = " insert into menu (iid, mid, menu_name, item_name, unit_price, description)"
        + " values (?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = con.prepareStatement(sql);
      preparedStmt.setInt (1, item_id);
      preparedStmt.setInt (2, menu_id);
      preparedStmt.setString   (3, menu_name);
      preparedStmt.setString(4, item_name);
      preparedStmt.setInt    (5, price);
      preparedStmt.setString    (6, description);
      // execute the preparedstatement
      preparedStmt.execute();
      
      con.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
  }
    
}
    
    

