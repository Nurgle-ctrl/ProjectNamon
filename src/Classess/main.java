/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Classess;

import java.awt.event.ItemEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Acer
 */
public class main extends javax.swing.JFrame {
    private int mouseX;
    private int mouseY;
    private final helper helpus;
    private final entry entriable;
    private final ExecutorService executor;
    private String category;
    private String where;
    private String postlimit;
    private String thelimit;
    private int index;
    private int editableRow = -1;
    
    private void searchlimiter(){
        boolean isLimit = "Limit".equals(limit.getSelectedItem());
        limiter.setEnabled(isLimit);
        limiter.setVisible(isLimit);  
        limiter.setEditable(isLimit);
        jPanel1.revalidate(); 
        jPanel1.repaint();
    }    
    /**
     * Creates new form main
     */
    public main() {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        helpus = new helper(executor); 
        entriable = new entry(helpus);
        initComponents();
        helpus.connector();
        searchlimiter();
        jTextField1.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        DISPLAY = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        limit = new javax.swing.JComboBox<>();
        limiter = new javax.swing.JTextField();
        columnnames = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        thetable = new javax.swing.JTable();
        editor = new javax.swing.JButton();
        adder = new javax.swing.JButton();
        Deleter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(926, 574));

        DISPLAY.setText("Display");
        DISPLAY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DISPLAYActionPerformed(evt);
            }
        });

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jTextField1.setText("Condition");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        limit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Limit" }));
        limit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                limitItemStateChanged(evt);
            }
        });
        limit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limitActionPerformed(evt);
            }
        });

        limiter.setEditable(false);
        limiter.setText("ninja");
        limiter.setEnabled(false);
        limiter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                limiterMouseClicked(evt);
            }
        });
        limiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limiterActionPerformed(evt);
            }
        });

        columnnames.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "EntryID", "Posted", "DatePosted", "DocNumber", "BusinessCode", "LocationCode", "ModuleCode", "AccountCode", "NormalBalance", "Amount", "Amount2", "Credit", "Debit", "FinalAmount" }));
        columnnames.setToolTipText("");
        columnnames.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                columnnamesItemStateChanged(evt);
            }
        });

        thetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ENTRY ID", "POSTED", "DATE POSTED", "DOC NUMBER", "BUSINESS CODE", "LOCATION CODE", "MODULE CODE", "ACCOUNT CODE", "NORMAL BALANCE", "AMOUNT", "AMOUNT 2", "CREDIT", "DEBIT", "FINAL AMOUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(thetable);
        if (thetable.getColumnModel().getColumnCount() > 0) {
            thetable.getColumnModel().getColumn(0).setResizable(false);
            thetable.getColumnModel().getColumn(1).setResizable(false);
            thetable.getColumnModel().getColumn(2).setResizable(false);
            thetable.getColumnModel().getColumn(3).setResizable(false);
            thetable.getColumnModel().getColumn(4).setResizable(false);
            thetable.getColumnModel().getColumn(5).setResizable(false);
            thetable.getColumnModel().getColumn(6).setResizable(false);
            thetable.getColumnModel().getColumn(7).setResizable(false);
            thetable.getColumnModel().getColumn(8).setResizable(false);
            thetable.getColumnModel().getColumn(9).setResizable(false);
            thetable.getColumnModel().getColumn(10).setResizable(false);
            thetable.getColumnModel().getColumn(11).setResizable(false);
            thetable.getColumnModel().getColumn(12).setResizable(false);
            thetable.getColumnModel().getColumn(13).setResizable(false);
        }

        editor.setText("Edit");
        editor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editorActionPerformed(evt);
            }
        });

        adder.setText("Add");
        adder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adderActionPerformed(evt);
            }
        });

        Deleter.setText("Delete");
        Deleter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(columnnames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DISPLAY)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limiter, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addComponent(editor, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adder, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Deleter))
                    .addComponent(jScrollPane1))
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DISPLAY)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limiter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnnames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editor)
                    .addComponent(adder)
                    .addComponent(Deleter))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DISPLAYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DISPLAYActionPerformed
        // TODO add your handling code here:
        category = columnnames.getSelectedItem().toString();
        index = columnnames.getSelectedIndex() + 1;
        where = jTextField1.getText().trim();
        postlimit = limit.getSelectedItem().toString().toUpperCase().equalsIgnoreCase("all") ? "" : limit.getSelectedItem().toString().toUpperCase();
        System.out.println(postlimit);
        thelimit = limiter.isEnabled() ? limiter.getText().trim() : "";
        if (where.isEmpty() && category.equals("All")) {
            JOptionPane.showMessageDialog(null, "Please enter a search condition","Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!limit.getSelectedItem().equals("All") && limiter.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Limiter is not limiting", "Limiter Error", JOptionPane.ERROR_MESSAGE);
            return;            
        }
        boolean check = helpus.checker(category, index, where);
        if (!check){
            JOptionPane.showMessageDialog(null, "Invalid input!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;             
        }           
        helpus.displaythis(category, index, where, postlimit, thelimit, thetable);    
    }//GEN-LAST:event_DISPLAYActionPerformed

    private void limitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limitActionPerformed
        // TODO add your handling code here:
        searchlimiter();
    }//GEN-LAST:event_limitActionPerformed
   
    private void limiterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limiterMouseClicked
        // TODO add your handling code here:
        limiter.setText("");
        limiter.setEnabled(true);
    }//GEN-LAST:event_limiterMouseClicked

    private void limitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_limitItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED){
            
            boolean isLimit = "Limit".equals(limit.getSelectedItem());
            limiter.setText("");
            limiter.setEnabled(isLimit);
            limiter.setVisible(isLimit);
            limiter.setEditable(isLimit);
            jPanel1.revalidate(); 
            jPanel1.repaint();
            System.out.println("Item selected: " + limit.getSelectedItem());
        }
                
    }//GEN-LAST:event_limitItemStateChanged

    
    
    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        // TODO add your handling code here:
        jTextField1.setText("");      
    }//GEN-LAST:event_jTextField1MouseClicked

    private void editorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editorActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = thetable.getSelectedRows();
        if (selectedRows.length == 1) {
            editableRow = selectedRows[0];
//            String[] data = helpus.getter(thetable, editableRow);
            entriable.entryID.setVisible(true);
            entriable.setEntryId(thetable.getValueAt(editableRow, 0).toString());
            entriable.setPosted(thetable.getValueAt(editableRow, 1).toString());
            entriable.setDatePosted(thetable.getValueAt(editableRow, 2).toString());
            entriable.setDocNumber(thetable.getValueAt(editableRow, 3).toString());
            entriable.setBusinessCode(thetable.getValueAt(editableRow, 4).toString());
            entriable.setLocationCode(thetable.getValueAt(editableRow, 5).toString());
            entriable.setModuleCode(thetable.getValueAt(editableRow, 6).toString());
            entriable.setAccountCode(thetable.getValueAt(editableRow, 7).toString());
            entriable.setNormalBalance(thetable.getValueAt(editableRow, 8).toString());
            entriable.setAmount(thetable.getValueAt(editableRow, 9).toString());
            entriable.setAmount2(thetable.getValueAt(editableRow, 10).toString());
            entriable.setCredit(thetable.getValueAt(editableRow, 11).toString());
            entriable.setDebit(thetable.getValueAt(editableRow, 12).toString());
            entriable.setFinalAmount(thetable.getValueAt(editableRow, 13).toString());
            entriable.setVisible(true);
            entriable.editedRow(selectedRows[0], thetable);
            
        } else if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this, "Please select only one row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editorActionPerformed

    private void adderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adderActionPerformed
        // TODO add your handling code here:
        entriable.setField();        
        entriable.setVisible(true);        
    }//GEN-LAST:event_adderActionPerformed

    private void DeleterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleterActionPerformed
        // TODO add your handling code here:
        int[] selectedRows = thetable.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this, "Please select at least one row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (selectedRows.length > 1) {
            JOptionPane.showMessageDialog(this, "Please select only one row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete row" + selectedRows[0] + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) thetable.getModel();
            for (int row : selectedRows) {
                String entryId = thetable.getValueAt(row, 0).toString();
                helpus.deleteEntry(entryId);
                entriable.editedRow(selectedRows[0], thetable);
                model.removeRow(row);
            }   
        }
    }//GEN-LAST:event_DeleterActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    mouseX = evt.getX();
    mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    int x = evt.getXOnScreen();
    int y = evt.getYOnScreen();
    this.setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_formMouseDragged

    private void limiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limiterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_limiterActionPerformed

    private void columnnamesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_columnnamesItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED){
            
            boolean isLimit = "All".equals(columnnames.getSelectedItem());
            jTextField1.setText("condition");
            jTextField1.setEnabled(!isLimit);
            jTextField1.setVisible(!isLimit);
            jTextField1.setEditable(!isLimit);
            jPanel1.revalidate(); 
            jPanel1.repaint();
        }        
    }//GEN-LAST:event_columnnamesItemStateChanged

    @Override
    public void dispose() {
        super.dispose();
        helpus.closeResources();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DISPLAY;
    private javax.swing.JButton Deleter;
    private javax.swing.JButton adder;
    private javax.swing.JComboBox<String> columnnames;
    private javax.swing.JButton editor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox<String> limit;
    private javax.swing.JTextField limiter;
    private javax.swing.JTable thetable;
    // End of variables declaration//GEN-END:variables
}
