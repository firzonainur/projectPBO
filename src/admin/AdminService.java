package admin;

import javax.swing.table.DefaultTableModel;

//interface
public interface AdminService {

    public void insert(Admin admin);

    public void update(int index, Admin admin);

    public void delete(int index);

    public DefaultTableModel view();
}
