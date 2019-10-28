package admin;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminServiceImp implements AdminService {

    @Override
    public void insert(Admin admin) {
        DataAdmin.listAdmin.add(admin);
        JOptionPane.showMessageDialog(null, "Data Admin Baru tersimpan!");
    }

    @Override
    public void update(int index, Admin admin) {
        DataAdmin.listAdmin.set(index, admin);
        JOptionPane.showMessageDialog(null, "Data Admin Berahasil diubah1");
    }

    @Override
    public void delete(int index) {
        DataAdmin.listAdmin.remove(index);
        JOptionPane.showMessageDialog(null, "Data Admin Berhasil dihapus!");
    }

    @Override
    public DefaultTableModel view() {
        String[] kolom = { "NIP", "Nama", "Alamat", "noHP" };
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        DataAdmin.listAdmin.stream().map((a) -> {
            o = new Object[4];
            o[0] = a.getId();
            o[1] = a.getNama();
            o[2] = a.getAlamat();
            o[3] = a.getnoHP();
            return o;
        }).forEachOrdered((o) -> {
            dtm.addRow(o);
        });
        return dtm;
    }
    
    public Object[] o;
}
