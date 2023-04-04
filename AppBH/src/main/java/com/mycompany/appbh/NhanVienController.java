/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.utils.MessageBox;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.NhanVienService;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author DELL
 */
public class NhanVienController implements Initializable {

    @FXML
    private TableView<NhanVien> listNhanVien;

    @FXML
    private TableColumn<NhanVien, String> id;

    @FXML
    private TableColumn<NhanVien, String> TenNhanVien;

    @FXML
    private TableColumn<NhanVien, String> GioiTinh;

    @FXML
    private TableColumn<NhanVien, String> DiaChi;

    @FXML
    private TableColumn<NhanVien, String> DienThoai;

    @FXML
    private TableColumn<NhanVien, Date> NgaySinh;

    @FXML
    private TableColumn<NhanVien, Integer> IDChiNhanh;

    @FXML
    private Button SeachByID;

    @FXML
    private Button SearchByName;

    @FXML
    private TextField nameNhanVien;

    @FXML
    private TextField GioiTinhText;

    @FXML
    private TextField DiaChiText;

    @FXML
    private TextField SoDienThoaihText;
    @FXML
    private DatePicker NgaySinhText;

    @FXML
    private TextField idNhanVien;
    @FXML
    private ComboBox<ChiNhanh> ListChiNhanh;
    
    
 
    

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
     @FXML
     private void switchToTrangChu() throws IOException 
    {
        App.setRoot("Index");
    }
     
    @FXML
    private void LogOut() throws IOException, SQLException {
        UserSession.cleanUserSession();
        App.setRoot("primary");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<NhanVien> nhanvien = NhanVienService.GetNhanVien();
            this.id.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNV"));
            this.TenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TenNV"));
            this.GioiTinh.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("GioiTinh"));
            this.DiaChi.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DiaChi"));
            this.DienThoai.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DienThoai"));
            this.NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanVien, Date>("NgaySinh"));
            this.IDChiNhanh.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("IDChiNhanh"));
            this.listNhanVien.setItems(FXCollections.observableArrayList(nhanvien));
            List<ChiNhanh> ChiNhanhList = ChiNhanhService.GetChiNhanh();
            this.ListChiNhanh.setItems(FXCollections.observableArrayList(ChiNhanhList));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SeacrhStaffByID() throws SQLException {
        this.nameNhanVien.setText("");
        String idNhanVien = this.idNhanVien.getText();
        List<NhanVien> nhanVien = NhanVienService.GetNhanVienByID(idNhanVien);
        this.id.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNV"));
        this.TenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TenNV"));
        this.GioiTinh.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("GioiTinh"));
        this.DiaChi.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DiaChi"));
        this.DienThoai.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DienThoai"));
        this.NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanVien, Date>("NgaySinh"));
        this.IDChiNhanh.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("IDChiNhanh"));
        this.listNhanVien.setItems(FXCollections.observableArrayList(nhanVien));
    }

    public void SeacrhStaffByName() throws SQLException {
        this.idNhanVien.setText("");
        String nameNhanVien = this.nameNhanVien.getText();
        List<NhanVien> nhanVien = NhanVienService.GetNhanVienByName(nameNhanVien);
        this.id.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNV"));
        this.TenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TenNV"));
        this.GioiTinh.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("GioiTinh"));
        this.DiaChi.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DiaChi"));
        this.DienThoai.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("DienThoai"));
        this.NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanVien, Date>("NgaySinh"));
        this.IDChiNhanh.setCellValueFactory(new PropertyValueFactory<NhanVien, Integer>("IDChiNhanh"));
        this.listNhanVien.setItems(FXCollections.observableArrayList(nhanVien));
    }

    public void addNhanVien() throws IOException {
        String sdt = this.SoDienThoaihText.getText();
        String name = this.nameNhanVien.getText();
        String GioiTinh = this.GioiTinhText.getText();
        String DiaChi = this.DiaChiText.getText();
        LocalDate dateIns = this.NgaySinhText.getValue();
        Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
        Date NgaySinh = Date.from(instant);
        int idChiNhanh = ListChiNhanh.getSelectionModel().getSelectedItem().getId();

        NhanVienService s = new NhanVienService();
        try {
            s.addNhanVien(name, GioiTinh, DiaChi, sdt, NgaySinh, idChiNhanh);
            MessageBox.getBox("Nhân viên", "Thêm nhân viên thành công!!!",
                    Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Nhân viên", "Thêm nhân viên thất bại!!!",
                    Alert.AlertType.ERROR).show();
        }
        App.setRoot("NhanVien");

    }
    String IDTextBox = null;

    
    @FXML
    public void g(MouseEvent event) throws SQLException {
            NhanVien a = this.listNhanVien.getSelectionModel().getSelectedItem();
        this.nameNhanVien.setText(a.getTenNV());
        this.idNhanVien.setText(a.getMaNV());
        this.GioiTinhText.setText(a.getGioiTinh());
        this.DiaChiText.setText(a.getDiaChi());
        this.SoDienThoaihText.setText(a.getDienThoai());
        this.NgaySinhText.setValue(LocalDate.parse(a.getNgaySinh().toString()));
        this.ListChiNhanh.setValue(ChiNhanhService.GetChiNhanhByID(Integer.toString(a.getIDChiNhanh())));
        IDTextBox = a.getMaNV();
    }

    public void UpdateNhanVien() throws IOException {
        try {

            String id = this.idNhanVien.getText();

            if (id.equals(IDTextBox)) {
                String sdt = this.SoDienThoaihText.getText();
                String name = this.nameNhanVien.getText();
                String GioiTinh = this.GioiTinhText.getText();
                String DiaChi = this.DiaChiText.getText();
                LocalDate dateIns = this.NgaySinhText.getValue();
                Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
                Date NgaySinh = Date.from(instant);
                int idChiNhanh = ListChiNhanh.getSelectionModel().getSelectedItem().getId();
                NhanVienService.updateNhanVien(id, name, GioiTinh, DiaChi, sdt, NgaySinh, idChiNhanh);
                MessageBox.getBox("Nhân viên", "Sửa nhân viên thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Nhân viên", " Không được sửa mã nhân viên!!!",
                        Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Nhân viên", "Sửa nhân viên thất bại!!!",
                    Alert.AlertType.ERROR).show();

        }
        App.setRoot("NhanVien");

    }

    public void deleteNhanVien() throws IOException {
        String IdNhanVienDel = this.idNhanVien.getText();

        if (!UserSession.getUserID().trim().toLowerCase().equals(IdNhanVienDel.trim().toLowerCase())) {
            try {
                NhanVienService.deleteNhanVien(this.idNhanVien.getText());
                MessageBox.getBox("Nhân viên", "Xóa nhân viên thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                MessageBox.getBox("Nhân viên", "Xóa nhân viên thất bại!!!",
                        Alert.AlertType.ERROR).show();
                ;
            }
            App.setRoot("NhanVien");

        } else {
            MessageBox.getBox("Nhân viên", "Không được xóa nhân viên mà đang đăng nhập!!!",
                    Alert.AlertType.ERROR).show();
        }
    }

}
