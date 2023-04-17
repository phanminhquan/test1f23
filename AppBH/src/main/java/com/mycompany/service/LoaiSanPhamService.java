/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;


import com.mycompany.pojo.LoaiSanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class LoaiSanPhamService {
    public static List<LoaiSanPham> GetLoaiSanPham() throws SQLException{
        List<LoaiSanPham> listloaisanpham = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblloaisanpham");
            while (rs.next()) {
                LoaiSanPham c = new LoaiSanPham(rs.getString("MaLoaiSanPham"), rs.getString("TenLoaiSanPham"));
                listloaisanpham.add(c);
            }
        }
        return listloaisanpham;
    }
    
    public static List<LoaiSanPham> GetLoaiSanPhamByID(String ID) throws SQLException{
        List<LoaiSanPham> listLoaiSanPham = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblloaisanpham";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE MaLoaiSanPham like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               LoaiSanPham n = new LoaiSanPham(rs.getString("MaLoaiSanPham"), rs.getString("TenLoaiSanPham"));
               listLoaiSanPham.add(n);
            }
            return listLoaiSanPham;
    }
    }
    
    public boolean addLoaiSanPham(String LoaiSanPham) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            int dem = 1;
            Statement stm1 = conn.createStatement();
           
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblloaisanpham");
            while(rs.next()) dem++;
            String sql = "insert into tblloaisanpham values(?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            String id = "SP"+ Integer.toString(dem);
            stm.setString(1,id);
            stm.setString(2,LoaiSanPham);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    
    public static boolean deleteLoaiSanPham(String ID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM tblloaisanpham WHERE MaLoaiSanPham =?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ID);
            
            int t = stm.executeUpdate();
            
            return t > 0;
        }
    }
    
    public static boolean updateLoaiSanPham(String Ma, String Ten ) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "update tblloaisanpham set TenLoaiSanPham = ? where MaLoaiSanPham = ?";
            PreparedStatement stm = conn.prepareCall(sql);        
            stm.setString(1,Ten);
            stm.setString(2,Ma);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
}
