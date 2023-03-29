module com.mycompany.appbh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.appbh to javafx.fxml;
    exports com.mycompany.appbh;

//    requires javafx.controls;
//    requires javafx.fxml;
//    requires java.base;
//    requires java.sql;
//    
//    opens com.mycompany to javafx.fxml;
//    opens com.mycompany.appbh to javafx.fxml,javafx.base;
//    opens com.mycompany.pojo to javafx.base;
//    exports com.mycompany.appbh;
}
