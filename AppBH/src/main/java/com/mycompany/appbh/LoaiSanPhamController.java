/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.service.LoaiSanPhamService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;

/**
 *
 * @author DELL
 */
public class LoaiSanPhamController implements Initializable{
    @FXML
    private TableView<LoaiSanPham> listLoaiSanPham;
    
    
    @FXML
    private TableColumn<LoaiSanPham, String> MaLoaiSanPham;

    @FXML
    private TableColumn<LoaiSanPham, String> TenLoaiSanPham;
    
    @FXML
    private TextField idLoaiSPText;
    
    @FXML
    private TextField TenLoaiSPText;
    
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
            List<LoaiSanPham> loaisanpham = LoaiSanPhamService.GetLoaiSanPham();
            this.MaLoaiSanPham.setCellValueFactory(new PropertyValueFactory<LoaiSanPham,String>("MaLoaiSanPham"));
            this.TenLoaiSanPham.setCellValueFactory(new PropertyValueFactory<LoaiSanPham,String>("TenLoaiSanPham"));
            this.listLoaiSanPham.setItems(FXCollections.observableArrayList(loaisanpham));           
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }
    
    public void SeacrhProductTypeByID() throws SQLException {
        this.TenLoaiSPText.setText("");
        String idLoaiSP = this.idLoaiSPText.getText();
        List<LoaiSanPham> LoaiSP = LoaiSanPhamService.GetLoaiSanPhamByID(idLoaiSP);
        this.MaLoaiSanPham.setCellValueFactory(new PropertyValueFactory<LoaiSanPham, String>("MaLoaiSanPham"));
        this.TenLoaiSanPham.setCellValueFactory(new PropertyValueFactory<LoaiSanPham, String>("TenLoaiSanPham"));
        this.listLoaiSanPham.setItems(FXCollections.observableArrayList(LoaiSP));
    }
    
    public void addLoaiSanPham() throws IOException{
        String name = this.TenLoaiSPText.getText();
        LoaiSanPhamService s = new LoaiSanPhamService();
        try {
            s.addLoaiSanPham(name);
            MessageBox.getBox("Question", "Thêm loại sản phẩm thành công!!!", 
                    Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Question", "Thêm loại sản phẩm thất bại!!!", 
                    Alert.AlertType.ERROR).show();
        }
        App.setRoot("LoaiSanPham");
    }
    
    String IDTextBox = null;

    @FXML
    public void g(MouseEvent event) throws SQLException {
        LoaiSanPham a = this.listLoaiSanPham.getSelectionModel().getSelectedItem();
        this.idLoaiSPText.setText(a.getMaLoaiSanPham());
        this.TenLoaiSPText.setText(a.getTenLoaiSanPham());
        IDTextBox = a.getMaLoaiSanPham();
    }
    public void deleteLoaiSanPham() throws IOException {
        
            try {
                LoaiSanPhamService.deleteLoaiSanPham(this.idLoaiSPText.getText());
                MessageBox.getBox("Loại sản phẩm", "Xóa loại sản phẩm thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } catch (SQLException ex) {
                Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
                MessageBox.getBox("Loại sản phẩm", "Xóa loại sản phẩm thất bại!!!",
                        Alert.AlertType.ERROR).show();  
            }
            App.setRoot("LoaiSanPham");

    }
    
    public void UpdateLoaiSanPham() throws IOException {
        try {
            
            String id = this.idLoaiSPText.getText();

            if (id.equals(IDTextBox)) {
                String name = this.TenLoaiSPText.getText();
                LoaiSanPhamService.updateLoaiSanPham(id, name);
                MessageBox.getBox("Loại sản phẩm", "Sửa loại sản phẩm thành công!!!",
                        Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Loại sản phẩm", " Không được sửa mã loại sản phẩm!!!",
                        Alert.AlertType.ERROR).show();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Loại sản phẩm", "Sửa loại sản phẩm thất bại!!!",
                    Alert.AlertType.ERROR).show();

        }
        App.setRoot("LoaiSanPham");

    }


}
