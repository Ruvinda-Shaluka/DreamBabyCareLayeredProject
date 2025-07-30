module lk.ijse.dreambabycareprojectinlayered {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires java.desktop;
    requires java.mail;
    requires activation;
    requires net.sf.jasperreports.core;
    requires net.sf.jasperreports.pdf;

    opens lk.ijse.dreambabycareprojectinlayered.view.tdm to javafx.base;
    opens lk.ijse.dreambabycareprojectinlayered to javafx.graphics;
    opens lk.ijse.dreambabycareprojectinlayered.controller to javafx.fxml;

    exports lk.ijse.dreambabycareprojectinlayered;
    exports lk.ijse.dreambabycareprojectinlayered.controller;
}