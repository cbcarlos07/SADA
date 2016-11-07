/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.connection;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Bruno
 */
public class ConnectionFactory {
    
    
    public static Connection getConnection(){
        Connection connection = null; 
        
        
        
         
            
        try {
            Class.forName("com.mysql.jdbc.Driver");
             String url = "jdbc:mysql://localhost:3306/sada";                    
             String user = "sada";
             String senha = "sada";
             System.out.println("url: "+url);
             connection =  DriverManager.getConnection(url,user,senha);
             System.out.println("Contado com sucesso");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
           
             
            
         
        
        return connection;
    }
}
