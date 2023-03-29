/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pojo;

/**
 *
 * @author DELL
 */
public class LoaiSanPham {
    
    private String MaLoaiSanPham;
    private String TenLoaiSanPham;
    /**
     * @return the IDLoaiSP
     */
    public String getMaLoaiSanPham() {
        return MaLoaiSanPham;
    }

    public String getTenLoaiSanPham() {
        return TenLoaiSanPham;
    }


    public void setTenLoaiSanPham(String TenLoaiSanPham) {
        this.TenLoaiSanPham = TenLoaiSanPham;
    }
  

    public LoaiSanPham() {
    }

    public LoaiSanPham(String MaLoaiSanPham, String TenLoaiSanPham) {
        this.MaLoaiSanPham = MaLoaiSanPham;
        this.TenLoaiSanPham = TenLoaiSanPham;
    }     
}
