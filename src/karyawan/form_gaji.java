/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

/**
 *
 * @author Lenovo
 */
import admin.Admin;
import admin.AdminForm;
import static admin.AdminForm.tfAlamat;
import static admin.AdminForm.tfId;
import static admin.AdminForm.tfNama;
import static admin.AdminForm.tfnoHP;
import admin.AdminForm1;
import koneksi.db_koneksi;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import keterangan.JFperusahaan;



public final class form_gaji extends javax.swing.JFrame {
    //membuat kelas
    public DefaultTableModel model;
    String nip, nama, jabatan;
    int gapok, transport, gaber, hasil, tunjang, lmbur;
    /**
     * Creates new form form_gaji
     */
    
    public void updateData(){
    loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "Update tblgaji set Nama ='"+nama+"',"
                    + " Jabatan = '"+jabatan+"',"
                    + " Gapok = '"+gapok+"',"
                    + " Transport = '"+transport+"',"
                    + " Lembur = '"+hasil+"',"
                    + " Tunjangan = '"+tunjang+"',"
                    + " Gaber = '"+(gaber+hasil)+"' where Nip = '"+nip+"'";
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            JOptionPane.showMessageDialog(null, "Update berhasil...");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());        }
    }
    
    public void dataSelect(){
        int i = tblgaji.getSelectedRow();
        if ( i == -1){
            //tidak ada baris terpilih
            return;
        }
        txtNip.setText(""+model.getValueAt(i, 0));
        txtNama.setText(""+model.getValueAt(i, 1));
        cmboxJabatan.setSelectedItem(""+model.getValueAt(i, 2));
        txtGapok.setText(""+model.getValueAt(i, 3));
        txtTransport.setText(""+model.getValueAt(i, 4));
        hLembur.setText(""+model.getValueAt(i, 5));
        tunjangan.setText(""+model.getValueAt(i, 6));
        txtGaber.setText(""+model.getValueAt(i, 7));
        
    }
    
    public void deleteOption(){
        int option = Integer.parseInt(opdel.getText());
        
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data " , "konfirmasi", 
                    JOptionPane.OK_CANCEL_OPTION);
        if (pesan == JOptionPane.OK_OPTION){
            try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "delete from tblgaji where Nip = '" + option + " '" ;
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            reset ();
            JOptionPane.showMessageDialog(null, "Delete Berhasil");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());        }
        }    
    }
    
    public void deleteData(){
        
        int pesan = JOptionPane.showConfirmDialog(null, "Anda yakin menghapus data " , "konfirmasi", 
                    JOptionPane.OK_CANCEL_OPTION);
        if (pesan == JOptionPane.OK_OPTION){
            try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "truncate table tblgaji";
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
            reset ();
            JOptionPane.showMessageDialog(null, "Delete Berhasil");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());        }
        }    
    }
    
    public void loadData(){
        nip = txtNip.getText();
        nama = txtNama.getText();
        jabatan = (String) cmboxJabatan.getSelectedItem();
        gapok = Integer.parseInt(txtGapok.getText());
        transport = Integer.parseInt(txtTransport.getText());
        gaber = Integer.parseInt(txtGaber.getText());
        tunjang = Integer.parseInt(tunjangan.getText());
        hasil = Integer.parseInt(hLembur.getText());
    }
    
    
    public void loadGaji() {
        jabatan = "" +cmboxJabatan.getSelectedItem();
        switch (jabatan){
            case "Manager":
                    gapok = 5000000;
                    break;
            case "Asisten Manager":
                    gapok = 4000000;
                    break;
            case "Kepala HRD":
                    gapok = 3000000;
                    break;
            case "Staff Keuangan":
                    gapok = 2000000;
                    break;
            case "Karyawan":
                    gapok = 3500000;
                    break;
            case "Office Boy":
                    gapok = 2000000;
                    break;
        }
        
        
        transport = (int) (gapok * 0.01);
        tunjang = (int) (gapok * 0.3);
        gaber = gapok + transport + tunjang;
        txtGapok.setText(""+gapok);
        txtTransport.setText(""+transport);
        txtGaber.setText(""+gaber);
        tunjangan.setText(""+tunjang);
    }
    
    public void saveData(){
        loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "insert into tblgaji(Nip, Nama, Jabatan, Gapok, Transport, Lembur, Tunjangan, Gaber)"
                    + " values ( '"+nip+"', '"+nama+"', '"+jabatan+"', '"+gapok+"', '"+transport+"', '"+hasil+"','"+tunjang+"','"+(gaber+hasil)+"')";
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());        }
    }
    
    public form_gaji() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        model = new DefaultTableModel ();
        tblgaji.setModel(model);
        
        model.addColumn("Nip");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Gaji Pokok");
        model.addColumn("Transport");
        model.addColumn("Lembur");
        model.addColumn("Tunjangan");
        model.addColumn("Gaji Bersih + Lembur");
        
        getData();
    }
    
    public void getData(){
        //menghapus isi tabel gaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try {
            //membuat statement pemanggilan datagaji dari database
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "Select * from tblgaji";
            ResultSet res = stat.executeQuery(sql);
            
            //penelusuran baris pada tblgaji dari database
            while (res.next()){
                Object [] obj = new Object[8];
                obj[0] = res.getString("Nip");
                obj[1] = res.getString("Nama");
                obj[2] = res.getString("Jabatan");
                obj[3] = res.getString("Gapok");
                obj[4] = res.getString("Transport");
                obj[5] = res.getString("Lembur");
                obj[6] = res.getString("Tunjangan");
                obj[7] = res.getString("Gaber");
                
                model.addRow(obj);
            }
        }catch(SQLException err){
                    JOptionPane.showMessageDialog(null, err.getMessage());
                    }
    }
    
    public void reset(){
        nip = "";
        nama = "";
        jabatan = "";
        gapok = 0;
        transport = 0;
        gaber = 0;
        tunjang = 0;
        hasil = 0;
        lmbur = 0;
        txtNip.setText(nip);
        txtNama.setText(nama);
        txtGapok.setText("");
        txtTransport.setText("");
        txtGaber.setText("");
        tunjangan.setText("");
        hLembur.setText("");
        lembur.setText("");
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblgaji = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNip = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtGapok = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTransport = new javax.swing.JTextField();
        txtGaber = new javax.swing.JTextField();
        cmboxJabatan = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lembur = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        hLembur = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tunjangan = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        delete1 = new javax.swing.JButton();
        opdel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        daftarKar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblgaji.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblgaji.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgajiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblgaji);

        jLabel1.setText("NIP");

        jLabel2.setText("NAMA");

        jLabel3.setText("JABATAN");

        jLabel4.setText("Gaji Pokok");

        jLabel5.setText("Transport");

        jLabel6.setText("Gaji Bersih");

        cmboxJabatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Asisten Manager", "Kepala HRD", "Staff Keuangan", "Karyawan", "Office Boy" }));
        cmboxJabatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxJabatanActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton1.setText("SAVE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        jButton2.setText("RESET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete1.png"))); // NOI18N
        delete.setText("DELETE ALL");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jLabel7.setText("Lembur");

        jButton5.setText("HITUNG");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("Tunjangan");

        jLabel9.setText("JAM");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel10.setText("SISTEM PEMBAYARAN GAJI PT SOLO KARYA UTAMA");

        delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete1.png"))); // NOI18N
        delete1.setText("DELETE");
        delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete1ActionPerformed(evt);
            }
        });

        jLabel11.setText("  NIP =");

        daftarKar.setText("DAFTAR KARYAWAN");
        daftarKar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftarKarActionPerformed(evt);
            }
        });

        jButton4.setText("DAFTAR GAJI POKOK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3))
                                        .addGap(39, 39, 39)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtNip)
                                                .addComponent(txtNama)
                                                .addComponent(cmboxJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtGaber, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addComponent(delete1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(opdel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel6)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addComponent(jLabel5)
                                            .addGap(21, 21, 21))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8))
                                            .addGap(18, 18, 18)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lembur, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel9)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(hLembur, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(43, 43, 43)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton3)
                                            .addGap(18, 18, 18)
                                            .addComponent(delete))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtGapok, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(daftarKar, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(148, 148, 148)
                            .addComponent(jLabel10))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtGapok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(daftarKar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTransport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmboxJabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tunjangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtGaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jButton5)
                            .addComponent(hLembur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(delete)
                    .addComponent(delete1)
                    .addComponent(opdel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmboxJabatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxJabatanActionPerformed
        // TODO add your handling code here:
        loadGaji();
    }//GEN-LAST:event_cmboxJabatanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        saveData();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int lb = Integer.parseInt(lembur.getText());
        int samadengan = lb*10000;
        hLembur.setText(""+samadengan);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_deleteActionPerformed

    private void delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete1ActionPerformed
        // TODO add your handling code here:
        deleteOption();
    }//GEN-LAST:event_delete1ActionPerformed

    private void tblgajiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgajiMouseClicked
        // TODO add your handling code here:
        dataSelect();
    }//GEN-LAST:event_tblgajiMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void daftarKarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftarKarActionPerformed
        // TODO add your handling code here:
        new AdminForm1().setVisible(true);
    }//GEN-LAST:event_daftarKarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new JFperusahaan().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new form_gaji().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmboxJabatan;
    private javax.swing.JButton daftarKar;
    private javax.swing.JButton delete;
    private javax.swing.JButton delete1;
    private javax.swing.JTextField hLembur;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lembur;
    private javax.swing.JTextField opdel;
    private javax.swing.JTable tblgaji;
    private javax.swing.JTextField tunjangan;
    private javax.swing.JTextField txtGaber;
    private javax.swing.JTextField txtGapok;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNip;
    private javax.swing.JTextField txtTransport;
    // End of variables declaration//GEN-END:variables
}
