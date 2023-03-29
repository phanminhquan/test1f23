/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author DELL
 */
public class ChiTietHoaDonBan {

    /**
     * @return the MaHD
     */
    public String getMaHD() {
        return MaHD;
    }

    /**
     * @return the MaHang
     */
    public String getMaHang() {
        return MaHang;
    }

    /**
     * @param MaHang the MaHang to set
     */
    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    /**
     * @return the SoLuong
     */
    public double getSoLuong() {
        return SoLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(double SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * @return the DonGia
     */
    public double getDonGia() {
        return DonGia;
    }

    /**
     * @param DonGia the DonGia to set
     */
    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    /**
     * @return the GiamGia
     */
    public double getGiamGia() {
        return GiamGia;
    }

    /**
     * @param GiamGia the GiamGia to set
     */
    public void setGiamGia(double GiamGia) {
        this.GiamGia = GiamGia;
    }

    /**
     * @return the ThanhTien
     */
    public double getThanhTien() {
        return ThanhTien;
    }

    /**
     * @param ThanhTien the ThanhTien to set
     */
    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    private String MaHD;
    private String MaHang;
    private double SoLuong;
    private double DonGia;
    private double GiamGia;
    private double ThanhTien;

    public ChiTietHoaDonBan() {
    }

    public ChiTietHoaDonBan(String MaHD, String MaHang, double SoLuong, double DonGia, double GiamGia, double ThanhTien) {
        this.MaHD = MaHD;
        this.MaHang = MaHang;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.GiamGia = GiamGia;
        this.ThanhTien = ThanhTien;
    }
    
}
