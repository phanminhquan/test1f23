package com.mycompany.appbh;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.ChiNhanh;
import com.mycompany.pojo.Hang;
import com.mycompany.pojo.KhachHang;
import com.mycompany.pojo.NhanVien;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.ChiNhanhService;
import com.mycompany.service.DonViTinhService;
import com.mycompany.service.HoaDonService;
import com.mycompany.service.NhanVienService;
import com.mycompany.service.SanPhamService;
import com.mycompany.utils.MessageBox;
import com.mycompany.appbh.App;
import com.mycompany.appbh.PrimaryController;
import com.mycompany.pojo.Hang;
import com.mycompany.pojo.HoaDonBan;
import com.mycompany.service.DonViTinhService;
import com.mycompany.service.HoaDonService;
import com.mycompany.utils.MessageBox;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

;

public class IndexController implements Initializable {

    Hang selectHang;
    @FXML
    private TableView<Hang> content;

    @FXML
    private TextField KhachHang;

    @FXML
    private TextField MaHoaDon;

    @FXML
    private TextField TenNV;

    @FXML
    private TextField ChiNhanh;

    @FXML
    private DatePicker NgayBan;

    @FXML
    private TextField GiamGia;

    @FXML
    private TextField TongTienThanhToan;
    @FXML
    private TextField ThanhToan;
    
    @FXML
    private Button thanhtoan;

    @FXML
    private TextField TienMat;

    @FXML
    private TextField thoi;
    @FXML
    private ComboBox<Hang> l;

    @FXML
    private Label DV;

    @FXML
    private TableColumn<Hang, String> IDSP;

    @FXML
    private TableColumn<Hang, String> tenSP;

    @FXML
    private TableColumn<Hang, Double> soLuong;

    @FXML
    private TableColumn<Hang, Double> DonGia;

    @FXML
    private TableColumn<Hang, Integer> GiamGiaSP;

    @FXML
    private TableColumn<Hang, Double> TongTien;

    @FXML
    private TextField InfoID;
    @FXML
    private TextField InfoName;
    @FXML
    private TextField InfoSoLuong;
    @FXML
    private TextField InfoDonGia;
    @FXML
    private TextField InfoGiamGia;
    @FXML
    private TextField InfoTongTien;

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

    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    private void switchToSanPham() throws IOException, SQLException {
        App.setRoot("SanPham");
    }
    @FXML
    private void switchToSearchHoaDon() throws IOException, SQLException {
        App.setRoot("HoaDon");
    }
    
    @FXML
    private void switchToThongKe() throws IOException, SQLException {
        App.setRoot("ThongKe");
    }
    
