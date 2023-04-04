/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.HoaDonBan;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.ThongKe;
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
public class ThongKeService {
    
    public static List<ThongKe> ListThongKe() throws SQLException{
        List<ThongKe> listThongKe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
           
         Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT b.MaHang,sum(b.SoLuong),sum(ThanhTien),c.MaLoaiSanPham FROM quanlybanhang.tblchitiethdban b join quanlybanhang.tblhdban a on a.MaHDBan = b.MaHDBan join quanlybanhang.tblhang c on b.MaHang = c.MaHang group by MaHang");
            while (rs.next()) {
               ThongKe n = new ThongKe(rs.getString("MaHang"), rs.getDouble("sum(b.SoLuong)"), rs.getDouble("sum(ThanhTien)"),0, rs.getString("MaLoaiSanPham"));
                listThongKe.add(n);
            }
            return listThongKe;
    }
    }
    public static List<ThongKe> ListThongKeByLoaiSP(LoaiSanPham a) throws SQLException{
        List<ThongKe> listThongKe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT b.MaHang,sum(b.SoLuong),sum(ThanhTien),a.IdChiNhanh,c.MaLoaiSanPham FROM quanlybanhang.tblchitiethdban b join quanlybanhang.tblhdban a on a.MaHDBan = b.MaHDBan join quanlybanhang.tblhang c on b.MaHang = c.MaHang  group by MaHang,a.IdChiNhanh";
            if (a != null)
                sql += " having c.MaLoaiSanPham = ?;";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (a != null)
                stm.setString(1, a.getMaLoaiSanPham());
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               
               ThongKe n = new ThongKe(rs.getString("MaHang"), rs.getDouble("sum(b.SoLuong)"), rs.getDouble("sum(ThanhTien)"), rs.getInt("IdChiNhanh"), rs.getString("MaLoaiSanPham"));
               listThongKe.add(n);
            }
            return listThongKe;
    }
    }
    
    public static List<ThongKe> ListThongKeByChiNhanh(ChiNhanh a,String Month, String Year) throws SQLException{
        List<ThongKe> listThongKe = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT b.MaHang,sum(b.SoLuong),sum(ThanhTien),a.IdChiNhanh,c.MaLoaiSanPham FROM quanlybanhang.tblchitiethdban b join quanlybanhang.tblhdban a on a.MaHDBan = b.MaHDBan join quanlybanhang.tblhang c on b.MaHang = c.MaHang  ";
            
            if ((Month != null && !Month.isEmpty()) || (Year != null && !Year.isEmpty()))
                sql += " Where ";
            if (Month != null && !Month.isEmpty()){
                sql += " Month(a.NgayBan) = ?";  
                 
            }
            if (Year != null && !Year.isEmpty()){
                if(Month == null || Month.isEmpty())
                    sql += " Year(a.NgayBan) = ?";
                else
                    sql += " and Year(a.NgayBan) = ?"; 
                
            }
            
            sql += " group by MaHang,a.IdChiNhanh";
            
            if (a != null)
                sql += " having IdChiNhanh = ?;";
            
            PreparedStatement stm = conn.prepareCall(sql);
           if (Month != null && !Month.isEmpty()){
                
                 stm.setString(1, Month);
            }
            if (Year != null && !Year.isEmpty()){
                if(Month == null || Month.isEmpty())
                    stm.setString(1, Year);
                else
                    stm.setString(2, Year);
            }
            if (a != null){
                if((Month == null || Month.isEmpty()) && (Year != null && !Year.isEmpty()))
                     stm.setString(2, Integer.toString(a.getId()));
                else if(Year == null || Year.isEmpty()){
                    if(!Month.isEmpty() || Month == null)
                        stm.setString(2,Integer.toString(a.getId()) );
                    else
                        stm.setString(1, Integer.toString(a.getId()));          
                }
                    
                else
                    stm.setString(3, Integer.toString(a.getId()));
            }
            
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               
               ThongKe n = new ThongKe(rs.getString("MaHang"), rs.getDouble("sum(b.SoLuong)"), rs.getDouble("sum(ThanhTien)"), rs.getInt("IdChiNhanh"), rs.getString("MaLoaiSanPham"));
               listThongKe.add(n);
            }
            return listThongKe;
    }
    }
    
    
    
}
