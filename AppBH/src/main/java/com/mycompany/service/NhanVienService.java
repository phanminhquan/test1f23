/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.NhanVien;
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
import static javafx.css.SizeUnits.S;

/**
 *
 * @author DELL
 */
public class NhanVienService {
    public static int Login(String IDNV, String pw) throws SQLException{
        try ( Connection conn = JdbcUtils.getConn()) {
           
            PreparedStatement stm = conn.prepareCall("Select * from tblnhanvien where MaNhanVien = ? and Password = ?");            
            stm.setString(1, IDNV);
            stm.setString(2, pw);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                NhanVien n = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),rs.getString("GioiTinh"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"),rs.getInt("idChiNhanh"),rs.getString("Password"));
                if(n.getIDChiNhanh() != 0)
                    return 1;
                return 0;
            }
            else return -1;
        }
    }
    
    public static List<NhanVien> GetNhanVien() throws SQLException{
        List<NhanVien> listNhanVien = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblnhanvien");
            while (rs.next()) {
                NhanVien n = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),rs.getString("GioiTinh"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"),rs.getInt("idChiNhanh"),rs.getString("Password"));
                listNhanVien.add(n);
            }
        }
        return listNhanVien; 
    }
    
    public static List<NhanVien> GetNhanVienByID(String ID) throws SQLException{
        List<NhanVien> listNhanVien = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblnhanvien";
            if (ID != null && !ID.isEmpty())
                sql += " WHERE MaNhanVien = ?";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (ID != null && !ID.isEmpty())
                stm.setString(1, ID);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               NhanVien n = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),rs.getString("GioiTinh"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"),rs.getInt("idChiNhanh"),rs.getString("Password"));
               listNhanVien.add(n);
            }
            NhanVien a = listNhanVien.get(0);
            return listNhanVien;
    }
    }
    public static List<NhanVien> GetNhanVienByName(String Name) throws SQLException{
        List<NhanVien> listNhanVien = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM tblnhanvien";
            if (Name != null && !Name.isEmpty())
                sql += " WHERE TenNhanVien like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (Name != null && !Name.isEmpty())
                stm.setString(1, Name);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
               NhanVien n = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),rs.getString("GioiTinh"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"),rs.getInt("idChiNhanh"),rs.getString("Password"));
               listNhanVien.add(n);
            }
            return listNhanVien;
    }
    }
    
    public static boolean addNhanVien(String name,String GioiTinh,String DiaChi,String SDT,Date NgaySinh,int IDChiNhanh) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            
            Statement stm1 = conn.createStatement();
            String idNV = null;
            ResultSet rs = stm1.executeQuery("SELECT * FROM tblnhanvien");
            while(rs.next()) { idNV = Integer.toString((Integer.parseInt(rs.getString("MaNhanVien").substring(2))+1));  }
            String sql = "insert into tblnhanvien values(?,?,?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareCall(sql);
            String id = "NV"+ idNV;
            stm.setString(1,id);
            stm.setString(2,name);
            stm.setString(3,GioiTinh);
            stm.setString(4,DiaChi);
            stm.setString(5,SDT);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(NgaySinh);
            stm.setString(6,s);
            stm.setString(7,Integer.toString(IDChiNhanh));
            stm.setString(8,"1");
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    
    public static boolean updateNhanVien(String id, String ten, String GioiTinh,String Diachi,String sdt,Date ngaysinh,int idChiNhanh ) throws SQLException{
        
        try(Connection conn = JdbcUtils.getConn()){
            String sql = "update tblnhanvien set TenNhanVien = ? , GioiTinh = ?, DiaChi = ?, DienThoai = ?,NgaySinh = ? , idChiNhanh = ? where  MaNhanVien = ?";
            PreparedStatement stm = conn.prepareCall(sql);        
             stm.setString(1,ten);
            stm.setString(2,GioiTinh);
            stm.setString(3,Diachi);
            stm.setString(4,sdt);
             Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(ngaysinh);
            stm.setString(5,s);
            stm.setString(6,Integer.toString(idChiNhanh));
            stm.setString(7,id);
            int r  = stm.executeUpdate();
            return r >0;           
        }
    }
    public static boolean deleteNhanVien(String ID) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "DELETE FROM tblnhanvien WHERE MaNhanVien=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, ID);
            
            int t = stm.executeUpdate();
            
            return t > 0;
        }
    }
}