    @FXML
    private void LogOut() throws IOException, SQLException {
        UserSession.cleanUserSession();
        App.setRoot("primary");
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
    KhachHang select;
    NhanVien a;
    List<Hang> items;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Date date = new Date();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(date);
        this.NgayBan.setValue(LOCAL_DATE(s));

        int maHoaDon = 0;

        try ( Connection conn = JdbcUtils.getConn()) {

            Statement stm1 = conn.createStatement();

            ResultSet rs = stm1.executeQuery("SELECT * FROM tblhdban");
            while (rs.next()) {
                maHoaDon = Integer.parseInt(rs.getString("MaHDBan"));
            }

            this.MaHoaDon.setText(Integer.toString(maHoaDon) + 1);

            a = NhanVienService.GetNhanVienByID(UserSession.getUserID().toString()).get(0);
            this.TenNV.setText(a.getTenNV());

            ChiNhanh b = ChiNhanhService.GetChiNhanhByID(Integer.toString(a.getIDChiNhanh()));

            this.ChiNhanh.setText(b.getDiaChi());
            items = SanPhamService.GetSanPham();

            this.l.setItems(FXCollections.observableArrayList(items));
            this.content.setEditable(true);

        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update() throws SQLException {
        double tong = 0, giamgia = 0, tongTienBanDau = 0;
        for (Hang i : itemInContent) {
            tongTienBanDau += i.getDonGiaBan() * i.getSoLuongBan();
            giamgia += i.getDonGiaBan()*i.getGiaGiam()*i.getSoLuongBan()/100;
        }
        double after = tongTienBanDau - giamgia;
        if(select!=null){
            Date today = new Date();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String stoday = formatter.format(today);
            String birthS = formatter.format(select.getNgaySinh());

            String ToDay = stoday.split("-")[2];
            String ToMonth = stoday.split("-")[1];

            String ToDayB = birthS.split("-")[2];
            String ToMonthB = birthS.split("-")[1];
            if (select != null && ToDay.equals(ToDayB) && ToMonth.equals(ToMonthB) && after >= 1000000) {
                double t = after*0.1;
                giamgia += t;

                after -= t;
            }
            if (!this.KhachHang.getText().equals("")) {
            double t = after * 0.05;
            giamgia += t;
            after -= t;
        }
        }

        if(tongTienBanDau - giamgia > 0)
            tong = tongTienBanDau-giamgia;
        else
            tong =0;
        this.GiamGia.setText(Double.toString(giamgia));
        this.TongTienThanhToan.setText(Double.toString(tongTienBanDau));
        this.ThanhToan.setText(Double.toString(tong));
        this.thoi.setText("");
        this.TienMat.setText("");
    }
    
    @FXML
    public void deleteKhach() throws SQLException{
        this.KhachHang.setText("");
        select=null;
        update();
    }
    
    @FXML
    public void load(MouseEvent event) throws IOException, SQLException {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(getClass().getResource("KhachHang_1.fxml"));

        DialogPane KH = fl.load();
        KhachHangController c = fl.getController();
        Dialog<ButtonType> a = new Dialog<>();
        a.setDialogPane(KH);
        Optional<ButtonType> btn = a.showAndWait();
        if (btn.get() == ButtonType.APPLY) {
            if (c.getListKhachHang().getSelectionModel().getSelectedItem() != null) {
                select = c.getListKhachHang().getSelectionModel().getSelectedItem();
                this.KhachHang.setText(select.getTenKhach());
                update();
            }

        }

    }

    @FXML
    public void PanelSanPham(MouseEvent event) throws IOException {
        FXMLLoader fl = new FXMLLoader();
        fl.setLocation(getClass().getResource("SanPham_1.fxml"));

        DialogPane SP = fl.load();
        SanPhamController c = fl.getController();
        Dialog<ButtonType> a = new Dialog<>();
        a.setDialogPane(SP);
        Optional<ButtonType> btn = a.showAndWait();
        if (btn.get() == ButtonType.APPLY) {
            if (c.listSanPham.getSelectionModel().getSelectedItem() != null) {
                for (Hang i : items) {
                    if (i.getMaHang().equals(c.listSanPham.getSelectionModel().getSelectedItem().getMaHang())) {
                        l.setValue(i);
                        break;
                    }
                }
            } else {
                MessageBox.getBox("Cảnh báo", "Bạn chưa chọn sản phẩm!!",
                        Alert.AlertType.ERROR).show();
            }
            ;

        }

    }

    List<Hang> itemInContent = new ArrayList<Hang>();

    private void addListToTableView() {
        this.IDSP.setCellValueFactory(new PropertyValueFactory<Hang, String>("MaHang"));
        this.tenSP.setCellValueFactory(new PropertyValueFactory<Hang, String>("TenHang"));
        this.soLuong.setCellValueFactory(new PropertyValueFactory<Hang, Double>("soLuongBan"));
        this.DonGia.setCellValueFactory(new PropertyValueFactory<Hang, Double>("DonGiaBan"));
        this.GiamGiaSP.setCellValueFactory(new PropertyValueFactory<Hang, Integer>("GiaGiam"));
        this.TongTien.setCellValueFactory(new PropertyValueFactory<Hang, Double>("TongGiaTien"));
        this.content.setItems(FXCollections.observableArrayList(itemInContent));
    }

    @FXML
    public void AddITem() throws SQLException, Exception {
        
        this.thanhtoan.setDisable(false);
        Hang a = this.l.getValue();
        boolean isFound = false;
        for (Hang i : itemInContent) {
            if (i.getMaHang().equals(a.getMaHang())) {
                isFound = true;
                break;
            }
        }

        try {

            if (!isFound) {
                if (DonViTinhService.getDonVITinhByID(Integer.toString(a.getDonViTinh())).getValue().equals("Kg")) {
                    a.setSoLuongBan(0);
                } else {
                    a.setSoLuongBan(1);
                }
                if(SanPhamService.GetSanPhamByID(Integer.toString(a.getDonViTinh())).get(0).getSoLuong() ==0)
                    throw new Exception("Hết hàng");
                itemInContent.add(a);
                this.addListToTableView();

            } else {
                for (Hang i : itemInContent) {
                    if (i.getMaHang().equals(a.getMaHang())) {
                        if (!DonViTinhService.getDonVITinhByID(Integer.toString(a.getDonViTinh())).getValue().equals("Kg")) {
                            Hang checkSL = SanPhamService.GetSanPhamByID(i.getMaHang()).get(0);
                            if(checkSL.getSoLuong() <= i.getSoLuongBan())
                                throw new Exception("Hàng trong kho không đủ");
                            i.setSoLuongBan(i.getSoLuongBan() + 1);
                        }
                        else throw new Exception("Hàng đã có, vui lòng nhập số KG");
                        break;
                    }
                }
            }

        } catch (NullPointerException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Cảnh báo", "bạn chưa chọn sản phẩm thêm vào",
                    Alert.AlertType.ERROR).show();

        }
        catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Cảnh báo", ex.getMessage(),
                    Alert.AlertType.ERROR).show();

        }
        this.l.setValue(null);
        update();
        this.content.refresh();
    }

    @FXML
    public void DeleteItem() throws SQLException {
        try{
            
            selectHang = this.content.getSelectionModel().getSelectedItem();
            if(this.selectHang == null)
                throw new Exception();
            for (Hang i : itemInContent) {
                if (i.equals(selectHang)) {
                    itemInContent.remove(i);
                    break;
                }
            }
            this.TienMat.setText("0");
            this.thoi.setText("0");
            this.addListToTableView();
            update();
            this.content.refresh();
        }
        catch(Exception ex)
        {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Cảnh báo", "bạn chưa chọn sản phẩm để xóa",
                    Alert.AlertType.ERROR).show();
        }
        
    }

    @FXML
    public void g(MouseEvent event) throws SQLException {
        selectHang = this.content.getSelectionModel().getSelectedItem();
        this.InfoID.setText(selectHang.getMaHang());
        this.InfoName.setText(selectHang.getTenHang());
        this.InfoSoLuong.setText(Integer.toString(selectHang.getSoLuongBan()));
        this.InfoDonGia.setText(Double.toString(selectHang.getDonGiaBan()));
        this.InfoGiamGia.setText(Integer.toString(selectHang.getGiaGiam()));
        this.InfoTongTien.setText(Double.toString(selectHang.getTongGiaTien()));
        this.DV.setText(DonViTinhService.getDonVITinhByID(Integer.toString(selectHang.getDonViTinh())).getValue());

    }

    @FXML
    void checkInput(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
            event.consume();
            InfoSoLuong.setStyle("-fx-border-color: red");
        } else {
            InfoSoLuong.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    public void updateSoLuong() throws SQLException, Exception {
        try {
            int SoLuong = Integer.parseInt(this.InfoSoLuong.getText());
            if(SoLuong <= 0){
                MessageBox.getBox("Cảnh báo", "Nhập số lượng không hợp lệ",
                    Alert.AlertType.ERROR).show();
            }
            else{
                for (Hang i : itemInContent) {
                    if (i.equals(selectHang)) {
                        Hang checkSL = SanPhamService.GetSanPhamByID(i.getMaHang()).get(0);
                        if (checkSL.getSoLuong() >= SoLuong) {
                            i.setSoLuongBan(SoLuong);
                        } else {
                            throw new Exception("Hàng còn lại trong kho không đủ. Còn lại: " + checkSL.getSoLuong());
                        }
                        break;
                    }
                }
                update();
                this.content.refresh();
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Sai định dạng", "Số lượng bãn nhập không đúng định dạng!!",
                    Alert.AlertType.ERROR).show();
        } catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Cảnh báo", ex.getMessage(),
                    Alert.AlertType.ERROR).show();
        }
    }

    public void Pay() {
        try {
            double tienThanhToan = Double.parseDouble(this.ThanhToan.getText());
            double TienTra = Double.parseDouble(this.TienMat.getText());
            if(Double.parseDouble(this.TongTienThanhToan.getText()) ==0){
                MessageBox.getBox("Cảnh báo", "Tiền cần thanh toán không thể bằng 0",
                        Alert.AlertType.ERROR).show();
            }
            if (TienTra >= tienThanhToan) {
                double tienthoi = TienTra - tienThanhToan;
                this.thoi.setText(Double.toString(tienthoi));
            }
            
                
            else {
                MessageBox.getBox("Cảnh báo", "Tiền mặt không đủ",
                        Alert.AlertType.ERROR).show();
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Sai định dạng", "Tiền mặt nhập không đúng định dạng!!",
                    Alert.AlertType.ERROR).show();
        }
    }

    public void LuuHD() throws SQLException, IOException, InterruptedException, Exception {
        LocalDate dateIns = this.NgayBan.getValue();
        Instant instant = Instant.from(dateIns.atStartOfDay(ZoneId.systemDefault()));
        Date NgayBanDate = Date.from(instant);

        try {
            if (itemInContent.size() == 0) {
                throw new Exception("Chưa có mặc hàng nào để thanh toán");
            }           
            if(Double.parseDouble(this.TienMat.getText()) == 0 || Double.parseDouble(this.thoi.getText()) == 0)
                throw new Exception("Chưa thanh toán");
                HoaDonBan currentHD = new HoaDonBan(); 
            if(select!=null){
                currentHD = HoaDonService.addHoaDon(a.getMaNV(), NgayBanDate, select.getMaKhach(), Double.parseDouble(ThanhToan.getText()), a.getIDChiNhanh());
            }
            else{
                currentHD = HoaDonService.addHoaDon(a.getMaNV(), NgayBanDate, null, Double.parseDouble(ThanhToan.getText()), a.getIDChiNhanh());

            }
            for (Hang i : itemInContent) {
                HoaDonService.addHoaDonBan(currentHD, i.getMaHang(), i.getSoLuongBan(), i.getDonGiaBan(), Double.parseDouble(Integer.toString(i.getGiaGiam())), i.getTongGiaTien());
                SanPhamService.updateSLHang(i.getMaHang(), i.getSoLuongBan());
            }
            MessageBox.getBox("Thanh toán", "Thanh toán thành công!!!",
                    Alert.AlertType.INFORMATION).show();
            App.setRoot("index");
            
        } catch (SQLException ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Thanh toán", "Thanh toán thất bại!!!",
                    Alert.AlertType.ERROR).show();
        } catch (Exception ex) {
            Logger.getLogger(PrimaryController.class.getName()).log(Level.SEVERE, null, ex);
            MessageBox.getBox("Thanh toán", ex.getMessage(),
                    Alert.AlertType.ERROR).show();
        }

    }

    @FXML
    public void ScanProduct() throws IOException, InterruptedException {
        try ( WatchService w = FileSystems.getDefault().newWatchService()) {
            Path path = Paths.get("E:\\barcodeBanHang");
            WatchKey wk;
            boolean start = true;
            do {
                wk = w.take();
                for (WatchEvent<?> event : wk.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    System.out.print(kind);
                    if (kind.equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                        start = false;
                    }
                }
            } while (start);
        }
    }
}
