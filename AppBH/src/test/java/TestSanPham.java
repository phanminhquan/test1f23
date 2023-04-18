
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.Hang;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.service.LoaiSanPhamService;
import com.mycompany.service.SanPhamService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class TestSanPham {
    public void testGetListSP() {
        
        try {
            List<Hang> sp = SanPhamService.GetSanPham();
          
            Assertions.assertTrue(!sp.isEmpty());
            for(Hang l: sp){
               Assertions.assertTrue(l !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetSPByID() {
        
        try {
            Hang l = SanPhamService.GetSanPhamByID("1").get(0);
            
            Assertions.assertTrue(l !=null);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testGetSPByName() {
        
        try {
            Hang l = SanPhamService.GetSanPhamByName("Thịt").get(0);
            
            Assertions.assertTrue(l !=null);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testDelete() {
        String id = "2";
        try(Connection conn = JdbcUtils.getConn()) {
            boolean actual = SanPhamService.deleteSanPham(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM tblhang WHERE MaHang=?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            Assertions.assertFalse(r.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void testUpdate() throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            String MaHang ="1";
            String TenHang ="aaaa";
            String MaLoaiSanPham ="1";
            double SoLuong = 1;
            double DonGiaNhap =99;
            double DonGiaBan= 100;
            String Anh ="ccc";
            String GhiChu ="ddd";
            int IdKhuyenMai=1;
            int DonViTinh=1;
            
            boolean test = SanPhamService.updateSanPham(MaHang, TenHang, MaLoaiSanPham, SoLuong, DonGiaNhap, DonGiaBan, Anh, GhiChu, IdKhuyenMai, DonViTinh);
            Hang b = SanPhamService.GetSanPhamByID(MaHang).get(0);
            Assertions.assertEquals(b.getTenHang(),TenHang);
            Assertions.assertEquals(b.getMaLoaiSanPham(),MaLoaiSanPham);
            Assertions.assertEquals(b.getSoLuong(),SoLuong);
            Assertions.assertEquals(b.getDonGiaNhap(),DonGiaNhap);
            Assertions.assertEquals(b.getDonGiaBan(),DonGiaBan);
            Assertions.assertEquals(b.getAnh(),Anh);
            Assertions.assertEquals(b.getGhiChu(),GhiChu);
            Assertions.assertEquals(b.getIdKhuyenMai(),IdKhuyenMai);
            Assertions.assertEquals(b.getDonViTinh(),DonViTinh);
            
            Assertions.assertTrue(test);   
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testAddSuccessful() throws SQLException {
       
        
        try(Connection conn = JdbcUtils.getConn()) {
            
            
            String TenHang ="bbbbb";
            String MaLoaiSanPham ="1";
            double SoLuong = 1;
            double DonGiaNhap =991;
            double DonGiaBan= 1001;
            String Anh ="ccca";
            String GhiChu ="dddd";
            int IdKhuyenMai=1;
            int DonViTinh=1;
            
            SanPhamService s = new SanPhamService();
            
            List<Hang> list = SanPhamService.GetSanPham();
            boolean test = s.addSanPham(TenHang, MaLoaiSanPham, SoLuong, DonGiaNhap, DonGiaBan, Anh, GhiChu, IdKhuyenMai, DonViTinh);
            List<Hang> list1 = SanPhamService.GetSanPham();
            
            
            Assertions.assertTrue(test);
            Assertions.assertEquals(list.size()+1, list1.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
