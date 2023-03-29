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
public class KhuyenMai {

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return the NgayBatDau
     */
    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    /**
     * @param NgayBatDau the NgayBatDau to set
     */
    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    /**
     * @return the NgayKetThuc
     */
    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    /**
     * @param NgayKetThuc the NgayKetThuc to set
     */
    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    /**
     * @return the GiaTri
     */
    public int getGiaTri() {
        return GiaTri;
    }

    /**
     * @param GiaTri the GiaTri to set
     */
    public void setGiaTri(int GiaTri) {
        this.GiaTri = GiaTri;
    }
    private int ID;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private int GiaTri;

    public KhuyenMai() {
    }

    public KhuyenMai(int ID, Date NgayBatDau, Date NgayKetThuc, int GiaTri) {
        this.ID = ID;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.GiaTri = GiaTri;
    }
    
}
