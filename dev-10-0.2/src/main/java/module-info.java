module cs151.application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens cs151.application to javafx.fxml, javafx.base;
    opens cs151.application.controller to javafx.fxml;

    exports cs151.application;
    exports cs151.application.controller;
}