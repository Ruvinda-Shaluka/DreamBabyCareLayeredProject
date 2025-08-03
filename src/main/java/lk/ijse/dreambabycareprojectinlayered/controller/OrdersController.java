package lk.ijse.dreambabycareprojectinlayered.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class OrdersController {
    public AnchorPane ancOrdersViewContainer;
    public TextField searchField;
    public Label ordersIdLabel;
    public DatePicker ordersDatePicker;
    public ComboBox cmbCustomerId;
    public ComboBox cmbShipmentId;
    public ComboBox cmbStatus;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView tblOrders;
    public TableColumn colOrderId;
    public TableColumn colDate;
    public TableColumn colCustomerId;
    public TableColumn colShipmentId;
    public TableColumn colStatus;

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
    }

    public void search(KeyEvent keyEvent) {
    }

    public void goToAddCustomersLabel(MouseEvent mouseEvent) {
    }

    public void goToAddShipmentLabel(MouseEvent mouseEvent) {
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
    }

    public void onClickTable(MouseEvent mouseEvent) {
    }
}
