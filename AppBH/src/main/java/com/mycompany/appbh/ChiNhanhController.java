/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author DELL
 */
public class ChiNhanhController implements Initializable{
    @FXML
    private TableView<ChiNhanh> listChiNhanh;
    
    
    @FXML
    private TableColumn<ChiNhanh, Integer> id;

    @FXML
    private TableColumn<ChiNhanh, String> diachi;

    @FXML
    private TextField idChiNhanhText;
    
    @FXML
    private TextField TenChiNhanhText;
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<ChiNhanh> chinhanh = ChiNhanhService.GetChiNhanh();
            this.id.setCellValueFactory(new PropertyValueFactory<ChiNhanh,Integer>("id"));
            this.diachi.setCellValueFactory(new PropertyValueFactory<ChiNhanh,String>("DiaChi"));
            this.listChiNhanh.setItems(FXCollections.observableArrayList(chinhanh));           
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    public void SeacrhStaffByID() throws SQLException {
        this.TenChiNhanhText.setText("");
        String idChiNhanh = this.idChiNhanhText.getText();
        List<ChiNhanh> chiNhanh = new ArrayList<ChiNhanh>();
        chiNhanh.add((ChiNhanhService.GetChiNhanhByID(idChiNhanh)));
        this.id.setCellValueFactory(new PropertyValueFactory<ChiNhanh, Integer>("id"));
        this.diachi.setCellValueFactory(new PropertyValueFactory<ChiNhanh, String>("DiaChi"));
        this.listChiNhanh.setItems(FXCollections.observableArrayList(chiNhanh));
    }
    
    public void addChiNhanh() throws IOException{
        String name = this.TenChiNhanhText.getText();
        ChiNhanhService s = new ChiNhanhService();
        try {
            s.addChiNhanh(name);
            MessageBox.getBox("Question", "Thêm chi nhánh thành công!!!", 
                    Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Question", "Thêm chi nhánh thất bại!!!", 
                    Alert.AlertType.ERROR).show();
        }
        App.setRoot("ChiNhanh");
        
        
    }
    
    String IDTextBox = null;

    @FXML
    public void g(MouseEvent event) throws SQLException {
        ChiNhanh a = this.listChiNhanh.getSelectionModel().getSelectedItem();
        this.idChiNhanhText.setText(Integer.toString(a.getId()));
        this.TenChiNhanhText.setText(a.getDiaChi());
        IDTextBox = Integer.toString(a.getId());
    }
    public void deleteChiNhanh() throws IOException {
        
            try {
                ChiNhanhService.deleteChiNhanh(this.idChiNhanhText.getText());
                MessageBox.getBox("Chi Nhánh", "Xóa chi nhánh thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                MessageBox.getBox("Chi Nhánh", "Xóa chi nhánh thất bại!!!",
                        Alert.AlertType.ERROR).show();  
            }
            App.setRoot("ChiNhanh");

    }
    
    public void UpdateChiNhanh() throws IOException {
        try {
            
            String id = this.idChiNhanhText.getText();

            if (id.equals(IDTextBox)) {
                String name = this.TenChiNhanhText.getText();
                int id1 = Integer.parseInt(id);
                ChiNhanhService.updateChiNhanh(id1, name);
                MessageBox.getBox("Chi nhánh", "Sửa chi nhánh thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Chi nhánh", " Không được sửa mã chi nhánh!!!",
                        Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Chi nhánh", "Sửa chi nhánh thất bại!!!",
                    Alert.AlertType.ERROR).show();

        }
        App.setRoot("ChiNhanh");

    }
}
