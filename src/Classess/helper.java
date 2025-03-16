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
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
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
    private static String query = null;
    private static final DefaultTableModel model = new DefaultTableModel();
    private final ExecutorService executor;    
    
    public helper(ExecutorService executor) {
        this.executor = executor;
    }           
    
    void connector(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Database Connection Error." + e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);     
        }
    }
    
    public void displaythis(String cat, int index, String whe, String lim, String delim, JTable thetable) {
        executor.submit(() -> {
            try {
            myPSTmt = null;
            myRs = null;
            model.setRowCount(0);
            System.out.println(index);                
                
                System.out.println("Thread " + Thread.currentThread().getName() + " - Preparing query");
                if (!whe.isBlank()){
                    switch (index) {
                        case 1, 2 -> {
                            query = "select * from tblentry where "+ cat +" = ? "+ lim +" "+ delim;
                            myPSTmt = myConn.prepareStatement(query);
                            myPSTmt.setInt(1, Integer.parseInt(whe));
                        }
                        case 8, 10, 11, 12, 13, 14 -> {
                            query = "select * from tblentry where "+ cat +" = ? "+ lim +" "+ delim;
                            myPSTmt = myConn.prepareStatement(query);
                            myPSTmt.setDouble(1, Double.parseDouble(whe));                            
                        }
                        case 3 -> {
                            if (whe.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                query = "select * from tblentry where " + cat + " = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query);
                                java.sql.Date sqlDate = java.sql.Date.valueOf(whe);
                                myPSTmt.setDate(1, sqlDate);
                            } else if (whe.matches("\\d{4}-\\d{2}")) { // Year and month: YYYY-MM
                                query = "select * from tblentry where year(" + cat + ") = ? and month(" + cat + ") = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query);
                                String[] parts = whe.split("-");
                                myPSTmt.setInt(1, Integer.parseInt(parts[0])); // Year
                                myPSTmt.setInt(2, Integer.parseInt(parts[1])); // Month
                            } else { // Year only: YYYY
                                query = "select * from tblentry where year(" + cat + ") = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query);
                                myPSTmt.setInt(1, Integer.parseInt(whe)); // Year
                            }
                        }
                        default -> {
                            query = "select * from tblentry where "+ cat +" = ? "+ lim +" "+ delim;
                            myPSTmt = myConn.prepareStatement(query);                            
                            myPSTmt.setString(1, whe);
                        }
                    }
                    System.out.println(query);
                    myRs = myPSTmt.executeQuery();                
                }
                System.out.println("Thread " + Thread.currentThread().getName() + " - Fetching results");
                    
                ResultSetMetaData metaData = (ResultSetMetaData) myRs.getMetaData();
                // Total columns
                int colCount = metaData.getColumnCount();
                // Columns names storage
                Vector<String> columnNames = new Vector<>();
                //instruct columns names
                for (int i = 1; i <= colCount; i++) {
                    columnNames.add(metaData.getColumnName(i));
                }
                //Change the current column name with the instructed.
                model.setColumnIdentifiers(columnNames);
                while (myRs.next()) {
                    Object[] rowData = new Object[colCount];
                    for (int i = 0; i < colCount; i++) {
                        rowData[i] = myRs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }
                
                thetable.setModel(model);

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
        });
           
    }
    public boolean checker(String cat, int index, String whe) {       
        try {
            switch (index) {
                case 1, 2 -> Integer.valueOf(whe);
                case 3 -> {
                    if (whe.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    return true;
                    }
                    // Check for year and month (YYYY-MM)
                    if (whe.matches("\\d{4}-\\d{2}")) {
                        return true;
                    }
                    // Check for year only (YYYY)
                    if (whe.matches("\\d{4}")) {
                        return true;
                    }
                    return false;
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
    public void addEntry(String[] data){
        executor.submit(() -> {
            if (data == null || data.length != 13) {
                JOptionPane.showMessageDialog(null, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE);
                return;
           }        
           try {
               query = "INSERT INTO tblentry (Posted, DatePosted, DocNumber, BusinessCode, LocationCode, ModuleCode, AccountCode, NormalBalance, Amount, Amount2, Credit, Debit, FinalAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
               myPSTmt = myConn.prepareStatement(query);
               myPSTmt.setInt(1, Integer.parseInt(data[0]));
               myPSTmt.setDate(2, java.sql.Date.valueOf(data[1])); // Assumes YYYY-MM-DD format
               myPSTmt.setString(3, data[2]);
               myPSTmt.setString(4, data[3]);
               myPSTmt.setString(5, data[4]);
               myPSTmt.setString(6, data[5]);
               myPSTmt.setString(7, data[6]);
               myPSTmt.setString(8, data[7]);
               myPSTmt.setDouble(9, Double.parseDouble(data[8]));
               myPSTmt.setDouble(10, Double.parseDouble(data[9]));
               myPSTmt.setDouble(11, Double.parseDouble(data[10]));
               myPSTmt.setDouble(12, Double.parseDouble(data[11]));
               myPSTmt.setDouble(13, Double.parseDouble(data[12]));
               myPSTmt.executeUpdate();
               myPSTmt.close();
               JOptionPane.showMessageDialog(null, "Entry added successfully!");
           } catch (SQLException | NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Error adding entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }    
        });
    }
//    public String[] getter(JTable thetable, int editableRow){
//        String[] data = new String[14];
//        executor.submit(() -> {
//        data[0] = thetable.getValueAt(editableRow, 0).toString();
//        data[1] = thetable.getValueAt(editableRow, 1).toString();
//        data[2] = thetable.getValueAt(editableRow, 2).toString();
//        data[3] = thetable.getValueAt(editableRow, 3).toString();
//        data[4] = thetable.getValueAt(editableRow, 4).toString();
//        data[5] = thetable.getValueAt(editableRow, 5).toString();
//        data[6] = thetable.getValueAt(editableRow, 6).toString();
//        data[7] = thetable.getValueAt(editableRow, 7).toString();
//        data[8] = thetable.getValueAt(editableRow, 8).toString();
//        data[9] = thetable.getValueAt(editableRow, 9).toString();
//        data[10] = thetable.getValueAt(editableRow, 10).toString();
//        data[11] = thetable.getValueAt(editableRow, 11).toString();
//        data[12] = thetable.getValueAt(editableRow, 12).toString();
//        data[13] = thetable.getValueAt(editableRow, 13).toString();   
//        return data;
//        });
//        return data;
//    }
    public void updateEntry(String[] data, String entryId, int row, JTable thetable){
        if (data == null || data.length != 13) {
            JOptionPane.showMessageDialog(null, "Invalid data: must provide 13 elements.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        executor.submit(() -> {
            try {
                myPSTmt = myConn.prepareStatement("UPDATE tblentry SET Posted = ?, DatePosted = ?, DocNumber = ?, BusinessCode = ?, LocationCode = ?, ModuleCode = ?, AccountCode = ?, NormalBalance = ?, Amount = ?, Amount2 = ?, Credit = ?, Debit = ?, FinalAmount = ? WHERE EntryID = ?");
                myPSTmt.setInt(1, Integer.parseInt(data[0]));
                myPSTmt.setDate(2, java.sql.Date.valueOf(data[1]));
                myPSTmt.setString(3, data[2]);
                myPSTmt.setString(4, data[3]);
                myPSTmt.setString(5, data[4]);
                myPSTmt.setString(6, data[5]);
                myPSTmt.setString(7, data[6]);
                myPSTmt.setString(8, data[7]);
                myPSTmt.setDouble(9, Double.parseDouble(data[8]));
                myPSTmt.setDouble(10, Double.parseDouble(data[9]));
                myPSTmt.setDouble(11, Double.parseDouble(data[10]));
                myPSTmt.setDouble(12, Double.parseDouble(data[11]));
                myPSTmt.setDouble(13, Double.parseDouble(data[12]));
                myPSTmt.setInt(14, Integer.parseInt(entryId));
                int rowsAffected = myPSTmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Entry updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No entry found with EntryID: " + entryId, "Error", JOptionPane.ERROR_MESSAGE);
                }
                myPSTmt.close();
                thetable.setValueAt(entryId, row, 0);
                for (int i = 0; i < data.length; i++) {
                    thetable.setValueAt(data[i], row, i+1);
                }              
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error updating entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error updating entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Error updating entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });        
    }
    public void deleteEntry(String entryId) {
        executor.submit(() -> {
            try {
                myPSTmt = myConn.prepareStatement("DELETE FROM tblentry WHERE EntryID = ?");
                myPSTmt.setString(1, entryId);
                int rowsAffected = myPSTmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Entry deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No entry found with EntryID: " + entryId, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error deleting entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }     
  
   
}
