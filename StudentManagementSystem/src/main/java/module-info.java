module com.example.studentmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires mysql.connector.j;



    opens com.example.studentmanagementsystem to javafx.fxml;
    exports com.example.studentmanagementsystem;
}