package com.mycompany.appbh;

import com.mycompany.conf.JdbcUtils;
import com.mycompany.pojo.UserSession;
import com.mycompany.service.NhanVienService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    
   
    @FXML private TextField txtA;
    @FXML private TextField txtB;
    @FXML private Label erro;
     

    
    
    
    @FXML
     private void switchToIndex() throws IOException, SQLException {
         String id = txtA.getText();
         String pw = txtB.getText();
         if(NhanVienService.Login(id, pw) == true){
             UserSession.getInstace(id);
             App.setRoot("index");
         }
         else
             erro.setText("Sai tên đăng nhập hoặc mật khẩu");
        
    }
}
