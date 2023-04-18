/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.mycompany.pojo.ChiNhanh;
import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.DonViTinh;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.ThongKe;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.DonViTinhService;
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
public class TestDonViTinh {
    @Test
    public void testGetListDonViTinh() {
        
        try {
            List<DonViTinh> dv = DonViTinhService.GetDonViTinh();
            
            Assertions.assertTrue(!dv.isEmpty());
            for(DonViTinh i: dv){
               Assertions.assertTrue(i !=null); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestDonViTinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testGetDonViTinhByID() {
        
        try {
            DonViTinh dv = DonViTinhService.getDonVITinhByID("1");
            
            Assertions.assertTrue(dv !=null);
            Assertions.assertTrue(dv.getID() != 0);
            Assertions.assertTrue(dv.getValue() != null);
            
        } catch (SQLException ex) {
            Logger.getLogger(TestDonViTinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
