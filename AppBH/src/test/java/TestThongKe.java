/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
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
public class TestThongKe {
     @Test
    public void testGetListThongKe() {
        
        try {
            List<ThongKe> tk = ThongKeService.ListThongKe();
            
            Assertions.assertTrue(!tk.isEmpty());
            for(ThongKe i: tk){
               Assertions.assertTrue(i !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testThongKeChiNhanh() {
        
        try {
            ChiNhanh cn = ChiNhanhService.GetChiNhanhByID("1");
            String month = "8";
            String year = "1906";
            
            List<ThongKe> tk = ThongKeService.ListThongKeByChiNhanh(cn, month, year);
            
            Assertions.assertTrue(!tk.isEmpty());
            for(ThongKe i: tk){
               Assertions.assertTrue(i !=null); 
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TestThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testGetListThongKeLoaiSanPham() {
        
        try {
            
            LoaiSanPham a =LoaiSanPhamService.GetLoaiSanPhamByID("1").get(0);
            
            
            
            List<ThongKe> tk1 = ThongKeService.ListThongKeByLoaiSP(a);
            
            
            Assertions.assertTrue(!tk1.isEmpty());
            for(ThongKe i: tk1){
               Assertions.assertTrue(i !=null); 
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TestThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
