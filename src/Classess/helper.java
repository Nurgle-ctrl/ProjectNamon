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
import java.util.concurrent.CompletableFuture;
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
        long startTime = System.nanoTime(); // Start timing
        executor.submit(() -> {
            myPSTmt = null;
            myRs = null;
            try {
                model.setRowCount(0);
                System.out.println("Thread " + Thread.currentThread().getName() + " - Preparing query");

                // Prepare the query
                if ("All".equalsIgnoreCase(cat)) {
                    query = "SELECT * FROM tblentry " + lim + " " + delim;
                    myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    myRs = myPSTmt.executeQuery();
                } else if (!whe.isBlank()) {
                    switch (index) {
                        case 2, 3 -> {
                            query = "SELECT * FROM tblentry WHERE " + cat + " = ? " + lim + " " + delim;
                            myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            myPSTmt.setInt(1, Integer.parseInt(whe));
                        }
                        case 9, 11, 12, 13, 14, 15 -> {
                            query = "SELECT * FROM tblentry WHERE " + cat + " = ? " + lim + " " + delim;
                            myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            myPSTmt.setDouble(1, Double.parseDouble(whe));
                        }
                        case 4 -> {
                            if (whe.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                query = "SELECT * FROM tblentry WHERE " + cat + " = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                myPSTmt.setDate(1, java.sql.Date.valueOf(whe));
                            } else if (whe.matches("\\d{4}-\\d{2}")) {
                                query = "SELECT * FROM tblentry WHERE YEAR(" + cat + ") = ? AND MONTH(" + cat + ") = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                String[] parts = whe.split("-");
                                myPSTmt.setInt(1, Integer.parseInt(parts[0]));
                                myPSTmt.setInt(2, Integer.parseInt(parts[1]));
                            } else {
                                query = "SELECT * FROM tblentry WHERE YEAR(" + cat + ") = ? " + lim + " " + delim;
                                myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                myPSTmt.setInt(1, Integer.parseInt(whe));
                            }
                        }
                        default -> {
                            query = "SELECT * FROM tblentry WHERE " + cat + " = ? " + lim + " " + delim;
                            myPSTmt = myConn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            myPSTmt.setString(1, whe);
                        }
                    }
                    System.out.println(query);
                    myRs = myPSTmt.executeQuery();
                } else {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "Please provide a search condition for " + cat, "Error", JOptionPane.ERROR_MESSAGE));
                    return;
                }

                long queryEndTime = System.nanoTime();
                System.out.println("Query execution time: " + (queryEndTime - startTime) / 1_000_000.0 + " ms");

                if (myRs != null) {
                    // Set column names
                    ResultSetMetaData metaData = (ResultSetMetaData) myRs.getMetaData();
                    int colCount = metaData.getColumnCount();
                    Vector<String> columnNames = new Vector<>();
                    for (int i = 1; i <= colCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }
                    model.setColumnIdentifiers(columnNames);

                    // Get row count for chunking
                    myRs.last();
                    int rowCount = myRs.getRow();
                    myRs.beforeFirst();
                    System.out.println("Total rows to process: " + rowCount);

                    // Multi-threaded processing
                    int chunkSize = Math.max(100000, rowCount / Runtime.getRuntime().availableProcessors());
                    int threadCount = (int) Math.ceil((double) rowCount / chunkSize);
                    CompletableFuture<?>[] futures = new CompletableFuture<?>[threadCount];

                    for (int i = 0; i < threadCount; i++) {
                        final int startRow = i * chunkSize + 1;
                        final int endRow = Math.min((i + 1) * chunkSize, rowCount);
                        futures[i] = CompletableFuture.runAsync(() -> {
                            Vector<Vector<Object>> chunkRows = new Vector<>();
                            try {
                                synchronized (myRs) { // Thread-safe ResultSet access
                                    myRs.absolute(startRow);
                                    for (int row = startRow; row <= endRow && myRs.next(); row++) {
                                        Vector<Object> rowData = new Vector<>(colCount);
                                        for (int col = 1; col <= colCount; col++) {
                                            rowData.add(myRs.getObject(col));
                                        }
                                        chunkRows.add(rowData);
                                    }
                                }
                                synchronized (model) { // Thread-safe model updates
                                    for (Vector<Object> row : chunkRows) {
                                        model.addRow(row);
                                    }
                                }
                            } catch (SQLException e) {
                                System.err.println("Error in thread " + Thread.currentThread().getName() + ": " + e.getMessage());
                            }
                        }, executor);
                    }

                    CompletableFuture.allOf(futures).join();
                    long processEndTime = System.nanoTime();
                    System.out.println("Processing time (multi-threaded): " + (processEndTime - queryEndTime) / 1_000_000.0 + " ms");

                    SwingUtilities.invokeLater(() -> {
                        thetable.setModel(model);
                        if (model.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "No records found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                        }
                        long uiUpdateEndTime = System.nanoTime();
                        System.out.println("UI update time: " + (uiUpdateEndTime - processEndTime) / 1_000_000.0 + " ms");
                        System.out.println("Total execution time: " + (uiUpdateEndTime - startTime) / 1_000_000.0 + " ms");
                    });
                }
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
            } finally {
                try {
                    if (myRs != null) myRs.close();
                    if (myPSTmt != null) myPSTmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(helper.class.getName()).log(Level.SEVERE, "Error closing resources", e);
                }
            }
        });
    }

    public boolean checker(String cat, int index, String whe) {
        try {
            switch (index) {
                case 2, 3 -> Integer.valueOf(whe);
                case 4 -> {
                    if (whe.matches("\\d{4}-\\d{2}-\\d{2}")) return true;
                    if (whe.matches("\\d{4}-\\d{2}")) return true;
                    if (whe.matches("\\d{4}")) return true;
                    return false;
                }
                case 1, 5, 6, 7, 8, 10 -> {
                    if (whe.matches(".*\\d.*")) return false;
                }
                case 9, 11, 12, 13, 14, 15 -> Double.valueOf(whe);
                default -> { return false; }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public void addEntry(String[] data) {
        executor.submit(() -> {
            if (data == null || data.length != 13) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "Invalid data", "Error", JOptionPane.ERROR_MESSAGE));
                return;
            }
            try (PreparedStatement pstmt = myConn.prepareStatement(
                    "INSERT INTO tblentry (Posted, DatePosted, DocNumber, BusinessCode, LocationCode, ModuleCode, AccountCode, NormalBalance, Amount, Amount2, Credit, Debit, FinalAmount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setDate(2, java.sql.Date.valueOf(data[1]));
                pstmt.setString(3, data[2]);
                pstmt.setString(4, data[3]);
                pstmt.setString(5, data[4]);
                pstmt.setString(6, data[5]);
                pstmt.setString(7, data[6]);
                pstmt.setString(8, data[7]);
                pstmt.setDouble(9, Double.parseDouble(data[8]));
                pstmt.setDouble(10, Double.parseDouble(data[9]));
                pstmt.setDouble(11, Double.parseDouble(data[10]));
                pstmt.setDouble(12, Double.parseDouble(data[11]));
                pstmt.setDouble(13, Double.parseDouble(data[12]));
                pstmt.executeUpdate();
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "Entry added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE));
            } catch (SQLException | NumberFormatException e) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "Error adding entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
            }
        });
    }

    public void updateEntry(String[] data, String entryId, int row, JTable thetable) {
        if (data == null || data.length != 13) {
            SwingUtilities.invokeLater(() ->
                JOptionPane.showMessageDialog(null, "Invalid data: must provide 13 elements.", "Error", JOptionPane.ERROR_MESSAGE));
            return;
        }
        executor.submit(() -> {
            try (PreparedStatement pstmt = myConn.prepareStatement(
                    "UPDATE tblentry SET Posted = ?, DatePosted = ?, DocNumber = ?, BusinessCode = ?, LocationCode = ?, ModuleCode = ?, AccountCode = ?, NormalBalance = ?, Amount = ?, Amount2 = ?, Credit = ?, Debit = ?, FinalAmount = ? WHERE EntryID = ?")) {
                pstmt.setInt(1, Integer.parseInt(data[0]));
                pstmt.setDate(2, java.sql.Date.valueOf(data[1]));
                pstmt.setString(3, data[2]);
                pstmt.setString(4, data[3]);
                pstmt.setString(5, data[4]);
                pstmt.setString(6, data[5]);
                pstmt.setString(7, data[6]);
                pstmt.setString(8, data[7]);
                pstmt.setDouble(9, Double.parseDouble(data[8]));
                pstmt.setDouble(10, Double.parseDouble(data[9]));
                pstmt.setDouble(11, Double.parseDouble(data[10]));
                pstmt.setDouble(12, Double.parseDouble(data[11]));
                pstmt.setDouble(13, Double.parseDouble(data[12]));
                pstmt.setInt(14, Integer.parseInt(entryId));
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(null, "Entry updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        thetable.setValueAt(entryId, row, 0);
                        for (int i = 0; i < data.length; i++) {
                            thetable.setValueAt(data[i], row, i + 1);
                        }
                    });
                } else {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "No entry found with EntryID: " + entryId, "Error", JOptionPane.ERROR_MESSAGE));
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
            try (PreparedStatement pstmt = myConn.prepareStatement("DELETE FROM tblentry WHERE EntryID = ?")) {
                pstmt.setString(1, entryId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "Entry deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE));
                } else {
                    SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "No entry found with EntryID: " + entryId, "Error", JOptionPane.ERROR_MESSAGE));
                }
            } catch (SQLException e) {
                SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null, "Error deleting entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE));
            }
        });
    }

    public void closeResources() {
        try {
            if (myConn != null && !myConn.isClosed()) myConn.close();
        } catch (SQLException e) {
            Logger.getLogger(helper.class.getName()).log(Level.SEVERE, "Error closing connection", e);
        }
    }
}