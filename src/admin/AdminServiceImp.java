package admin;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//implementasi interface
public class AdminServiceImp implements AdminService {

    //polimorfism
    @Override
    public void insert(Admin admin) {
        DataAdmin.listAdmin.add(admin);
        JOptionPane.showMessageDialog(null, "Data Karyawan Baru tersimpan!");
    }

    @Override
    public void update(int index, Admin admin) {
        DataAdmin.listAdmin.set(index, admin);
        JOptionPane.showMessageDialog(null, "Data Karyawan Berahasil diubah1");
    }

    @Override
    public void delete(int index) {
        DataAdmin.listAdmin.remove(index);
        JOptionPane.showMessageDialog(null, "Data Karyawan Berhasil dihapus!");
    }

    @Override
    public DefaultTableModel view() {
        String[] kolom = { "Id", "Nama", "Alamat", "Nomor HP" };
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        for (Admin a : DataAdmin.listAdmin) {
            Object[] o = new Object[4];
            o[0] = a.getId();
            o[1] = a.getNama();
            o[2] = a.getAlamat();
            o[3] = a.getnoHP();
            dtm.addRow(o);
        }
        return dtm;
    }
}
