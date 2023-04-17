
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.ChiNhanh;
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
public class ChiNhanhService {
    public static List<ChiNhanh> GetChiNhanh() throws SQLException{
        List<ChiNhanh> listchinhanh = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblchinhanh");
            while (rs.next()) {
                ChiNhanh c = new ChiNhanh(rs.getInt("id"), rs.getString("DiaChi"));
                listchinhanh.add(c);
            }
        }
        return listchinhanh;
    }
    
    public static ChiNhanh GetChiNhanhByID(String ID) throws SQLException{
        List<ChiNhanh> listChiNhanh = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblchinhanh";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE id like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               ChiNhanh n = new ChiNhanh(rs.getInt("id"), rs.getString("DiaChi"));
               listChiNhanh.add(n);
            }
            ChiNhanh a = listChiNhanh.get(0);
            return a;
    }
    }
    
    public boolean addChiNhanh(String DiaChi) throws SQLException{
  
        try(Connection conn = JdbcUtils.getConn()){
            int dem = 0;
            Statement stm1 = conn.createStatement();
           
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblchinhanh");
            while(rs.next()){ dem = rs.getInt("id")+1;}
            String sql = "insert into tblchinhanh values(?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            int id = dem;
            stm.setInt(1,id);
            stm.setString(2,DiaChi);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    
    public static boolean deleteChiNhanh(String ID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM tblchinhanh WHERE id =?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ID);
            
            int t = stm.executeUpdate();
            
            return t > 0;
        }
    }
    
    public static boolean updateChiNhanh(int id, String DiaChi ) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "update tblchinhanh set DiaCHi = ? where id = ?";
            PreparedStatement stm = conn.prepareCall(sql);        
            stm.setString(1,DiaChi);
            stm.setInt(2,id);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
}
