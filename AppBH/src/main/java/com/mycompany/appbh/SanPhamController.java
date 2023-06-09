/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.DonViTinh;
import com.mycompany.pojo.Hang;
import com.mycompany.pojo.KhuyenMai;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.DonViTinhService;
import com.mycompany.service.KhuyenMaiService;
import com.mycompany.service.LoaiSanPhamService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author DELL
 */
public class SanPhamController implements Initializable{
    
    
    @FXML
    private ComboBox<LoaiSanPham> ListLoaiSanPham;
    @FXML
    private ComboBox<KhuyenMai> ListKhuyenMai;
    @FXML
    private ComboBox<DonViTinh> ListDonViTinh;
    
    @FXML
    public TableView<Hang> listSanPham;
    @FXML
    private Button xoa;
    @FXML
    private Button sua;
    
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
     private void switchToTrangChu() throws IOException 
    {
        App.setRoot("Index");
    }
     
      @FXML
    private void LogOut() throws IOException, SQLException {
        UserSession.cleanUserSession();
        App.setRoot("primary");
    }
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
     private void switchToHoaDon() throws IOException
     {
         App.setRoot("HoaDon");
     }
    @FXML
     private void switchToThongKe() throws IOException
     {
         App.setRoot("ThongKe");
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
            List<DonViTinh> donvitinhList = DonViTinhService.GetDonViTinh();
            this.ListDonViTinh.setItems(FXCollections.observableArrayList(donvitinhList));
            List<LoaiSanPham> loaiSanPhamList = LoaiSanPhamService.GetLoaiSanPham();
            this.ListLoaiSanPham.setItems(FXCollections.observableArrayList(loaiSanPhamList));
            List<KhuyenMai> KhuyenMaiList = KhuyenMaiService.GetKhuyenMai();
            this.ListKhuyenMai.setItems(FXCollections.observableArrayList(KhuyenMaiList));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    public void SeacrhStaffByID() throws SQLException {
        this.TenHangText.setText("");
        String idSanPham = this.MaHangText.getText();
        List<Hang> sanpham = SanPhamService.GetSanPhamByID(idSanPham);
        if(this.MaHangText.getText().isEmpty())
        {
            MessageBox.getBox("Sản phẩm", "Bạn phải nhập dữ liệu cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
        else
        {
            if (sanpham.isEmpty())
            {
                MessageBox.getBox("Sản phẩm", "Không tìm thấy mã sản phẩm phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            }
            else {
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
        }
        
        
    }
    
    public void SeacrhStaffByName() throws SQLException {
        this.MaHangText.setText("");
        String namesanpham = this.TenHangText.getText();
        List<Hang> sanpham = SanPhamService.GetSanPhamByName(namesanpham);
        if(this.TenHangText.getText().isEmpty())
        {
            MessageBox.getBox("Sản phẩm", "Bạn phải nhập dữ liệu cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
        else
        {
            if (sanpham.isEmpty())
            {
                MessageBox.getBox("Sản phẩm", "Không tìm thấy tên sản phẩm phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            }
            else {
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
        }
    }
    
    public void addSanPham() throws IOException{
        String TenHang = this.TenHangText.getText();
        LoaiSanPham MaLoaiSanPham = ListLoaiSanPham.getSelectionModel().getSelectedItem();
        String SoLuong = (this.SoLuongText.getText());
        String DonGiaNhap = (this.DongiaNhapText.getText());
        String DonGiaBan = (this.DonGiaBanText.getText());
        String Anh = this.AnhText.getText();
        String GhiChu = this.GhiChuText.getText();
        KhuyenMai IdKhuyenMai = ListKhuyenMai.getSelectionModel().getSelectedItem();
        DonViTinh DonViTinh = ListDonViTinh.getSelectionModel().getSelectedItem();
        if (TenHang.trim().equals("") || MaLoaiSanPham == null || SoLuong == null || DonGiaBan == null || DonGiaNhap == null || IdKhuyenMai == null || DonViTinh == null) {
            MessageBox.getBox("Thông báo", "Vui lòng nhập đầy đủ thông tin!!!",
                    Alert.AlertType.INFORMATION).show();
        } else {
            SanPhamService s = new SanPhamService();
            if (Double.parseDouble(DonGiaNhap) > Double.parseDouble(DonGiaBan)) {
                MessageBox.getBox("Sản phẩm", "Đơn giá nhập phải nhỏ hơn đơn giá bán!!!",
                        Alert.AlertType.INFORMATION).show();
            }
            else if(Double.parseDouble(SoLuong) < 0 || Double.parseDouble(DonGiaNhap) < 0 ||  Double.parseDouble(DonGiaBan) < 0)
            {
                MessageBox.getBox("Sản phẩm", "không thể nhận giá trị âm!!!",
                        Alert.AlertType.INFORMATION).show();
            }
            else {
                try {
                    s.addSanPham(TenHang, MaLoaiSanPham.getMaLoaiSanPham(), Double.parseDouble(SoLuong), Double.parseDouble(DonGiaNhap), Double.parseDouble(DonGiaBan), Anh, GhiChu, IdKhuyenMai.getID(), DonViTinh.getID());
                    MessageBox.getBox("Question", "Thêm sản phẩm thành công!!!",
                            Alert.AlertType.INFORMATION).show();
                } catch (NumberFormatException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                    MessageBox.getBox("Question", "Nhập sai định dạng!!!",
                            Alert.AlertType.ERROR).show();
                } catch (SQLException ex) {
                    Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                    MessageBox.getBox("Question", "Thêm sản phẩm thất bại!!!",
                            Alert.AlertType.ERROR).show();

                }

                App.setRoot("SanPham");
            }

        }

    }
    
    String IDTextBox = null;

    @FXML
    public void g(MouseEvent event) throws SQLException {
        Hang a = this.listSanPham.getSelectionModel().getSelectedItem();
        this.MaHangText.setText(a.getMaHang());
        this.TenHangText.setText(a.getTenHang());
        this.SoLuongText.setText(Double.toString(a.getSoLuong()));
        this.DongiaNhapText.setText(Double.toString(a.getDonGiaNhap()));
        this.DonGiaBanText.setText(Double.toString(a.getDonGiaBan()));
        this.AnhText.setText(a.getAnh());
        this.GhiChuText.setText(a.getGhiChu());
        this.ListDonViTinh.setValue(DonViTinhService.getDonVITinhByID(Integer.toString(a.getDonViTinh())));
        this.ListLoaiSanPham.setValue(LoaiSanPhamService.GetLoaiSanPhamByID(a.getMaLoaiSanPham()).get(0));
        this.ListKhuyenMai.setValue(KhuyenMaiService.KhuyenMaiByID(Integer.toString(a.getIdKhuyenMai())));
        IDTextBox = a.getMaHang();
        this.xoa.setDisable(false);
        this.sua.setDisable(false);
    }

    public void UpdateSanPham() throws IOException {
        try {

            String id = this.MaHangText.getText();

            if (id.equals(IDTextBox)) {
                String tenhang = this.TenHangText.getText();
                LoaiSanPham loaisanpham = ListLoaiSanPham.getSelectionModel().getSelectedItem();
                String soluong = this.SoLuongText.getText();
                String dongianhap = this.DongiaNhapText.getText();
                String dongiaban = this.DonGiaBanText.getText();
                String anh = this.AnhText.getText();
                String ghichu = this.GhiChuText.getText();
                KhuyenMai idKhuyenMai = ListKhuyenMai.getSelectionModel().getSelectedItem();
                DonViTinh DonViTinh = ListDonViTinh.getSelectionModel().getSelectedItem();
                if (tenhang.trim().equals("") || loaisanpham == null || soluong == null || dongianhap == null || dongiaban == null || idKhuyenMai == null || DonViTinh == null) {
                    MessageBox.getBox("Thông báo", "Vui lòng nhập đầy đủ thông tin!!!",
                            Alert.AlertType.INFORMATION).show();
                }
                else if(Double.parseDouble(soluong) < 0 || Double.parseDouble(dongianhap) < 0 ||  Double.parseDouble(dongiaban) < 0)
            {
                MessageBox.getBox("Sản phẩm", "không thể nhận giá trị âm!!!",
                        Alert.AlertType.INFORMATION).show();
            }
                else {
                    if (Double.parseDouble(dongianhap) > Double.parseDouble(dongiaban)) {
                        MessageBox.getBox("Sản phẩm", "Đơn giá nhập phải nhỏ hơn đơn giá bán!!!",
                                Alert.AlertType.INFORMATION).show();
                    } else {
                        SanPhamService.updateSanPham(id, tenhang, loaisanpham.getMaLoaiSanPham(), Double.parseDouble(soluong), Double.parseDouble(dongianhap), Double.parseDouble(dongiaban), anh, ghichu, idKhuyenMai.getID(), DonViTinh.getID());
                        MessageBox.getBox("Sản phẩm", "Sửa nhân sản phẩm thành công!!!",
                                Alert.AlertType.INFORMATION).show();
                    }

                }

            } else {
                MessageBox.getBox("Sản phẩm", " Không được sửa mã sản phẩm!!!",
                        Alert.AlertType.ERROR).show();
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Question", "Nhập sai định dạng!!!",
                    Alert.AlertType.ERROR).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Sản phẩm", "Sửa sản phẩm thất bại!!!",
                    Alert.AlertType.ERROR).show();

        }
        App.setRoot("SanPham");

    }

    public void deleteSanPham() throws IOException {
            try {
                SanPhamService.deleteSanPham(this.MaHangText.getText());
                MessageBox.getBox("Sản phẩm", "Xóa sản phẩm thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                MessageBox.getBox("Sản phẩm", "Xóa sản phẩm thất bại!!!",
                        Alert.AlertType.ERROR).show();
                ;
            }
            App.setRoot("SanPham");
        }
    
}
