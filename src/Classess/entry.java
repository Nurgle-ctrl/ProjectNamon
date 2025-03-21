/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Classess;

import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Acer
 */
public class entry extends javax.swing.JFrame {
    private int mouseX;
    private int mouseY;
    private final helper helpus;
    private String entryId = null; // Null for new entry, set for edit
    private boolean isEditMode = false;
    private int row = -1;
    private JTable thetable;
    private final JTextField[] fields;
    /**
     * Creates new form entry
     * @param helpus
     */
    public entry(helper helpus) {
        initComponents();
        this.fields = new JTextField[]{posted, datePosted, docNumber, businessCode, locationCode, moduleCode, accountCode, normalBalance, amount, amount2, credit, debit, finalAmount};
        this.helpus = helpus;
    }
    
    private void saveEntry() {
        String[] data = new String[13];
        for (int i = 0; i < fields.length; i++) {
            data[i] = fields[i].getText().trim();
        }
        if (isEditMode) {
            helpus.updateEntry(data, entryId, row, thetable);
        } else {
            helpus.addEntry(data);
        }
        dispose();
    }
    public void editedRow(int row, JTable table){
        this.row = row;
        this.thetable = table;
    }
    public void setField() {
        if (entryID != null) {
            entryID.setText("");
            entryID.setVisible(false);
        } else {
            System.err.println("entryID is null in setField!");
        }
        for (JTextField field : fields) {
            if (field != null) {
                field.setText("");
            } else {
                System.err.println("A field in fields array is null!");
            }
        }
    }
    // Getters and Setters
    public void setEntryId(String entryId) {
        this.entryId = entryId;
        this.entryID.setText(entryId);
        this.isEditMode = (entryId != null && !entryId.isEmpty());
    }
    public String getEntryId() {
        return entryId;
    }
    public void setPosted(String posted) {
        this.posted.setText(posted);
    }
    public void setDatePosted(String datePosted) {
        this.datePosted.setText(datePosted);
    }
    public void setDocNumber(String docNumber) {
        this.docNumber.setText(docNumber);
    }
    public void setBusinessCode(String businessCode) {
        this.businessCode.setText(businessCode);
    }
    public void setLocationCode(String locationCode) {
        this.locationCode.setText(locationCode);
    }
    public void setModuleCode(String moduleCode) {
        this.moduleCode.setText(moduleCode);
    }
    public void setAccountCode(String accountCode) {
        this.accountCode.setText(accountCode);
    }
    public void setNormalBalance(String normalBalance) {
        this.normalBalance.setText(normalBalance);
    }
    public void setAmount(String amount) {
        this.amount.setText(amount);
    }
    public void setAmount2(String amount2) {
        this.amount2.setText(amount2);
    }
    public void setCredit(String credit) {
        this.credit.setText(credit);
    }
    public void setDebit(String debit) {
        this.debit.setText(debit);
    }
    public void setFinalAmount(String finalAmount) {
        this.finalAmount.setText(finalAmount);
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
        title = new javax.swing.JLabel();
        entryID = new javax.swing.JTextField();
        posted = new javax.swing.JTextField();
        datePosted = new javax.swing.JTextField();
        docNumber = new javax.swing.JTextField();
        businessCode = new javax.swing.JTextField();
        locationCode = new javax.swing.JTextField();
        moduleCode = new javax.swing.JTextField();
        accountCode = new javax.swing.JTextField();
        normalBalance = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        credit = new javax.swing.JTextField();
        amount2 = new javax.swing.JTextField();
        debit = new javax.swing.JTextField();
        finalAmount = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ENTRY HERE");
        title.setToolTipText("");

        entryID.setEditable(false);
        entryID.setText("entryID");
        entryID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entryIDActionPerformed(evt);
            }
        });

        posted.setText("Posted");
        posted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                postedMouseClicked(evt);
            }
        });

        datePosted.setText("DatePosted");
        datePosted.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datePostedMouseClicked(evt);
            }
        });

        docNumber.setText("DocNumber");
        docNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                docNumberMouseClicked(evt);
            }
        });

        businessCode.setText("BusinessCode");
        businessCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                businessCodeMouseClicked(evt);
            }
        });

        locationCode.setText("LocationCode");
        locationCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationCodeMouseClicked(evt);
            }
        });

        moduleCode.setText("ModuleCode");
        moduleCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moduleCodeMouseClicked(evt);
            }
        });

        accountCode.setText("AccountCode");
        accountCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountCodeMouseClicked(evt);
            }
        });

        normalBalance.setText("NormalBalance");
        normalBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalBalanceMouseClicked(evt);
            }
        });

        amount.setText("Amount");
        amount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amountMouseClicked(evt);
            }
        });

        credit.setText("Credit");
        credit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditMouseClicked(evt);
            }
        });

        amount2.setText("Amount2");
        amount2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                amount2MouseClicked(evt);
            }
        });

        debit.setText("Debit");
        debit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                debitMouseClicked(evt);
            }
        });

        finalAmount.setText("FinalAmount");
        finalAmount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finalAmountMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("x");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jButton1.setText("ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ENTRY ID");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("POSTED");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DATE POSTED");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DOC NUMBER");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BUSINESS CODE");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("LOCATION CODE");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("MODULE CODE");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ACCOUNT CODE");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NORMAL BALANCE");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("AMOUNT");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("AMOUNT2");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CREDIT");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("DEBIT");

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("FINAL AMOUNT");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(96, 96, 96))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(credit, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(amount2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(amount, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(normalBalance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addComponent(accountCode, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(moduleCode, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(locationCode, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(businessCode, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(docNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(datePosted, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(posted, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(entryID, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(debit, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(title))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(entryID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(posted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(datePosted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(docNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(businessCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locationCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moduleCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(normalBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(amount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(credit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(debit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(finalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(146, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveEntry();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void postedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_postedMouseClicked
        // TODO add your handling code here:
        posted.setText("");      
    }//GEN-LAST:event_postedMouseClicked

    private void datePostedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePostedMouseClicked
        // TODO add your handling code here:
        datePosted.setText("");       
    }//GEN-LAST:event_datePostedMouseClicked

    private void docNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_docNumberMouseClicked
        // TODO add your handling code here:
        docNumber.setText("");          
    }//GEN-LAST:event_docNumberMouseClicked

    private void businessCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_businessCodeMouseClicked
        // TODO add your handling code here:
        businessCode.setText("");        
    }//GEN-LAST:event_businessCodeMouseClicked

    private void locationCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationCodeMouseClicked
        // TODO add your handling code here:
        locationCode.setText("");        
    }//GEN-LAST:event_locationCodeMouseClicked

    private void moduleCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moduleCodeMouseClicked
        // TODO add your handling code here:
        moduleCode.setText("");
    }//GEN-LAST:event_moduleCodeMouseClicked

    private void accountCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountCodeMouseClicked
        // TODO add your handling code here:
        accountCode.setText("");
    }//GEN-LAST:event_accountCodeMouseClicked

    private void normalBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalBalanceMouseClicked
        // TODO add your handling code here:
        normalBalance.setText("");
    }//GEN-LAST:event_normalBalanceMouseClicked

    private void amountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amountMouseClicked
        // TODO add your handling code here:
        amount.setText("");
    }//GEN-LAST:event_amountMouseClicked

    private void amount2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_amount2MouseClicked
        // TODO add your handling code here:
        amount2.setText("");
    }//GEN-LAST:event_amount2MouseClicked

    private void creditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditMouseClicked
        // TODO add your handling code here:
        credit.setText("");
    }//GEN-LAST:event_creditMouseClicked

    private void debitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_debitMouseClicked
        // TODO add your handling code here:
        debit.setText("");
    }//GEN-LAST:event_debitMouseClicked

    private void finalAmountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finalAmountMouseClicked
        // TODO add your handling code here:
        finalAmount.setText(""); 
    }//GEN-LAST:event_finalAmountMouseClicked

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
    int x = evt.getXOnScreen();
    int y = evt.getYOnScreen();
    
    // Set the new location of the frame by subtracting the initial coordinates
    this.setLocation(x - mouseX, y - mouseY);
    }//GEN-LAST:event_formMouseDragged

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
    mouseX = evt.getX();
    mouseY = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void entryIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entryIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entryIDActionPerformed

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
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(entry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountCode;
    private javax.swing.JTextField amount;
    private javax.swing.JTextField amount2;
    private javax.swing.JTextField businessCode;
    private javax.swing.JTextField credit;
    private javax.swing.JTextField datePosted;
    private javax.swing.JTextField debit;
    private javax.swing.JTextField docNumber;
    javax.swing.JTextField entryID;
    private javax.swing.JTextField finalAmount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField locationCode;
    private javax.swing.JTextField moduleCode;
    private javax.swing.JTextField normalBalance;
    private javax.swing.JTextField posted;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
