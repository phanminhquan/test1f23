/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.HoaDonBan;
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
public class HoaDonService {

    public static HoaDonBan addHoaDon(String MaNV, Date NgayBan, String MaKH, double TongTien, int IDChiNhanh) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            int dem = 1;
            Statement stm1 = conn.createStatement();
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblhdban");
            while (rs.next()) {
                dem++;
            }
            String sql = "insert into tblhdban values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            String id = Integer.toString(dem);
            stm.setString(1, id);
            stm.setString(2, MaNV);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(NgayBan);
            stm.setString(3, s);
            stm.setString(4, MaKH);
            stm.setString(5, Double.toString(TongTien));
            stm.setString(6, Integer.toString(IDChiNhanh));

            int r = stm.executeUpdate();
            return new HoaDonBan(id,MaNV, NgayBan, MaKH, TongTien, IDChiNhanh);
        }
    }

    public static boolean addHoaDonBan(HoaDonBan a, String MaHang, double SL, double DonGia, Double GiamGia, Double ThanhTien) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "insert into tblchitiethdban values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, a.getMaHD());
            stm.setString(2, MaHang);
            stm.setString(3, Double.toString(SL));
            stm.setString(4, Double.toString(DonGia));
            stm.setString(5, Double.toString(GiamGia));
            stm.setString(6, Double.toString(ThanhTien));

            int r = stm.executeUpdate();
            return r > 0;
        }
    }
    
    public static List<HoaDonBan> Gethoadon() throws SQLException{
        List<HoaDonBan> listhd = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblhdban");
            while (rs.next()) {
                HoaDonBan c = new HoaDonBan(rs.getString("MaHDBan"), rs.getString("MaNhanVien"),rs.getDate("NgayBan"),rs.getString("MaKhach"),rs.getDouble("TongTien"),rs.getInt("IdChiNhanh"));
                listhd.add(c);
            }
        }
        return listhd;
    }
//    public static List<Hang> GetSanPhamByID(String ID) throws SQLException{
//        List<Hang> listSanPham = new ArrayList<>();
//        try (Connection conn = JdbcUtils.getConn()) {
//            String sql = "SELECT * FROM tblhang";
//            if (ID != null && !ID.isEmpty())
//                sql += " WHERE MaHang = ?";
//            
//            PreparedStatement stm = conn.prepareCall(sql);
//            if (ID != null && !ID.isEmpty())
//                stm.setString(1, ID);
//            
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//               Hang n = new Hang(rs.getString("MaHang"), rs.getString("TenHang"),rs.getString("MaLoaiSanPham"),rs.getDouble("SoLuong"),rs.getDouble("DonGiaNhap"),rs.getDouble("DonGiaBan"),rs.getString("Anh"),rs.getString("GhiChu"),rs.getInt("IdKhuyenMai"),rs.getInt("DonViTinh"));
//               listSanPham.add(n);
//            }
//            return listSanPham;
//    }
//    }
    public static List<HoaDonBan> GetHoaDonByName(String Name) throws SQLException{
        
        List<HoaDonBan> listhoadon = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblhdban a join tblkhach b on a.MaKhach = b.MaKhach";
            if (Name != null && !Name.isEmpty())
                sql += " WHERE b.TenKhach like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (Name != null && !Name.isEmpty())
                stm.setString(1, Name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               HoaDonBan n = new HoaDonBan(rs.getString("MaHDBan"), rs.getString("MaNhanVien"),rs.getDate("NgayBan"),rs.getString("MaKhach"),rs.getDouble("TongTien"),rs.getInt("IdChiNhanh"));
               listhoadon.add(n);
            }
            return listhoadon;
    }
    }
}
