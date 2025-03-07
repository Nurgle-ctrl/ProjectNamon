/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classess;

import com.mysql.jdbc.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
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
    
    public void displaythis(String cat, int index, String whe, String lim, String delim, JTable thetable) {
        new Thread(() -> {
        try {
            if (!whe.isBlank()){
                String query = "select * from tblentry where "+ cat +" = ? "+ lim +" "+ delim;                
                myPSTmt = myConn.prepareStatement(query);
                switch (index) {
                    case 1 -> myPSTmt.setInt(1, Integer.parseInt(whe));
                    case 2 -> {
                        java.sql.Date sqlDate = java.sql.Date.valueOf(whe);
                        myPSTmt.setDate(1, sqlDate);
                    }
                    default -> myPSTmt.setString(1, whe);
                }
                myRs = myPSTmt.executeQuery();                
            }

                model = new DefaultTableModel();
                ResultSetMetaData rsmd = (ResultSetMetaData) myRs.getMetaData();
                int colCount = rsmd.getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    model.addColumn(rsmd.getColumnName(i));
                }
                
                // Add each row from the ResultSet to the model.
                while (myRs.next()) {
                    Object[] rowData = new Object[colCount];
                    for (int i = 1; i <= colCount; i++) {
                        rowData[i - 1] = myRs.getObject(i);
                    }
                    model.addRow(rowData);
                }
                
                // Update the JTable on the Event Dispatch Thread.
                SwingUtilities.invokeLater(() -> {
                    thetable.setModel(model);
                });
 
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"SQL Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        }).start(); 
           
    }
   
}
