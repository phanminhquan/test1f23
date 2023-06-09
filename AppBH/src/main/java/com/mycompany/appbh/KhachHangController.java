/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import static com.mycompany.appbh.IndexController.LOCAL_DATE;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.KhachHangService;
import com.mycompany.service.NhanVienService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
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
import javafx.scene.input.MouseEvent;
import java.util.Date;
import javafx.scene.control.Button;

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
    private Button xoa;
    @FXML
    private Button sua;
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
    public Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public void SeacrhCustomerByID() throws SQLException {
        this.TenKhachHang.setText("");
        String MaKhachHang = this.MaKhachHang.getText();
        List<KhachHang> khachhang = KhachHangService.GetKhachHangByID(MaKhachHang);
        if(this.MaKhachHang.getText().isEmpty())
        {
            MessageBox.getBox("Khách Hàng", "Bạn phải nhập dữ liệu cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
        else
        {
            if (khachhang.isEmpty())
                MessageBox.getBox("Thông báo", "Không tìm thấy mã khách hàng phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            else {
                this.MaKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("MaKhach"));
                this.TenKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("TenKhach"));
                this.DienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DienThoai"));
                this.DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DiaChi"));
                this.getListKhachHang().setItems(FXCollections.observableArrayList(khachhang));
            }
        }
    }

    public void SeacrhCustomerByName() throws SQLException {
        this.MaKhachHang.setText("");
        String TenKhachHang = this.TenKhachHang.getText();
        List<KhachHang> khachhang = KhachHangService.GetKhachHangByName(TenKhachHang);
        if(this.TenKhachHang.getText().isEmpty())
        {
            MessageBox.getBox("Khách Hàng", "Bạn phải nhập dữ liệu cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
        else
        {
            if (khachhang.isEmpty())
                MessageBox.getBox("Thông báo", "Không tìm thấy tên khách hàng phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            else {
                this.MaKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("MaKhach"));
                this.TenKhach.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("TenKhach"));
                this.DienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DienThoai"));
                this.DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang, String>("DiaChi"));
                this.getListKhachHang().setItems(FXCollections.observableArrayList(khachhang));
            }
        }
        

    }

    
    
    public void addKhachHang() throws IOException, SQLException {
        String name = this.TenKhachHang.getText();
        
        
        
        if (name.trim().equals("") || this.DiaChiText.getText().trim().equals("") || this.DienThoaiText.getText().trim().equals("")) {
            MessageBox.getBox("Thông báo", "Vui lòng nhập đầy đủ thông tin!!!",
                    Alert.AlertType.INFORMATION).show();
        } else {
            boolean kt1 = false;
            for (int i = 0; i < name.replaceAll(" ", "").length(); i++) {

                if (!Character.isLetter(name.replaceAll(" ", "").charAt(i))) {
                    kt1 = true;
                    break;
                }
            }
            if (kt1) {
                MessageBox.getBox("Khách Hàng", "Tên khách hàng không đúng định dạng!!!",
                        Alert.AlertType.INFORMATION).show();
            } else {
                String dienthoai = this.DienThoaiText.getText();
                boolean kt = false;
                for (int i = 0; i < dienthoai.length(); i++) {

                    int so = dienthoai.charAt(i);
                    if (so < 48 || so > 57) {
                        kt = true;
                        break;
                    }
                }
                int so = dienthoai.length();
                String dt = dienthoai.substring(0, 1);
                if (!dt.equals("0") || so != 10 || kt) {
                    MessageBox.getBox("Khách Hàng", "Số điện thoại không đúng định dạng!!!",
                            Alert.AlertType.INFORMATION).show();
                } else {
                    String DiaChi = this.DiaChiText.getText();
                    LocalDate dateIns = this.NgaySinhKH.getValue();
                    Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
                    Date birth = Date.from(instant);
                    Date today = new Date();
                    
                    if(birth.after(today) || removeTime(today).equals(removeTime(birth))){
                         MessageBox.getBox("Khách Hàng", "Năm sinh không hợp lệ!!!",
                            Alert.AlertType.INFORMATION).show();
                    }
                    else{
                        KhachHangService s = new KhachHangService();
                        try {
                            s.addKhachHang(name, DiaChi, dienthoai, birth);
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
            }
        }

    }
    
    String IDTextBox = null;

    @FXML
    public void g(MouseEvent event) throws SQLException {
        KhachHang a = this.listKhachHang.getSelectionModel().getSelectedItem();
        this.TenKhachHang.setText(a.getTenKhach());
        this.MaKhachHang.setText(a.getMaKhach());
        this.DiaChiText.setText(a.getDiaChi());
        this.DienThoaiText.setText(a.getDienThoai());
        this.NgaySinhKH.setValue(LocalDate.parse(a.getNgaySinh().toString()));
        IDTextBox = a.getMaKhach();
        this.xoa.setDisable(false);
        this.sua.setDisable(false);
    }

    public void UpdateKhachHang() throws IOException {
        try {

            String id = this.MaKhachHang.getText();

            if (id.equals(IDTextBox)) {
                String tenKhach = this.TenKhachHang.getText();
                if (tenKhach.trim().equals("") || this.DiaChiText.getText().trim().equals("") || this.DienThoaiText.getText().trim().equals("")) {
                    MessageBox.getBox("Thông báo", "Vui lòng nhập đầy đủ thông tin!!!",
                            Alert.AlertType.INFORMATION).show();
                } else {
                    boolean kt1 = false;
                    for (int i = 0; i < tenKhach.replaceAll(" ", "").length(); i++) {

                        if (!Character.isLetter(tenKhach.replaceAll(" ", "").charAt(i))) {
                            kt1 = true;
                            break;
                        }
                    }
                    if (kt1) {
                        MessageBox.getBox("Khách Hàng", "Tên khách hàng không đúng định dạng!!!",
                                Alert.AlertType.INFORMATION).show();
                    } else {
                        String diachi = this.DiaChiText.getText();
                        String dienthoai = this.DienThoaiText.getText();
                        boolean kt = false;
                        for (int i = 0; i < dienthoai.length(); i++) {
                            int so = dienthoai.charAt(i);
                            if (so < 48 || so > 57) {
                                kt = true;
                                break;
                            }
                        }
                        if (dienthoai.length() != 10 || !dienthoai.substring(0, 1).equals("0") || kt) {
                            MessageBox.getBox("Khách Hàng", "Số điện thoại không đúng định dạng!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            LocalDate dateIns = this.NgaySinhKH.getValue();
                            Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
                            Date NgaySinh = Date.from(instant);
                            
                           
                            Date today = new Date();
                    
                    if(NgaySinh.after(today) || removeTime(today).equals(removeTime(NgaySinh))){
                         MessageBox.getBox("Khách Hàng", "Năm sinh không hợp lệ!!!",
                            Alert.AlertType.INFORMATION).show();
                    }
                    else{
                            KhachHangService.updateKhachHang(id, tenKhach, diachi, dienthoai, NgaySinh);
                            MessageBox.getBox("Khách Hàng", "Sửa khách hàng thành công!!!",
                                    Alert.AlertType.INFORMATION).show();
                        }
                        }
                    }
                }

            } else {
                MessageBox.getBox("Khách Hàng", " Không được sửa mã khách hàng!!!",
                        Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Khách Hàng", "Sửa khách hàng thất bại!!!",
                    Alert.AlertType.ERROR).show();

        }
        App.setRoot("KhachHang");

    }

    public void deleteKhachHang() throws IOException {
            try {
                KhachHangService.deleteKhachHang(this.MaKhachHang.getText());
                MessageBox.getBox("Khách Hàng", "Xóa khách hàng thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                MessageBox.getBox("Khách Hàng", "Xóa khách hàng thất bại!!!",
                        Alert.AlertType.ERROR).show();
                ;
            }
            App.setRoot("KhachHang");

        } 
}
