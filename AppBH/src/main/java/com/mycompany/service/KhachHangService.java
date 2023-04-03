/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.KhachHang;
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
public class KhachHangService {
    public static List<KhachHang> GetKhachHang() throws SQLException{
        List<KhachHang> listkhachhang = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblkhach");
            while (rs.next()) {
                KhachHang c = new KhachHang(rs.getString("MaKhach"), rs.getString("TenKhach"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"));
                listkhachhang.add(c);
            }
        }
        return listkhachhang;
    }
    
    public static List<KhachHang> GetKhachHangByID(String ID) throws SQLException{
        List<KhachHang> listKhachHang = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblkhach";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE MaKhach = ?";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               KhachHang n = new KhachHang(rs.getString("MaKhach"), rs.getString("TenKhach"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"));
               listKhachHang.add(n);
            }
            return listKhachHang;
    }
    }
    public static List<KhachHang> GetKhachHangByName(String Name) throws SQLException{
        List<KhachHang> listKhachHang = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblkhach";
            if (Name != null && !Name.isEmpty())
                sql += " WHERE TenKhach like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (Name != null && !Name.isEmpty())
                stm.setString(1, Name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               KhachHang n = new KhachHang(rs.getString("MaKhach"), rs.getString("TenKhach"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"));
               listKhachHang.add(n);
            }
            return listKhachHang;
    }
    }
    
    public boolean addKhachHang(String name,String Sdt,String DiaChi,Date NgaySinh) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            int dem = 1;
            Statement stm1 = conn.createStatement();
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblkhach");
            while(rs.next()) dem++;
            String sql = "insert into tblkhach values(?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            String id = Integer.toString(dem);
            stm.setString(1,id);
            stm.setString(2,name);
            stm.setString(3,Sdt);
            stm.setString(4,DiaChi);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(NgaySinh);
            stm.setString(5,s);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    
    public static boolean updateKhachHang(String makhach, String name,String Sdt,String DiaChi,Date NgaySinh) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "update tblkhach set TenKhach = ? , DiaChi = ?, DienThoai = ?,NgaySinh = ? where  MaKhach = ?";
            PreparedStatement stm = conn.prepareCall(sql);        
             stm.setString(1,name);
            stm.setString(2,Sdt);
            stm.setString(3,DiaChi);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(NgaySinh);
            stm.setString(4,s);
            stm.setString(5,makhach);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    public static boolean deleteKhachHang(String ID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM tblkhach WHERE MaKhach=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ID);
            
            int t = stm.executeUpdate();
            
            return t > 0;
        }
    }
}
