module ucr.laboratory6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ucr.laboratory6 to javafx.fxml;
    exports ucr.laboratory6;
    exports domain;
    opens domain to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
}