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
public class pengembalian {
    private int idPengembalian;
    private peminjaman peminjaman;
    private String tanggalPengembalian;

    public pengembalian() {}

    public pengembalian(peminjaman peminjaman, String tanggalPengembalian) {
        this.peminjaman = peminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public int getIdPengembalian() {
        return idPengembalian;
    }

    public void setIdPengembalian(int idPengembalian) {
        this.idPengembalian = idPengembalian;
    }

    public peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public String getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public void save() {
        String query = "INSERT INTO pengembalian (idpeminjaman, tanggalpengembalian) VALUES ("
                + this.getPeminjaman().getIdPeminjaman() + ", '"
                + this.getTanggalPengembalian() + "')";
        DBHelper.insertQueryGetId(query);

        DBHelper.executeQuery("UPDATE peminjaman SET status = 1 WHERE idpeminjaman = " + this.getPeminjaman().getIdPeminjaman());
    }

    public pengembalian getById(int id) {
        pengembalian pengembalian = null;
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pengembalian WHERE idpengembalian = " + id);

        try {
            while (rs.next()) {
                pengembalian = new pengembalian();
                pengembalian.setIdPengembalian(rs.getInt("idpengembalian"));
                pengembalian.setPeminjaman(new peminjaman().getById(rs.getInt("idpeminjaman")));
                pengembalian.setTanggalPengembalian(rs.getString("tanggalpengembalian"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pengembalian;
    }

    public ArrayList<pengembalian> getAll() {
        ArrayList<pengembalian> list = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM pengembalian");

        try {
            while (rs.next()) {
                pengembalian pengembalian = new pengembalian();
                pengembalian.setIdPengembalian(rs.getInt("idpengembalian"));
                pengembalian.setPeminjaman(new peminjaman().getById(rs.getInt("idpeminjaman")));
                pengembalian.setTanggalPengembalian(rs.getString("tanggalpengembalian"));

                list.add(pengembalian);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean getStatus() {
        return this.getPeminjaman().getStatus();
    }
}
