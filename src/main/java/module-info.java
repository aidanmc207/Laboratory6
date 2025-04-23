module com.example.laboratory6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.laboratory6 to javafx.fxml;
    exports com.example.laboratory6;
}