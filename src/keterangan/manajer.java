/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keterangan;

/**
 *
 * @author Lenovo
 */
// inheritance(pewarisan)
public class manajer extends pegawai {
//polimorfism
public static String setNama(){
        String nama = "Manager";
        return nama;
}public static int setGaji(){
        int gaji = 5000000 ;
        return gaji;
}public static int setTingkat(){
        int tingkat = 1;
        return tingkat;
}    
}
