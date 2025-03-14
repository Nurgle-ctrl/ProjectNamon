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
import java.util.Vector;
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
    private static Connection myConn = null;
    private static PreparedStatement myPSTmt = null;
    private static  ResultSet myRs = null;
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
                    
                Vector<String> columnNames = new Vector<>();
                Vector<Vector<Object>> rowData = new Vector<>();
                    
                // Retrieve column names from ResultSet
                int colCount = myRs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    columnNames.add(myRs.getMetaData().getColumnName(i));
                }

                // Add each row from the ResultSet to the rowData Vector
                while (myRs.next()) {
                    Vector<Object> row = new Vector<>();
                    for (int i = 1; i <= colCount; i++) {
                        row.add(myRs.getObject(i));
                    }
                    rowData.add(row);
                }
                
                System.out.println("Thread " + Thread.currentThread().getName() + " - Updating UI with rows" );
                if (rowData.isEmpty()) {
                    model.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "No records found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Set the model with column names and data
                model.setDataVector(rowData, columnNames);
                thetable.setModel(model);
                thetable.setDefaultEditor(Object.class, null);
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null,"SQL Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start(); 
           
    }
    public boolean checker(String cat, int index, String whe) {
        try {
            switch (index) {
                case 1, 2 -> // Index 1 and 2 should be an integer
                    Integer.valueOf(whe);
                case 3 -> {
                    // Index 3 should be a valid date
                    if (!whe.matches("\\d{4}-\\d{2}-\\d{2}")) { // Example format: YYYY-MM-DD
                        return false;
                    }
                }
                case 4, 5, 6, 7, 9 -> {
                    // These indexes should be a string (no numbers allowed)
                    if (whe.matches(".*\\d.*")) { // If it contains any digit, return false
                        return false;
                    }
                }
                case 8, 10, 11, 12, 13, 14 -> // These indexes should be a double (floating-point number)
                    Double.valueOf(whe);
                default -> {
                    return false; // If the index is outside the expected range, return false
                }
            }
        } catch (NumberFormatException e) {
            return false; // Return false if parsing fails
        }
        return true; // If all conditions pass, return true
    }




   
}
