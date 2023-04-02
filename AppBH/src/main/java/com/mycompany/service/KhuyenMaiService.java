/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.KhuyenMai;
import com.mycompany.pojo.NhanVien;
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
public class KhuyenMaiService {
    public static List<KhuyenMai> GetKhuyenMai() throws SQLException{
        List<KhuyenMai> listkhuyenmai = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblkhuyenmai");
            while (rs.next()) {
                KhuyenMai c = new KhuyenMai(rs.getInt("id"),rs.getDate("NgayBatDau"),rs.getDate("NgayKetThuc"), rs.getInt("GiaTri"));
                listkhuyenmai.add(c);
            }
        }
        return listkhuyenmai;
    }
    public static KhuyenMai KhuyenMaiByID(String ID) throws SQLException{
        List<KhuyenMai> list = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblkhuyenmai";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE id = ?";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               KhuyenMai n = new KhuyenMai(rs.getInt("id"),rs.getDate("NgayBatDau"),rs.getDate("NgayKetThuc"),rs.getInt("GiaTri"));
               list.add(n);
            }
            KhuyenMai a = list.get(0);
            return a;
    }
}
}
