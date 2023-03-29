/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Hang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SanPhamService {
    public static List<Hang> GetSanPham() throws SQLException{
        List<Hang> listsanpham = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblhang");
            while (rs.next()) {
                Hang c = new Hang(rs.getString("MaHang"),
                        rs.getString("TenHang"),
                        rs.getString("MaLoaiSanPham"),
                        rs.getDouble("SoLuong"),
                        rs.getDouble("DonGiaNhap"),
                        rs.getDouble("DonGiaBan"),
                        rs.getString("Anh"),
                        rs.getString("GhiChu"),
                        rs.getInt("IdKhuyenMai"),
                        rs.getInt("DonViTinh"));
                listsanpham.add(c);
            }
        }
        return listsanpham;
    }
    
    public static List<Hang> GetSanPhamByID(String ID) throws SQLException{
        List<Hang> listSanPham = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblhang";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE MaHang = ?";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               Hang n = new Hang(rs.getString("MaHang"), rs.getString("TenHang"),rs.getString("MaLoaiSanPham"),rs.getDouble("SoLuong"),rs.getDouble("DonGiaNhap"),rs.getDouble("DonGiaBan"),rs.getString("Anh"),rs.getString("GhiChu"),rs.getInt("IdKhuyenMai"),rs.getInt("DonViTinh"));
               listSanPham.add(n);
            }
            return listSanPham;
    }
    }
    public static List<Hang> GetSanPhamByName(String Name) throws SQLException{
        
        List<Hang> listSanPham = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblhang";
            if (Name != null && !Name.isEmpty())
                sql += " WHERE TenHang like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (Name != null && !Name.isEmpty())
                stm.setString(1, Name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               Hang n = new Hang(rs.getString("MaHang"), rs.getString("TenHang"),rs.getString("MaLoaiSanPham"),rs.getDouble("SoLuong"),rs.getDouble("DonGiaNhap"),rs.getDouble("DonGiaBan"),rs.getString("Anh"),rs.getString("GhiChu"),rs.getInt("IdKhuyenMai"),rs.getInt("DonViTinh"));
               listSanPham.add(n);
            }
            return listSanPham;
    }
    }
    
    public boolean addSanPham(String TenHang,String MaLoaiSanPham,double SoLuong,double DonGiaNhap,double DonGiaBan,String Anh, String GhiChu, int IdKhuyenMai, int DonViTinh) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            int dem = 1;
            Statement stm1 = conn.createStatement();
           
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblhang");
            while(rs.next()) dem++;
            String sql = "insert into tblhang values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            String id = "H"+ Integer.toString(dem);
            stm.setString(1,id);
            stm.setString(2,TenHang);
            stm.setString(3,MaLoaiSanPham);
            stm.setDouble(4,SoLuong);
            stm.setDouble(5,DonGiaNhap);
            stm.setDouble(6,DonGiaBan);
            stm.setString(7,Anh);
            stm.setString(8,GhiChu);
            stm.setInt(9,IdKhuyenMai);
            stm.setInt(10,DonViTinh);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    
}
