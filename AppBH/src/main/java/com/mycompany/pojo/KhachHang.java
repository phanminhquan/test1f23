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
public class KhachHang {

    /**
     * @return the NgaySinh
     */
    public Date getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    private String MaKhach;
    private String TenKhach;
    private String DiaChi;
    private String DienThoai;
    private Date NgaySinh;
    
    public KhachHang(){} 

    public KhachHang(String maKH, String TenKhachhang, String DiaChi, String DienThoai,Date NgaySinh) {
        this.MaKhach = maKH;
        this.TenKhach = TenKhachhang;
        this.DiaChi = DiaChi;
        this.DienThoai = DienThoai;
        this.NgaySinh = NgaySinh;
    }
    
   

    /**
     * @return the maKH
     */
    public String getMaKhach() {
        return MaKhach;
    }

    /**
     * @param maKH the maKH to set
     */

    /**
     * @return the TenKhachhang
     */
    public String getTenKhach() {
        return TenKhach;
    }

    /**
     * @param TenKhachhang the TenKhachhang to set
     */
    public void setTenKhachhang(String TenKhachhang) {
        this.TenKhach = TenKhachhang;
    }

    /**
     * @return the DiaChi
     */
    public String getDiaChi() {
        return DiaChi;
    }

    /**
     * @param DiaChi the DiaChi to set
     */
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    /**
     * @return the DienThoai
     */
    public String getDienThoai() {
        return DienThoai;
    }

    /**
     * @param DienThoai the DienThoai to set
     */
    public void setDienThoai(String DienThoai) {
        this.DienThoai = DienThoai;
    }
   
}
