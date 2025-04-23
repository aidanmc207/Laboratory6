module ucr.laboratory6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens ucr.laboratory6 to javafx.fxml;
    exports ucr.laboratory6;
    exports controller;
    opens controller to javafx.fxml;
}