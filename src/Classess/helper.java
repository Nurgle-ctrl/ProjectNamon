/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class helper {
    private static Connection myConn = null;
    private static PreparedStatement myPSTmt = null;
    private static  ResultSet myRs = null;
    private static DefaultTableModel model = new DefaultTableModel();
    
    void connector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/amondb","root", "");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Connection Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);     
        }
    }
    
    
}
