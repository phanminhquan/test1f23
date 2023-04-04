/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.LoaiSanPhamService;
import com.mycompany.service.SanPhamService;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class ThongKe {

    /**
     * @return the MaHang
     */
    public String getMaHang() throws SQLException {
        if(MaHang == null)
            return "";
        else
            return SanPhamService.GetSanPhamByID(MaHang).get(0).getTenHang();
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
    public String getIDChiNhanh() throws SQLException {
        if(IDChiNhanh == 0)
            return "";
        else
        return ChiNhanhService.GetChiNhanhByID( Integer.toString(IDChiNhanh)).getDiaChi();
    }

    /**
     * @param IDChiNhanh the IDChiNhanh to set
     */
    public void setIDChiNhanh(int IDChiNhanh) {
        this.IDChiNhanh = IDChiNhanh;
    }

    /**
     * @return the MaLoaiSP
     */
    public String getMaLoaiSP() throws SQLException {
        
        if(MaLoaiSP == null)
            return "";
        else
            return LoaiSanPhamService.GetLoaiSanPhamByID(MaLoaiSP).get(0).getTenLoaiSanPham();
    }

    /**
     * @param MaLoaiSP the MaLoaiSP to set
     */
    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }
    private String MaHang;
    private double SoLuong;
    private double TongTien;
    private int IDChiNhanh;
    private String  MaLoaiSP;

    public ThongKe() {
    }

    public ThongKe(String MaHang, double SoLuong, double TongTien, int IDChiNhanh, String MaLoaiSP) {
        this.MaHang = MaHang;
        this.SoLuong = SoLuong;
        this.TongTien = TongTien;
        this.IDChiNhanh = IDChiNhanh;
        this.MaLoaiSP = MaLoaiSP;
    }
    
}
