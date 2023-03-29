/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.KhachHang;
import com.mycompany.service.KhachHangService;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author DELL
 */
public class KhachHangController implements Initializable {

    /**
     * @return the listKhachHang
     */
    public TableView<KhachHang> getListKhachHang() {
        return listKhachHang;
    }
    @FXML
    private TableView<KhachHang> listKhachHang;

    @FXML
    private TableColumn<KhachHang, String> MaKhach;

    @FXML
    private TableColumn<KhachHang, String> TenKhach;

    @FXML
    private TableColumn<KhachHang, String> DiaChi;

    @FXML
    private TableColumn<KhachHang, String> DienThoai;
    
     @FXML
    private TableColumn<KhachHang, Date> NgaySinh;

    @FXML
    private TextField MaKhachHang;

    @FXML
    private TextField TenKhachHang;

    @FXML
    private TextField DiaChiText;

    @FXML
    private TextField DienThoaiText;
    
      @FXML
    private DatePicker NgaySinhKH;

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

    private void addToTableList() throws SQLException {
        List<KhachHang> khachhang = KhachHangService.GetKhachHang();
        this.MaKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("MaKhach"));
        this.TenKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("TenKhach"));
        this.DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DiaChi"));
        this.DienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DienThoai"));
        this.NgaySinh.setCellValueFactory(new PropertyValueFactory<KhachHang, Date>("NgaySinh"));
        this.getListKhachHang().setItems(FXCollections.observableArrayList(khachhang));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.addToTableList();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void SeacrhCustomerByID() throws SQLException {
        this.TenKhachHang.setText("");
        String MaKhachHang = this.MaKhachHang.getText();
        List<KhachHang> khachhang = KhachHangService.GetKhachHangByID(MaKhachHang);
        this.MaKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("MaKhach"));
        this.TenKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("TenKhach"));
        this.DienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DienThoai"));
        this.DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DiaChi"));
        this.getListKhachHang().setItems(FXCollections.observableArrayList(khachhang));
    }

    public void SeacrhCustomerByName() throws SQLException {
        this.MaKhachHang.setText("");
        String TenKhachHang = this.TenKhachHang.getText();
        List<KhachHang> khachhang = KhachHangService.GetKhachHangByName(TenKhachHang);
        this.MaKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("MaKhach"));
        this.TenKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("TenKhach"));
        this.DienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DienThoai"));
        this.DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DiaChi"));
        this.getListKhachHang().setItems(FXCollections.observableArrayList(khachhang));

    }

    public void addKhachHang() throws IOException, SQLException {
        String name = this.TenKhachHang.getText();
        String dienthoai = this.DienThoaiText.getText();
        String DiaChi = this.DiaChiText.getText();
        LocalDate dateIns = this.NgaySinhKH.getValue();
        Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
        Date birth = Date.from(instant);
        KhachHangService s = new KhachHangService();
        try {
            s.addKhachHang(name, dienthoai, DiaChi,birth);
            MessageBox.getBox("Question", "Thêm khách hàng thành công!!!",
                    Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Question", "Thêm khách hàng thất bại!!!",
                    Alert.AlertType.ERROR).show();
        }
        this.addToTableList();
        this.listKhachHang.refresh();

    }
}
