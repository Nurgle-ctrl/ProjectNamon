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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class helper {
    private static final String URL = "jdbc:mysql://localhost:3306/amondb";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection myConn;
    private static PreparedStatement myPSTmt;
    private static  ResultSet myRs;
    private static final DefaultTableModel model = new DefaultTableModel();
    
    void connector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Connection Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);     
        }
    }
    
    public void displaythis(String cat, int index, String whe, String lim, String delim, JTable thetable) {
        new Thread(() -> {
            myPSTmt = null;
            myRs = null;
            try {
                
                System.out.println("Thread " + Thread.currentThread().getName() + " - Preparing query");
                if (!whe.isBlank()){
                    String query = "select * from tblentry where "+ cat +" = ? "+ lim +" "+ delim;  
                    System.out.println(query);
                    myPSTmt = myConn.prepareStatement(query);
                    switch (index) {
                        case 1 -> myPSTmt.setInt(1, Integer.parseInt(whe));
                        case 2 -> myPSTmt.setDouble(1, Double.parseDouble(whe));
                        case 3 -> {
                            java.sql.Date sqlDate = java.sql.Date.valueOf(whe);
                            myPSTmt.setDate(1, sqlDate);
                        }
                        default -> myPSTmt.setString(1, whe);
                    }
                    myRs = myPSTmt.executeQuery();                
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " - Fetching results");
                    
                ResultSetMetaData metaData = (ResultSetMetaData) myRs.getMetaData();
                int colCount = metaData.getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    model.addColumn(metaData.getColumnName(i));
                 }
                
                while (myRs.next()) {
                    Object[] rowData = new Object[colCount];
                    for (int i = 0; i < colCount; i++) {
                        rowData[i] = myRs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }
                
                thetable.setModel(model);
                thetable.setDefaultEditor(Object.class, null);

                if (model.getRowCount() == 0) {
                   JOptionPane.showMessageDialog(null, "No records found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"SQL Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    myPSTmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start(); 
           
    }
    public boolean checker(String cat, int index, String whe) {
        try {
            switch (index) {
                case 1, 2 -> Integer.valueOf(whe);
                case 3 -> {
                    if (!whe.matches("\\d{4}-\\d{2}-\\d{2}")) { // format: YYYY-MM-DD
                        return false;
                    }
                }
                case 4, 5, 6, 7, 9 -> {
                    if (whe.matches(".*\\d.*")) {
                        return false;
                    }
                }
                case 8, 10, 11, 12, 13, 14 -> Double.valueOf(whe);
                default -> { return false; 
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }




   
}
