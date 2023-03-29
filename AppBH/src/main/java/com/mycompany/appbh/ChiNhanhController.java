/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.ChiNhanh;
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
}
