/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author cinn
 */
public class pegawai {
    private int idpegawai;
    private String nama;
    private String alamat;
    private String notelp;
    private String jabatan;
    private String username;
    private String password;

    public pegawai() {
    }

    public pegawai(int idpegawai, String nama, String alamat, String notelp, String jabatan, String username, String password) {
        this.idpegawai = idpegawai;
        this.nama = nama;
        this.alamat = alamat;
        this.notelp = notelp;
        this.jabatan = jabatan;
        this.username = username;
        this.password = password;
    }

    public int getIdpegawai() {
        return idpegawai;
    }

    public void setIdpegawai(int idpegawai) {
        this.idpegawai = idpegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     public pegawai getById(int id) {
        pegawai pegawai = new pegawai();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai WHERE idpegawai = " + id + ";");
        try {
            while (rs.next()) {
                pegawai = new pegawai();
                pegawai.setIdpegawai(rs.getInt("idpegawai"));
                pegawai.setNama(rs.getString("nama"));
                pegawai.setAlamat(rs.getString("alamat"));
                pegawai.setNotelp(rs.getString("notelp"));
                pegawai.setJabatan(rs.getString("jabatan"));
                pegawai.setUsername(rs.getString("username"));
                pegawai.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pegawai;
    }
    
    public ArrayList<pegawai> getAll() {
        ArrayList<pegawai> listPegawai = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pegawai");
        try {
            while (rs.next()) {
                pegawai pegawai = new pegawai();
                pegawai.setIdpegawai(rs.getInt("idpegawai"));
                pegawai.setNama(rs.getString("nama"));
                pegawai.setAlamat(rs.getString("alamat"));
                pegawai.setNotelp(rs.getString("notelp"));
                pegawai.setJabatan(rs.getString("jabatan"));
                pegawai.setUsername(rs.getString("username"));
                pegawai.setPassword(rs.getString("password"));
                listPegawai.add(pegawai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPegawai;
    }

    public ArrayList<pegawai> search(String keyword) {
        ArrayList<pegawai> listPegawai = new ArrayList<>();
        String sql = "SELECT * FROM pegawai WHERE " +
                     "nama LIKE '%" + keyword + "%' " +
                     "OR alamat LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                pegawai pegawai = new pegawai();
                pegawai.setIdpegawai(rs.getInt("idpegawai"));
                pegawai.setNama(rs.getString("nama"));
                pegawai.setAlamat(rs.getString("alamat"));
                pegawai.setNotelp(rs.getString("notelp"));
                pegawai.setJabatan(rs.getString("jabatan"));
                pegawai.setUsername(rs.getString("username"));
                pegawai.setPassword(rs.getString("password"));
                listPegawai.add(pegawai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPegawai;
    }

    public void save() {
        if (getById(idpegawai).getIdpegawai()== 0) {
            String SQL = "INSERT INTO pegawai (nama, alamat, notelp, jabatan, username, password) VALUES (" +
                         "'" + this.nama + "', " +
                         "'" + this.alamat + "', " +
                         "'" + this.notelp + "', " + 
                         "'" + this.jabatan + "', " +
                         "'" + this.username + "', " +
                         "'" + this.password + "')";
            this.idpegawai = DBHelper.insertQueryGetId(SQL);
        } else {
            String SQL = "UPDATE pegawai SET " +
             "nama = '" + this.nama + "', " +
             "alamat = '" + this.alamat + "', " +  
             "notelp = '" + this.notelp + "' " +
             "jabatan = '" + this.jabatan + "' " +
             "username = '" + this.username + "' " +
             "password = '" + this.password + "' " +                    
             "WHERE idpegawai = " + this.idpegawai;
            DBHelper.executeQuery(SQL);
        }
    }

    public void delete(){
        String SQL = "DELETE FROM pegawai WHERE idpegawai = '" + this.idpegawai + "'";
        DBHelper.executeQuery(SQL);
    }
}
