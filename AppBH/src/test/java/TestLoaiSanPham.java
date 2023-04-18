/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.ThongKe;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.LoaiSanPhamService;
import com.mycompany.service.NhanVienService;
import com.mycompany.service.ThongKeService;
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
/**
 *
 * @author DELL
 */
public class TestLoaiSanPham {
    @Test
    public void testGetListLoaiSP() {
        
        try {
            List<LoaiSanPham> loaiSP = LoaiSanPhamService.GetLoaiSanPham();
          
            Assertions.assertTrue(!loaiSP.isEmpty());
            for(LoaiSanPham l: loaiSP){
               Assertions.assertTrue(l !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestLoaiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetLoaiSPByID() {
        
        try {
            LoaiSanPham l = LoaiSanPhamService.GetLoaiSanPhamByID("1").get(0);
            
            Assertions.assertTrue(l !=null);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestLoaiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testDelete() {
        String id = "2";
        try(Connection conn = JdbcUtils.getConn()) {
            boolean actual = LoaiSanPhamService.deleteLoaiSanPham(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM tblloaisanpham WHERE MaLoaiSanPham=?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            Assertions.assertFalse(r.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestLoaiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void testUpdate() throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            String id = "1";
            String ten ="aaaa";
            boolean test = LoaiSanPhamService.updateLoaiSanPham(id, ten);
            LoaiSanPham b = LoaiSanPhamService.GetLoaiSanPhamByID(id).get(0);
            Assertions.assertEquals(b.getMaLoaiSanPham(),id);
            Assertions.assertEquals(b.getTenLoaiSanPham(),ten);
            Assertions.assertTrue(test);   
        } catch (SQLException ex) {
            Logger.getLogger(TestLoaiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testAddSuccessful() throws SQLException {
       
        
        try(Connection conn = JdbcUtils.getConn()) {
            LoaiSanPhamService s = new LoaiSanPhamService();
            LoaiSanPham a = new LoaiSanPham("2", "Nuoc ngot");
            List<LoaiSanPham> list = LoaiSanPhamService.GetLoaiSanPham();
            boolean test = s.addLoaiSanPham(a.getTenLoaiSanPham());
            List<LoaiSanPham> list1 = LoaiSanPhamService.GetLoaiSanPham();
            
            
            Assertions.assertTrue(test);
            Assertions.assertEquals(list.size()+1, list1.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestLoaiSanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
