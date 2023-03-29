/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class HoaDonService {

    public static boolean addHoaDon(String MaNV, Date NgayBan, String MaKH, double TongTien, int IDChiNhanh) throws SQLException {
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
            return r > 0;
        }
    }

    public static boolean addHoaDonBan(String maHD, String MaHang, double SL, double DonGia, Double GiamGia, Double ThanhTien) throws SQLException {
        try ( Connection conn = JdbcUtils.getConn()) {
            String sql = "insert into tblchitiethdban values(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);

            stm.setString(1, maHD);
            stm.setString(2, MaHang);
            stm.setString(3, Double.toString(SL));
            stm.setString(4, Double.toString(DonGia));
            stm.setString(5, Double.toString(GiamGia));
            stm.setString(6, Double.toString(ThanhTien));

            int r = stm.executeUpdate();
            return r > 0;
        }
    }
}
