/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DonViTinh;
import com.mycompany.pojo.KhuyenMai;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DonViTinhService {
    public static DonViTinh getDonVITinhByID(String ID) throws SQLException{
        List<DonViTinh> list = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tbldonvitinh";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE id = ?";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               DonViTinh n = new DonViTinh(rs.getInt("id"),rs.getString("Value"));
               list.add(n);
            }
            DonViTinh a = list.get(0);
            return a;
    }
}
}
