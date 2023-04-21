
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.NhanVien;
import com.mycompany.service.NhanVienService;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class TestNhanVien {
    
//    private static Connection conn;
//    private NhanVienService s = new NhanVienService();
    
    @BeforeAll
    public void bfa(){
        
    }
    
    @AfterAll
    public void afa(){
        
    }
//    
//    @Test
//    public void TestLogin(){
//        int t=1;
//        Assertions.assertTrue(t == 0);
//    }
//    
    
    @Test
    public void testLogin() throws SQLException{
       
         try ( Connection conn = JdbcUtils.getConn()) {           
           String tentk = "nv1";
           String pw =  "123";
           int kq = NhanVienService.Login(tentk, pw);
           Assertions.assertTrue(kq== 1);
          }
        }
        
         
    
    @Test
    public void testGetStaff() throws SQLException{
       List<NhanVien> listNhanVien = new ArrayList<>();
         try ( Connection conn = JdbcUtils.getConn()) {           
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM tblnhanvien");
            while (rs.next()) {
                NhanVien n = new NhanVien(rs.getString("MaNhanVien"), rs.getString("TenNhanVien"),rs.getString("GioiTinh"),rs.getString("DiaChi"),rs.getString("DienThoai"),rs.getDate("NgaySinh"),rs.getInt("idChiNhanh"),rs.getString("Password"));
                listNhanVien.add(n);
            }
        }
         long t = listNhanVien.stream().filter(c -> c.getTenNV() == null).count();
         Assertions.assertTrue(t == 0);
          
    }
    
     @Test
    public void testSearchID() {
        
        try {
            List<NhanVien> nv = NhanVienService.GetNhanVienByID("NV1");
            
            Assertions.assertTrue(!nv.isEmpty());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testSearchName() {
        
        try {
            List<NhanVien> nv = NhanVienService.GetNhanVienByName("aaaaa");
            
            Assertions.assertTrue(!nv.isEmpty());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void testAddSuccessful() throws SQLException {
       
        
        try(Connection conn = JdbcUtils.getConn()) {
            
            NhanVien a = new NhanVien("aaa", "nhat", "Nam", "123", "123", new Date(1,2,2002), 0, "123");
            List<NhanVien> list = NhanVienService.GetNhanVien();
            boolean test = NhanVienService.addNhanVien(a.getTenNV(), a.getGioiTinh(), a.getDiaChi(), a.getDienThoai(), a.getNgaySinh(), a.getIDChiNhanh());
            List<NhanVien> list1 = NhanVienService.GetNhanVien();
            
            
            Assertions.assertTrue(test);
            Assertions.assertEquals(list.size()+1, list1.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testUpdate() throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            
            NhanVien a = NhanVienService.GetNhanVienByID("NV1").get(0);
            String ten="aaaaa";
            String GioiTinh="Nu";
            String Diachi ="bbbbbbb";
            String sdt ="1123123123";
            Date ngaysinh = new Date(1,2,2002);
            int idChiNhanh=2;
            
            boolean test = NhanVienService.updateNhanVien(a.getMaNV(), ten, GioiTinh, Diachi, sdt, ngaysinh, idChiNhanh);
            NhanVien b = NhanVienService.GetNhanVienByID(a.getMaNV()).get(0);
            Assertions.assertEquals(b.getTenNV(),ten);
            Assertions.assertEquals(b.getDiaChi(),Diachi);
            Assertions.assertEquals(b.getDienThoai(),sdt);
            Assertions.assertEquals(b.getNgaySinh(),ngaysinh);
            Assertions.assertEquals(b.getIDChiNhanh(),idChiNhanh);
            Assertions.assertEquals(b.getGioiTinh(),GioiTinh);
            Assertions.assertTrue(test);
            
            
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(TestNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testDelete() {
        String id = "NV2";
        try(Connection conn = JdbcUtils.getConn()) {
            boolean actual = NhanVienService.deleteNhanVien(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM tblnhanvien WHERE MaNhanVien=?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            Assertions.assertFalse(r.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
