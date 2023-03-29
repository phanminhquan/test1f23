/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class HoaDonBan {

    /**
     * @return the MaHD
     */
    public String getMaHD() {
        return MaHD;
    }

    /**
     * @return the MaNV
     */
    public String getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    /**
     * @return the NgayBan
     */
    public Date getNgayBan() {
        return NgayBan;
    }

    /**
     * @param NgayBan the NgayBan to set
     */
    public void setNgayBan(Date NgayBan) {
        this.NgayBan = NgayBan;
    }

    /**
     * @return the MaKH
     */
    public String getMaKH() {
        return MaKH;
    }

    /**
     * @param MaKH the MaKH to set
     */
    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    /**
     * @return the TongTien
     */
    public double getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    /**
     * @return the IDChiNhanh
     */
    public int getIDChiNhanh() {
        return IDChiNhanh;
    }

    /**
     * @param IDChiNhanh the IDChiNhanh to set
     */
    public void setIDChiNhanh(int IDChiNhanh) {
        this.IDChiNhanh = IDChiNhanh;
    }
    private String MaHD;
    private String MaNV;
    private Date NgayBan;
    private String MaKH;
    private double TongTien;
    private int IDChiNhanh;

    public HoaDonBan() {
    }

    public HoaDonBan(String MaHD, String MaNV, Date NgayBan, String MaKH, double TongTien, int IDChiNhanh) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.NgayBan = NgayBan;
        this.MaKH = MaKH;
        this.TongTien = TongTien;
        this.IDChiNhanh = IDChiNhanh;
    }
    
    
}
