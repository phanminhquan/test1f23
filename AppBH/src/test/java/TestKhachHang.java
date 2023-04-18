/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DonViTinh;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.ThongKe;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.DonViTinhService;
import com.mycompany.service.KhachHangService;
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
public class TestKhachHang {
      @Test
    public void testGetListKhachHang() {
        
        try {
            List<KhachHang> kh = KhachHangService.GetKhachHang();
            
            Assertions.assertTrue(!kh.isEmpty());
            for(KhachHang i: kh){
               Assertions.assertTrue(i !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void testGetListKhachHangByID() {
        
        try {
            String ID ="1";
            KhachHang kh = KhachHangService.GetKhachHangByID(ID).get(0);
            
            Assertions.assertTrue(kh != null);
            Assertions.assertTrue(kh.getMaKhach() != null);
            Assertions.assertTrue(kh.getTenKhach() != null);
            Assertions.assertTrue(kh.getDiaChi() != null);
            Assertions.assertTrue(kh.getDienThoai() != null);
            Assertions.assertTrue(kh.getNgaySinh() != null);
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       @Test
    public void testGetListKhachHangByName() {
        
        try {
            List<KhachHang> kh = KhachHangService.GetKhachHangByName("Nhat");
            
            Assertions.assertTrue(!kh.isEmpty());
            for(KhachHang i: kh){
               Assertions.assertTrue(i !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testAddSuccessful() throws SQLException {
       
        
        try(Connection conn = JdbcUtils.getConn()) {
            KhachHangService s = new KhachHangService();
            String name ="Van";
            String Sdt ="0986553475";
            String DiaChi ="q12";
            Date NgaySinh = new Date(1,2,2002);
            List<KhachHang> list = KhachHangService.GetKhachHang() ;
            boolean test = s.addKhachHang(name, Sdt, DiaChi, NgaySinh);
            List<KhachHang> list1 = KhachHangService.GetKhachHang();
            Assertions.assertTrue(test);
            Assertions.assertEquals(list.size()+1, list1.size());
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
     @Test
    public void testDelete() {
        String id = "2";
        try(Connection conn = JdbcUtils.getConn()) {
            boolean actual = KhachHangService.deleteKhachHang(id);
            Assertions.assertTrue(actual);
            
            PreparedStatement stm = conn.prepareCall("SELECT * FROM tblkhach WHERE MaKhach=?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            Assertions.assertFalse(r.next());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
   public void testUpdate() throws SQLException {
        try(Connection conn = JdbcUtils.getConn()) {
            String id="1";
            String Ten ="Nhat";
            String DiaChi = "bbbbcac";
            String dt = "ccccccdsddasdasdasdasdadadss";
            java.util.Date NgaySinh = new java.util.Date(1,3,2003);
            boolean test = KhachHangService.updateKhachHang(id, Ten, DiaChi, dt, NgaySinh);
            KhachHang b = KhachHangService.GetKhachHangByID(id).get(0);
            Assertions.assertEquals(b.getMaKhach(),id);
            Assertions.assertEquals(b.getTenKhach(),Ten);
            Assertions.assertEquals(b.getDiaChi(),DiaChi);
            Assertions.assertEquals(b.getNgaySinh(),NgaySinh);
            Assertions.assertTrue(test);   
        } catch (SQLException ex) {
            Logger.getLogger(TestKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
