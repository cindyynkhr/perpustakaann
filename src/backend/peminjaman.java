/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author cinn
 */
public class peminjaman {
    private int idPeminjaman;
    private anggota anggota;
    private buku buku;
    private pegawai pegawai;
    private String tanggalPinjam;
    private String tanggalKembali;

    public peminjaman() {}

 
    
    public peminjaman(anggota anggota, buku buku, pegawai pegawai, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.pegawai=pegawai;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(pegawai pegawai) {
        this.pegawai = pegawai;
    }
    
    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(anggota anggota) {
        this.anggota = anggota;
    }

    public buku getBuku() {
        return buku;
    }

    public void setBuku(buku buku) {
        this.buku = buku;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    // CRUD Operations
    public peminjaman getById(int id) {
        peminjaman peminjaman = new peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman WHERE idpeminjaman = " +id);

        try {
            while (rs.next()) {
                peminjaman = new peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.setAnggota(new anggota().getById(rs.getInt("idanggota")));
                peminjaman.setBuku(new buku().getById(rs.getInt("idbuku")));
                peminjaman.setPegawai(new pegawai().getById(rs.getInt("idpegawai")));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }

    public ArrayList<peminjaman> getAll() {
        ArrayList<peminjaman> listPeminjaman = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman");

        try {
            while (rs.next()) {
                peminjaman peminjaman = new peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.setAnggota(new anggota().getById(rs.getInt("idanggota")));
                peminjaman.setBuku(new buku().getById(rs.getInt("idbuku")));
                peminjaman.setPegawai(new pegawai().getById(rs.getInt("idpegawai")));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }
     private boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void save() {
        if (getById(idPeminjaman).idPeminjaman == 0) {
          String query = "INSERT INTO peminjaman ( idanggota, idbuku, idpegawai, tanggalpinjam, tanggalkembali) VALUES ("
//    + this.getIdPeminjaman() + ", "  // idpeminjaman (Angka)
    + this.getAnggota().getIdanggota() + ", "  // idanggota (Angka)
    + this.getBuku().getIdBuku() + ", "  // idbuku (Angka)
    + this.getPegawai().getIdpegawai() + ", '"  // idpegawai (Angka)
    + this.getTanggalPinjam() + "', '"  // tanggalpinjam (Format Tanggal 'YYYY-MM-DD')
    + this.getTanggalKembali() + "');";  // tanggalkembali (Format Tanggal 'YYYY-MM-DD')

// Pastikan untuk menggunakan preparedStatement untuk menghindari SQL injection (lebih aman)

            this.idPeminjaman = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE peminjaman SET tanggalkembali = '" + this.getTanggalKembali()
                    + " WHERE idpeminjaman = " + this.idPeminjaman;
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM peminjaman WHERE idpeminjaman = " + this.idPeminjaman;
        DBHelper.executeQuery(query);
    }
}
