
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abdelrhman
 */
public class DB_connection {
    public static Connection db_connection(){
        Connection  con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/RORS", "root", "Abdelrhman.Yasser1010");
        } catch (Exception ex) {
            Logger.getLogger(DB_connection.class.getName()).log(Level.SEVERE, null, ex);
        }
       return con;
    
    }
    
}
