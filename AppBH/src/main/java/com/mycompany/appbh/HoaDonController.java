/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.HoaDonBan;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.HoaDonService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author DELL
 */
public class HoaDonController implements Initializable {

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
    private void LogOut() throws IOException, SQLException {
        UserSession.cleanUserSession();
        App.setRoot("primary");
    }
    
    @FXML
    private TableView<HoaDonBan> listHoaDonBan;

    @FXML
    private TextField tenKH;
    @FXML
    private TextField Day;
    @FXML
    private TextField Month;
    @FXML
    private TextField Year;

    @FXML
    private TableColumn<HoaDonBan, String> MaHD;

    @FXML
    private TableColumn<HoaDonBan, String> MaNV;

    @FXML
    private TableColumn<HoaDonBan, Date> NgayBan;

    @FXML
    private TableColumn<HoaDonBan, String> MaKhach;

    @FXML
    private TableColumn<HoaDonBan, Double> TongTien;

    @FXML
    private TableColumn<HoaDonBan, Integer> IdChiNhanh;

    private void setupTable() {
        this.MaHD.setCellValueFactory(new PropertyValueFactory<HoaDonBan, String>("MaHD"));
        this.MaNV.setCellValueFactory(new PropertyValueFactory<HoaDonBan, String>("MaNV"));
        this.NgayBan.setCellValueFactory(new PropertyValueFactory<HoaDonBan, Date>("NgayBan"));
        this.MaKhach.setCellValueFactory(new PropertyValueFactory<HoaDonBan, String>("MaKH"));
        this.TongTien.setCellValueFactory(new PropertyValueFactory<HoaDonBan, Double>("TongTien"));
        this.IdChiNhanh.setCellValueFactory(new PropertyValueFactory<HoaDonBan, Integer>("IDChiNhanh"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<HoaDonBan> hoadon = HoaDonService.Gethoadon();
            setupTable();
            this.listHoaDonBan.setItems(FXCollections.observableArrayList(hoadon));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void TimTenKH() throws SQLException {
        try {
            String name = tenKH.getText();
            List<HoaDonBan> list = HoaDonService.GetHoaDonByName(name);
            if(this.tenKH.getText().isEmpty())
        {
            MessageBox.getBox("Hóa Đơn", "Bạn phải nhập dữ liệu cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
            else
        {
            if (list.isEmpty())
                MessageBox.getBox("Hóa đơn", "Không tìm thấy thông tin hóa đơn phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            else {
                setupTable();
                this.listHoaDonBan.setItems(FXCollections.observableArrayList(list));
            }
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void searchabc ()
    {
        try {
                List<HoaDonBan> list = HoaDonService.GetHoaDonByTime(Day.getText(), Month.getText(), Year.getText());
                if (this.Day.getText().isEmpty() && this.Month.getText().isEmpty() && this.Year.getText().isEmpty()) {
                    MessageBox.getBox("Hóa Đơn", "Bạn phải nhập dữ liệu cần tìm!!!",
                            Alert.AlertType.INFORMATION).show();
                } else {
                    if (list.isEmpty()) {
                        MessageBox.getBox("Hóa đơn", "Không tìm thấy thông tin hóa đơn phù hợp!!!",
                                Alert.AlertType.INFORMATION).show();
                    } else {
                        setupTable();
                        this.listHoaDonBan.setItems(FXCollections.observableArrayList(list));
                    }
                }

            }
            catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void findAll()
    {
        try {
            String name = tenKH.getText();
            String day = Day.getText();
            String month = Month.getText();
            String year = Year.getText();
       
            if(this.tenKH.getText().trim().equals(""))
        {
            MessageBox.getBox("Hóa Đơn", "Bạn phải nhập tên khách cần tìm!!!",
                    Alert.AlertType.INFORMATION).show();
        }
            else
        {
            List<HoaDonBan> list = HoaDonService.GetHoaDonAll(name,day,month,year);
            if (list.isEmpty())
                MessageBox.getBox("Hóa đơn", "Không tìm thấy thông tin hóa đơn phù hợp!!!",
                        Alert.AlertType.INFORMATION).show();
            else {
                setupTable();
                this.listHoaDonBan.setItems(FXCollections.observableArrayList(list));
            }
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    @FXML
    public void SearchByTime() throws SQLException {
        try {
            String nam = this.Year.getText();
            if (!this.Year.getText().isEmpty()) {
                this.Year.setText(Integer.toString(Math.abs(Integer.parseInt(this.Year.getText()))));
            }
            String ngay = this.Day.getText();
            if (!this.Day.getText().isEmpty()) {
                if (Integer.parseInt(Day.getText()) < 0 || Integer.parseInt(Day.getText()) > 31) {
                    MessageBox.getBox("Hóa Đơn", "Nhập ngày không phù hợp!!!",
                            Alert.AlertType.INFORMATION).show();
                }
                else
                    searchabc();
                
            } else if (!Day.getText().isEmpty() && !Month.getText().isEmpty()) {
                switch (Integer.parseInt(Month.getText())) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12: {
                        int day = Integer.parseInt(Day.getText());
                        if (Integer.parseInt(Day.getText()) > 31 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            searchabc();
                        }
                        break;
                    }
                    case 4:
                    case 6:
                    case 9:
                    case 11: {
                        if (Integer.parseInt(Day.getText()) > 30 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            searchabc();
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt(Day.getText()) > 29 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            searchabc();
                        }
                        break;

                    }
                    default:
                        MessageBox.getBox("Hóa đơn", "Nhập tháng không phù hợp!!!",
                                Alert.AlertType.INFORMATION).show();
                        break;
                }
            } else {
                searchabc();
            }
        } catch (NumberFormatException ex) {
            MessageBox.getBox("Hóa đơn", "Nhập sai định dạng!!!",
                    Alert.AlertType.INFORMATION).show();
        }

    }
    @FXML
    public void SearchAll() throws SQLException {
        try {
            String nam = this.Year.getText();
            if (!this.Year.getText().isEmpty()) {
                this.Year.setText(Integer.toString(Math.abs(Integer.parseInt(this.Year.getText()))));
            }
            String ngay = this.Day.getText();
            if (!this.Day.getText().isEmpty()) {
                if (Integer.parseInt(Day.getText()) < 0 || Integer.parseInt(Day.getText()) > 31) {
                    MessageBox.getBox("Hóa Đơn", "Nhập ngày không phù hợp!!!",
                            Alert.AlertType.INFORMATION).show();
                }
                else
                    findAll();
                
            } else if (!Day.getText().isEmpty() && !Month.getText().isEmpty()) {
                switch (Integer.parseInt(Month.getText())) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12: {
                        int day = Integer.parseInt(Day.getText());
                        if (Integer.parseInt(Day.getText()) > 31 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            findAll();
                        }
                        break;
                    }
                    case 4:
                    case 6:
                    case 9:
                    case 11: {
                        if (Integer.parseInt(Day.getText()) > 30 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            findAll();
                        }
                        break;
                    }
                    case 2: {
                        if (Integer.parseInt(Day.getText()) > 29 || Integer.parseInt(Day.getText()) < 0) {
                            MessageBox.getBox("Hóa đơn", "Ngày tháng năm không phù hợp!!!",
                                    Alert.AlertType.INFORMATION).show();
                        } else {
                            findAll();
                        }
                        break;

                    }
                    default:
                        MessageBox.getBox("Hóa đơn", "Nhập tháng không phù hợp!!!",
                                Alert.AlertType.INFORMATION).show();
                        break;
                }
            } else {
                findAll();
            }
        } catch (NumberFormatException ex) {
            MessageBox.getBox("Hóa đơn", "Nhập sai định dạng!!!",
                    Alert.AlertType.INFORMATION).show();
        }
    }
}
