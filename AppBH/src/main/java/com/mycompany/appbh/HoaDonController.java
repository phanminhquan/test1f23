/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.HoaDonBan;
import com.mycompany.service.HoaDonService;
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
            setupTable();
            this.listHoaDonBan.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void SearchByTime() throws SQLException {
        try {
            List<HoaDonBan> list = HoaDonService.GetHoaDonByTime(Day.getText(), Month.getText(), Year.getText());
            setupTable();
            this.listHoaDonBan.setItems(FXCollections.observableArrayList(list));
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
