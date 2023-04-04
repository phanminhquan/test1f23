/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.appbh;

import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.Hang;
import com.mycompany.pojo.LoaiSanPham;
import com.mycompany.pojo.ThongKe;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.LoaiSanPhamService;
import com.mycompany.service.ThongKeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;

/**
 *
 * @author DELL
 */
public class ThongKeController implements Initializable {

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
    
    
    
    @FXML
    private AnchorPane tk;

    @FXML
    private TableColumn<ThongKe, String> LoaiSP;

    @FXML
    private TextField Month;

    @FXML
    private TableColumn<ThongKe, Double> SoLuong;

    @FXML
    private TableColumn<ThongKe, String> TenHang;

    @FXML
    private TableColumn<ThongKe, Double> Tien;

    @FXML
    private TextField Year;

    @FXML
    private TableView<ThongKe> bangThongKe;

    @FXML
    private ComboBox<ChiNhanh> ListChiNhanh;

    @FXML
    private PieChart c;

    @FXML
    private BarChart<String, Double> b;

    private void PieChart(List<ThongKe> a) throws SQLException {
        c.getData().clear();
        List<PieChart.Data> listChart = new ArrayList<PieChart.Data>();
        for (ThongKe i : a) {
            listChart.add(new PieChart.Data(i.getMaHang(), i.getSoLuong()));
        }
        ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList(listChart);
        c.getData().addAll(piechart);
    }

    private void BarChart(List<ThongKe> a) throws SQLException {
        b.getData().clear();
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (ThongKe i : a) {
            series.getData().add(new XYChart.Data(i.getMaHang(), i.getSoLuong()));
        }
        b.getData().addAll(series);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<ThongKe> thongke = ThongKeService.ListThongKe();
            this.TenHang.setCellValueFactory(new PropertyValueFactory<ThongKe, String>("MaHang"));
            this.SoLuong.setCellValueFactory(new PropertyValueFactory<ThongKe, Double>("SoLuong"));
            this.Tien.setCellValueFactory(new PropertyValueFactory<ThongKe, Double>("TongTien"));
            this.LoaiSP.setCellValueFactory(new PropertyValueFactory<ThongKe, String>("MaLoaiSP"));

            this.bangThongKe.setItems(FXCollections.observableArrayList(thongke));
            List<ChiNhanh> ChiNhanhList = ChiNhanhService.GetChiNhanh();
            this.ListChiNhanh.setItems(FXCollections.observableArrayList(ChiNhanhList));
            List<PieChart.Data> listChart = new ArrayList<PieChart.Data>();
            for (ThongKe i : thongke) {
                listChart.add(new PieChart.Data(i.getMaHang(), i.getSoLuong()));
            }
            ObservableList<PieChart.Data> piechart = FXCollections.observableArrayList(listChart);
            c.getData().addAll(piechart);
            this.BarChart(thongke);
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void SetUpTable(List<ThongKe> a) {
        this.TenHang.setCellValueFactory(new PropertyValueFactory<ThongKe, String>("MaHang"));
        this.SoLuong.setCellValueFactory(new PropertyValueFactory<ThongKe, Double>("SoLuong"));
        this.Tien.setCellValueFactory(new PropertyValueFactory<ThongKe, Double>("TongTien"));
        this.LoaiSP.setCellValueFactory(new PropertyValueFactory<ThongKe, String>("MaLoaiSP"));
        this.bangThongKe.setItems(FXCollections.observableArrayList(a));
    }

    @FXML
    public void ThongKeByChiNHanh() throws SQLException {
        ChiNhanh a = ListChiNhanh.getSelectionModel().getSelectedItem();
        List<ThongKe> ListThongKeChiNhanh = ThongKeService.ListThongKeByChiNhanh(a, this.Month.getText(), this.Year.getText());
        SetUpTable(ListThongKeChiNhanh);
        this.PieChart(ListThongKeChiNhanh);
        this.BarChart(ListThongKeChiNhanh);
    }

    @FXML
    public void ThongKeAll() throws SQLException {

        List<ThongKe> thongke = ThongKeService.ListThongKe();
        SetUpTable(thongke);
        ListChiNhanh.getSelectionModel().clearSelection();

        this.PieChart(thongke);
        this.BarChart(thongke);

    }
}
