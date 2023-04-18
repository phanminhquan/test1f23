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
public class TestChiNhanh {
    @Test
    public void testGetListChiNhanh() {
        
        try {
            List<ChiNhanh> cn = ChiNhanhService.GetChiNhanh();
            
            Assertions.assertTrue(!cn.isEmpty());
            for(ChiNhanh i: cn){
               Assertions.assertTrue(i !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testGetChiNhanhByID() {
        
        try {
            ChiNhanh cn = ChiNhanhService.GetChiNhanhByID("1");
            
            Assertions.assertTrue(cn !=null);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
   public void testUpdate() throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            int idChiNhanh=1;
            String Diachi ="aaaa";
            boolean test = ChiNhanhService.updateChiNhanh(idChiNhanh, Diachi);
            ChiNhanh b = ChiNhanhService.GetChiNhanhByID(Integer.toString(idChiNhanh));
            Assertions.assertEquals(b.getId(),idChiNhanh);
            Assertions.assertEquals(b.getDiaChi(),Diachi);
            Assertions.assertTrue(test);   
        } catch (SQLException ex) {
            Logger.getLogger(TestChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
   @Test
    public void testDelete() {
        String id = "2";
        try(Connection conn = JdbcUtils.getConn()) {
            boolean actual = ChiNhanhService.deleteChiNhanh(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM tblchinhanh WHERE id=?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            Assertions.assertFalse(r.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     @Test
    public void testAddSuccessful() throws SQLException {
       
        
        try(Connection conn = JdbcUtils.getConn()) {
            ChiNhanhService s = new ChiNhanhService();
            ChiNhanh a = new ChiNhanh(1, "Ha Noi");
            List<ChiNhanh> list = ChiNhanhService.GetChiNhanh();
            boolean test = s.addChiNhanh(a.getDiaChi());
            List<ChiNhanh> list1 = ChiNhanhService.GetChiNhanh();
            
            
            Assertions.assertTrue(test);
            Assertions.assertEquals(list.size()+1, list1.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestChiNhanh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
