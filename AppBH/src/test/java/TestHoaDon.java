/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.HoaDonBan;
import com.mycompany.pojo.NhanVien;
import com.mycompany.service.HoaDonService;
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
/**
 *
 * @author DELL
 */
public class TestHoaDon {
    
   
    
   @Test
    public void testAdd() {
        
        try {
            int sizebefore = HoaDonService.Gethoadon().size();
            
            String MaNV ="NV1";
            String MaKH ="1";
            double tien = 3000;
            int idcn = 1;
            Date d= new Date(1,1,2023);
            HoaDonBan check = HoaDonService.addHoaDon(MaNV,d, MaKH, tien, idcn);
            
            int sizeafter = HoaDonService.Gethoadon().size();
            
            Assertions.assertTrue(check !=null);
            Assertions.assertEquals(sizebefore+1,sizeafter);  
            
        } catch (SQLException ex) {
            Logger.getLogger(TestHoaDon.class.getName()).log(Level.SEVERE, "", ex);
        }
    }
    
        @Test
    public void testAddChiTietHoaDon() {
        
        try {
            
            HoaDonBan hd = HoaDonService.Gethoadon().get(0);
            String hang ="1";
            double sl =1;
            double dongia = 4000;
            String MaKH ="1";
            double tien = 3000;
            double giam =0;
            double thanhtien = 100;           
            boolean check = HoaDonService.addHoaDonBan(hd, hang, sl, dongia, giam, thanhtien);            
            Assertions.assertEquals(check, true);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestHoaDon.class.getName()).log(Level.SEVERE, "", ex);
        }
    }
    
    
    @Test
    public void testSearchName() {
        
        try {
            List<HoaDonBan> hd = HoaDonService.GetHoaDonByName("nhat");
            
            Assertions.assertTrue(!hd.isEmpty());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestHoaDon.class.getName()).log(Level.SEVERE, "", ex);
        }
    }
    
     @Test
    public void testSearchTime() {
        
        try {
            String day = "23";
            String month = "8";
            String year ="1906";
            
            List<HoaDonBan> hd1 = HoaDonService.GetHoaDonByTime(day, month, year);
            List<HoaDonBan> hd2 = HoaDonService.GetHoaDonByTime(day,"","");
            List<HoaDonBan> hd3 = HoaDonService.GetHoaDonByTime("",month,"");
            List<HoaDonBan> hd4 = HoaDonService.GetHoaDonByTime("","",year);
            List<HoaDonBan> hd5 = HoaDonService.GetHoaDonByTime(day,month,"");
            List<HoaDonBan> hd6 = HoaDonService.GetHoaDonByTime(day,"",year);
            List<HoaDonBan> hd7 = HoaDonService.GetHoaDonByTime("",month,year);
            List<HoaDonBan> hd8 = HoaDonService.GetHoaDonByTime("","","");
            
            Assertions.assertTrue(!hd1.isEmpty());
            Assertions.assertTrue(!hd2.isEmpty());
            Assertions.assertTrue(!hd3.isEmpty());
            Assertions.assertTrue(!hd4.isEmpty());
            Assertions.assertTrue(!hd5.isEmpty());
            Assertions.assertTrue(!hd6.isEmpty());
            Assertions.assertTrue(!hd7.isEmpty());
            Assertions.assertTrue(!hd8.isEmpty());
            
        } catch (SQLException ex) {
            Logger.getLogger(TestHoaDon.class.getName()).log(Level.SEVERE, "", ex);
        }
    }
}
