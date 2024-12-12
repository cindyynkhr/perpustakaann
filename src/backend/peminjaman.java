/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author cinn
 */
public class peminjaman {
    private int idPeminjaman;
    private anggota anggota;
    private buku buku;
    private String tanggalPinjam;
    private String tanggalKembali;
    private boolean status; 

    public peminjaman() {}

    public peminjaman(anggota anggota, buku buku, String tanggalPinjam, String tanggalKembali, boolean status) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // CRUD Operations
    public peminjaman getById(int id) {
        peminjaman peminjaman = null;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM peminjaman WHERE idpeminjaman = " + id);

        try {
            while (rs.next()) {
                peminjaman = new peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.setAnggota(new anggota().getById(rs.getInt("idanggota")));
                peminjaman.setBuku(new buku().getById(rs.getInt("idbuku")));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
                peminjaman.setStatus(rs.getBoolean("status"));
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
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
                peminjaman.setStatus(rs.getBoolean("status"));

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

    public void save() {
        if (getById(idPeminjaman).idPeminjaman == 0) {
            String query = "INSERT INTO peminjaman (idanggota, idbuku, tanggalpinjam, tanggalkembali, status) VALUES ("
                    + this.getAnggota().getIdanggota() + ", "
                    + this.getBuku().getIdBuku() + ", '"
                    + this.getTanggalPinjam() + "', '"
                    + this.getTanggalKembali() + "', "
                    + (this.getStatus() ? 1 : 0) + ")";
            this.idPeminjaman = DBHelper.insertQueryGetId(query);
        } else {
            String query = "UPDATE peminjaman SET idanggota = " + this.getAnggota().getIdanggota()
                    + ", idbuku = " + this.getBuku().getIdBuku()
                    + ", tanggalpinjam = '" + this.getTanggalPinjam()
                    + "', tanggalkembali = '" + this.getTanggalKembali()
                    + "', status = " + (this.getStatus() ? 1 : 0)
                    + " WHERE idpeminjaman = " + this.idPeminjaman;
            DBHelper.executeQuery(query);
        }
    }

    public void delete() {
        String query = "DELETE FROM peminjaman WHERE idpeminjaman = " + this.idPeminjaman;
        DBHelper.executeQuery(query);
    }
}
