/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.Hang;
import com.mycompany.service.SanPhamService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author DELL
 */
public class SanPhamController implements Initializable{
    @FXML
    public TableView<Hang> listSanPham;
    
    
    @FXML
    private TableColumn<Hang, String> MaHang;
    
    @FXML
    private TableColumn<Hang, String> TenHang;

    @FXML
    private TableColumn<Hang, String> MaLoaiSanPham;
    
    @FXML
    private TableColumn<Hang, Double> SoLuong;
    
    @FXML
    private TableColumn<Hang, Double> DonGiaNhap;
    
    @FXML
    private TableColumn<Hang, Double> DonGiaBan;
    
    @FXML
    private TableColumn<Hang, String> Anh;

    @FXML
    private TableColumn<Hang, String> GhiChu;
    
    @FXML
    private TableColumn<Hang, Integer> IdKhuyenMai;

    @FXML
    private TableColumn<Hang, Integer> DonViTinh;
    
    @FXML
    private TextField MaHangText;
    
    @FXML
    private TextField TenHangText;
    
    @FXML
    private TextField MaLoaiSanPhamText;
    
    @FXML
    private TextField SoLuongText;
    
    @FXML
    private TextField DongiaNhapText;
    
    @FXML
    private TextField DonGiaBanText;
    
    @FXML
    private TextField AnhText;
    
    @FXML
    private TextField GhiChuText;
    
    @FXML
    private TextField IdKhuyenMaiText;
    
    @FXML
    private TextField DonViTinhText;

    @FXML
    private void switchToChiNhanh() throws IOException, SQLException {
        App.setRoot("ChiNhanh");
    }

    @FXML
    private void switchToKhachHang() throws IOException, SQLException {
        App.setRoot("KhachHang");
    }

    @FXML
    private void switchToLoaiSanPham() throws IOException, SQLException {
        App.setRoot("LoaiSanPham");
    }

    @FXML
    private void switchToNhanVien() throws IOException, SQLException {
        App.setRoot("NhanVien");
    }

    @FXML
    private void switchToSanPham() throws IOException, SQLException {
        App.setRoot("SanPham");
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Hang> hang = SanPhamService.GetSanPham();
            this.MaHang.setCellValueFactory(new PropertyValueFactory<Hang,String>("MaHang"));
            this.TenHang.setCellValueFactory(new PropertyValueFactory<Hang,String>("TenHang"));
            this.MaLoaiSanPham.setCellValueFactory(new PropertyValueFactory<Hang,String>("MaLoaiSanPham"));
            this.SoLuong.setCellValueFactory(new PropertyValueFactory<Hang,Double>("SoLuong"));
            this.DonGiaNhap.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaNhap"));
            this.DonGiaBan.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaBan"));
            this.Anh.setCellValueFactory(new PropertyValueFactory<Hang,String>("Anh"));
            this.GhiChu.setCellValueFactory(new PropertyValueFactory<Hang,String>("GhiChu"));
            this.IdKhuyenMai.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("IdKhuyenMai"));
            this.DonViTinh.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("DonViTinh"));
            this.listSanPham.setItems(FXCollections.observableArrayList(hang));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    public void SeacrhStaffByID() throws SQLException {
        this.TenHangText.setText("");
        String idSanPham = this.MaHangText.getText();
        List<Hang> sanpham = SanPhamService.GetSanPhamByID(idSanPham);
        this.MaHang.setCellValueFactory(new PropertyValueFactory<Hang, String>("MaHang"));
        this.TenHang.setCellValueFactory(new PropertyValueFactory<Hang,String>("TenHang"));
        this.MaLoaiSanPham.setCellValueFactory(new PropertyValueFactory<Hang,String>("MaLoaiSanPham"));
        this.SoLuong.setCellValueFactory(new PropertyValueFactory<Hang,Double>("SoLuong"));
        this.DonGiaNhap.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaNhap"));
        this.DonGiaBan.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaBan"));
        this.Anh.setCellValueFactory(new PropertyValueFactory<Hang,String>("Anh"));
        this.GhiChu.setCellValueFactory(new PropertyValueFactory<Hang,String>("GhiChu"));
        this.IdKhuyenMai.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("IdKhuyenMai"));
        this.DonViTinh.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("DonViTinh"));
        this.listSanPham.setItems(FXCollections.observableArrayList(sanpham));
    }
    
    public void SeacrhStaffByName() throws SQLException {
        this.MaHangText.setText("");
        String namesanpham = this.TenHangText.getText();
        List<Hang> sanpham = SanPhamService.GetSanPhamByName(namesanpham);
        this.MaHang.setCellValueFactory(new PropertyValueFactory<Hang, String>("MaHang"));
        this.TenHang.setCellValueFactory(new PropertyValueFactory<Hang,String>("TenHang"));
        this.MaLoaiSanPham.setCellValueFactory(new PropertyValueFactory<Hang,String>("MaLoaiSanPham"));
        this.SoLuong.setCellValueFactory(new PropertyValueFactory<Hang,Double>("SoLuong"));
        this.DonGiaNhap.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaNhap"));
        this.DonGiaBan.setCellValueFactory(new PropertyValueFactory<Hang,Double>("DonGiaBan"));
        this.Anh.setCellValueFactory(new PropertyValueFactory<Hang,String>("Anh"));
        this.GhiChu.setCellValueFactory(new PropertyValueFactory<Hang,String>("GhiChu"));
        this.IdKhuyenMai.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("IdKhuyenMai"));
        this.DonViTinh.setCellValueFactory(new PropertyValueFactory<Hang,Integer>("DonViTinh"));
        this.listSanPham.setItems(FXCollections.observableArrayList(sanpham));
    }
    
    public void addSanPham() throws IOException{
        String TenHang = this.TenHangText.getText();
        String MaLoaiSanPham = this.MaLoaiSanPhamText.getText();
        double SoLuong = Double.parseDouble(this.SoLuongText.getText());
        double DonGiaNhap = Double.parseDouble(this.DongiaNhapText.getText());
        double DonGiaBan = Double.parseDouble(this.DonGiaBanText.getText());
        String Anh = this.AnhText.getText();
        String GhiChu = this.GhiChuText.getText();
        int IdKhuyenMai = Integer.parseInt(this.IdKhuyenMaiText.getText());
        int DonViTinh = Integer.parseInt(this.DonViTinhText.getText());     
        SanPhamService s = new SanPhamService();
        try {
            s.addSanPham(TenHang, MaLoaiSanPham, SoLuong, DonGiaNhap, DonGiaBan, Anh,GhiChu,IdKhuyenMai,DonViTinh);
            MessageBox.getBox("Question", "Thêm sản phẩm thành công!!!", 
                    Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Question", "Thêm sản phẩm thất bại!!!", 
                    Alert.AlertType.ERROR).show();
        }
        App.setRoot("SanPham");
        
        
    }
}
