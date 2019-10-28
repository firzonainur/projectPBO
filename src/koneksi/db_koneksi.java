/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author Lenovo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class db_koneksi {
        // TODO code application logic here
        
    private static Connection conn;
    public static Connection getKoneksi(){
        String host = "jdbc:mysql://localhost/karyawan";
        String user = "root";
        String pass = "";
        try {
            conn = (Connection) DriverManager.getConnection(host, user, pass);
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return conn;
    }
}
    
//    public static Connection conn;
//    public static Statement stat;
//    public static void main(String[] args) {
//        try{
//            String url = "jdbc:mysql://localhost/karyawan";
//            String user = "root";
//            String pass = "";
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, user, pass);
//            stat = conn.createStatement();
//            System.out.println("koneksi berhasil");
//        }catch(Exception e){
//            System.out.println("koneksi gagal" +e.getMessage());
//    }
//        
//    }
