/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.mycompany.service.KhuyenMaiService;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Hang {

    /**
     * @return the soLuongBan
     */
    public int getSoLuongBan() {
        return soLuongBan;
    }

    /**
     * @param soLuongBan the soLuongBan to set
     */
    public void setSoLuongBan(int soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    /**
     * @return the maHang
     */
    public String getMaHang() {
        return MaHang;
    }

    /**
     * @return the TenHang
     */
    public String getTenHang() {
        return TenHang;
    }

    /**
     * @param TenHang the TenHang to set
     */
    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    /**
     * @return the MaLoaiSanPham
     */
    public String getMaLoaiSanPham() {
        return MaLoaiSanPham;
    }

    /**
     * @param MaLoaiSanPham the MaLoaiSanPham to set
     */
    public void setMaLoaiSanPham(String MaLoaiSanPham) {
        this.MaLoaiSanPham = MaLoaiSanPham;
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
     * @return the donGiaNhap
     */
    public double getDonGiaNhap() {
        return DonGiaNhap;
    }

    /**
     * @param donGiaNhap the donGiaNhap to set
     */
    public void setDonGiaNhap(double donGiaNhap) {
        this.DonGiaNhap = donGiaNhap;
    }

    /**
     * @return the DonGiaBan
     */
    public double getDonGiaBan() {
        return DonGiaBan;
    }

    /**
     * @param DonGiaBan the DonGiaBan to set
     */
    public void setDonGiaBan(double DonGiaBan) {
        this.DonGiaBan = DonGiaBan;
    }

    /**
     * @return the Anh
     */
    public String getAnh() {
        return Anh;
    }

    /**
     * @param Anh the Anh to set
     */
    public void setAnh(String Anh) {
        this.Anh = Anh;
    }

    /**
     * @return the GhiChu
     */
    public String getGhiChu() {
        return GhiChu;
    }

    /**
     * @param GhiChu the GhiChu to set
     */
    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    /**
     * @return the IDKhuyenMai
     */
    public int getIdKhuyenMai() {
        return IdKhuyenMai;
    }

    /**
     * @param IDKhuyenMai the IDKhuyenMai to set
     */
    public void setIdKhuyenMai(int IDKhuyenMai) {
        this.IdKhuyenMai = IDKhuyenMai;
    }

    /**
     * @return the IDDonViTinh
     */
    public int getDonViTinh() {
        return DonViTinh;
    }

    /**
     * @param IDDonViTinh the IDDonViTinh to set
     */
    public void setDonViTinh(int IDDonViTinh) {
        this.DonViTinh = IDDonViTinh;
    }

    @Override
    public String toString() {
        return this.TenHang;
    }
    
    
    private String MaHang;
    private String TenHang;
    private String MaLoaiSanPham;
    private double SoLuong;
    private double DonGiaNhap;
    private double DonGiaBan;
    private String Anh;
    private String GhiChu;
    private int IdKhuyenMai;
    private int  DonViTinh;
    
    
    
    
    private int GiaGiam;
    private int soLuongBan;
    private double TongGiaTien;

    public Hang() {
    }

    public Hang(String maHang, String TenHang, String MaLoaiSanPham, double SoLuong, double donGiaNhap, double DonGiaBan, String Anh, String GhiChu, int IDKhuyenMai, int IDDonViTinh) {
        this.MaHang = maHang;
        this.TenHang = TenHang;
        this.MaLoaiSanPham = MaLoaiSanPham;
        this.SoLuong = SoLuong;
        this.DonGiaNhap = donGiaNhap;
        this.DonGiaBan = DonGiaBan;
        this.Anh = Anh;
        this.GhiChu = GhiChu;
        this.IdKhuyenMai = IDKhuyenMai;
        this.DonViTinh = IDDonViTinh;
    }

    /**
     * @return the TongGiaTien
     */
    public double getTongGiaTien() throws SQLException {
        if((this.DonGiaBan) * this.getSoLuongBan()-this.getGiaGiam() > 0)
            return ((this.DonGiaBan) * this.getSoLuongBan()-this.getGiaGiam()*(this.DonGiaBan) * this.getSoLuongBan()/100);
        else return 0;
    }

    /**
     * @param GiaGiam the GiaGiam to set
     */
    public void setGiaGiam(int GiaGiam) {
        this.GiaGiam = GiaGiam;
    }
    
     public int getGiaGiam() throws SQLException {
        var KM = KhuyenMaiService.KhuyenMaiByID(Integer.toString(this.IdKhuyenMai));
        Date today = new Date();
        if(KM.getNgayBatDau().before(today) && KM.getNgayKetThuc().after(today))
            return KM.getGiaTri();
        else return 0;
    }

    /**
     * @param TongGiaTien the TongGiaTien to set
     */
  
    
    
    
}
