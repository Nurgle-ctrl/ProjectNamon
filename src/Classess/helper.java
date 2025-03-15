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
import javax.swing.SwingUtilities;
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
            myPSTmt = null;
            myRs = null;
            model.setRowCount(0);
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
    public void saveUpdate(int editableRow, JTable thetable) {
        executor.submit(() -> {           
            if (editableRow != -1) {
                Vector<Object> rowData = new Vector<>();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    rowData.add(model.getValueAt(editableRow, i));
                }
                try {
                    String updateQuery = "UPDATE tblentry SET EntryID = ?, Posted = ?, DatePosted = ?, DocNumber = ?, BusinessCode = ?, LocationCode = ?, ModuleCode = ?, AccountCode = ?, NormalBalance = ?, Amount = ?, Amount2 = ?, Credit = ?, Debit = ?, FinalAmount = ? WHERE EntryID = ?";
                    myPSTmt = myConn.prepareStatement(updateQuery);
                    for (int i = 0; i < rowData.size(); i++) {
                        if (rowData.get(i) instanceof Integer integer) {
                            myPSTmt.setInt(i + 1, integer);
                        } else if (rowData.get(i) instanceof Double aDouble) {
                            myPSTmt.setDouble(i + 1, aDouble);
                        } else if (rowData.get(i) instanceof java.sql.Date date) {
                            myPSTmt.setDate(i + 1, date);
                        } else {
                            myPSTmt.setString(i + 1, rowData.get(i) != null ? rowData.get(i).toString() : null);
                        }
                    }
                    myPSTmt.setInt(15, (Integer) rowData.get(0));
                    myPSTmt.executeUpdate();

                    System.out.println("Database updated for row " + editableRow);
                    JOptionPane.showMessageDialog(null, "Row " + (editableRow + 1) + " updated successfully.");
                    myPSTmt.close();
                } catch (SQLException e) {
                    System.err.println("Update failed: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No row selected for updating.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });    
    }
                
    public void addNewEntry(JTable thetable) {
        executor.submit(() -> {
            String[] fields = {"Posted", "DatePosted (YYYY-MM-DD)", "DocNumber", "BusinessCode", "LocationCode", "ModuleCode", "AccountCode", "NormalBalance", "Amount", "Amount2", "Credit", "Debit", "FinalAmount"};
            Vector<Object> newEntryData = new Vector<>();

            for (String field : fields) {
                String value = JOptionPane.showInputDialog(null, "Enter " + field + ":");
                if (value == null) { 
                    JOptionPane.showMessageDialog(null, "Entry addition cancelled.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    switch (field) {
                        case "DatePosted (YYYY-MM-DD)":
                            newEntryData.add(Date.parse(value));
                            break;
                        case "Amount":
                        case "Amount2":
                        case "Credit":
                        case "Debit":
                        case "FinalAmount":
                            newEntryData.add(Double.valueOf(value));
                            break;
                        default:
                            newEntryData.add(value);
                    }
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input for " + field + ": " + e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            try {
                String insertQuery = "INSERT INTO tblentry (Posted, DatePosted, DocNumber, BusinessCode, LocationCode, ModuleCode, AccountCode, NormalBalance, Amount, Amount2, Credit, Debit, FinalAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                myPSTmt = myConn.prepareStatement(insertQuery);
                for (int i = 0; i < newEntryData.size(); i++) {
                    if (newEntryData.get(i) instanceof Integer) {
                        myPSTmt.setInt(i + 1, (Integer) newEntryData.get(i));
                    } else if (newEntryData.get(i) instanceof Double) {
                        myPSTmt.setDouble(i + 1, (Double) newEntryData.get(i));
                    } else if (newEntryData.get(i) instanceof java.sql.Date) {
                        myPSTmt.setDate(i + 1, (java.sql.Date) newEntryData.get(i));
                    } else {
                        myPSTmt.setString(i + 1, newEntryData.get(i) != null ? newEntryData.get(i).toString() : null);
                    }
                }
                myPSTmt.executeUpdate();
                System.out.println("New entry added to database");
                JOptionPane.showMessageDialog(null, "New entry added successfully.");
                myPSTmt.close();
            } catch (SQLException e) {
                System.err.println("Insert failed: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Failed to add entry.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void deleteEntry(JTable thetable) {
        executor.submit(() -> {
        int[] selectedRows = thetable.getSelectedRows();
            if (selectedRows.length == 1) {
            int selectedRow = selectedRows[0];
            int entryId = (Integer) model.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete row " + (selectedRow + 1) + " with EntryID " + entryId + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                
                    try {
                        String deleteQuery = "DELETE FROM tblentry WHERE EntryID = ?";
                        PreparedStatement pstmt = myConn.prepareStatement(deleteQuery);
                        pstmt.setInt(1, entryId);
                        int rowsAffected = pstmt.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Row deleted successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete row.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e) {
                        System.err.println("Delete failed: " + e.getMessage());
                        JOptionPane.showMessageDialog(null, "Failed to delete row.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

            }
            } else if (selectedRows.length > 1) {
                JOptionPane.showMessageDialog(null, "Please select only one row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }   
                            });
    }    
    


   
}
