package lk.ijse.dreambabycareprojectinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreambabycareprojectinlayered.bo.BOFactory;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrdersBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrdersDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.OrdersTM;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrdersController implements Initializable{
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
    public TableView<OrdersTM> tblOrders;
    public TableColumn<OrdersTM,String> colOrderId;
    public TableColumn<OrdersTM,String> colDate;
    public TableColumn<OrdersTM,String> colCustomerId;
    public TableColumn<OrdersTM,String> colShipmentId;
    public TableColumn<OrdersTM,String> colStatus;

    private final String datePattern = "^\\d{4}/\\d{2}/\\d{2}$";

    private final OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String orderId = ordersIdLabel.getText();
        //String orderDate = txtOrderDate.getText();
        LocalDate orderDate = ordersDatePicker.getValue();
        String customerId = (String) cmbCustomerId.getSelectionModel().getSelectedItem();
        String shipmentId = (String) cmbShipmentId.getSelectionModel().getSelectedItem();
        String status = (String) cmbStatus.getSelectionModel().getSelectedItem();

        if (orderId.isEmpty() || orderDate == null || customerId.isEmpty() || shipmentId.isEmpty() || status == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return;
        }

        try {
            boolean isUpdated = ordersBO.update(new OrdersDto(
                    orderId,
                    orderDate,
                    customerId,
                    shipmentId,
                    status
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Order Updated Successfully").show();
                loadOrdersTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Order").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update Order").show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this order?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Confirmation");

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String orderId = ordersIdLabel.getText();
            try {
                boolean isDeleted = ordersBO.delete(orderId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Order Deleted Successfully").show();
                    loadOrdersTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Order").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete Order").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String orderId = ordersIdLabel.getText();
        //String orderDate = txtOrderDate.getText();
        LocalDate orderDate = ordersDatePicker.getValue();
        String customerId = (String) cmbCustomerId.getSelectionModel().getSelectedItem();
        String shipmentId = (String) cmbShipmentId.getSelectionModel().getSelectedItem();
        String status = (String) cmbStatus.getSelectionModel().getSelectedItem();

        boolean isValidDate = orderDate != null && orderDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")).matches(datePattern);

        ordersDatePicker.setStyle(";-fx-border-color: #7367F0");

        if (orderId.isEmpty() || orderDate == null || customerId.isEmpty() || shipmentId.isEmpty() || status == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return;
        }
        if (!isValidDate) {
            ordersDatePicker.setStyle(";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid date format. Use YYYY/MM/DD").show();
            return;
        }

        if (isValidDate){
            try {
                boolean isSaved = ordersBO.save(new OrdersDto(
                        orderId,
                        orderDate,
                        customerId,
                        shipmentId,
                        status
                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Order Saved Successfully").show();
                    loadOrdersTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Order").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to save Order").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void onClickTable(MouseEvent mouseEvent) {
        OrdersTM selectedItem = tblOrders.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            ordersIdLabel.setText(selectedItem.getOrder_id());
            ordersDatePicker.setValue(selectedItem.getOrder_date());
            cmbCustomerId.setValue(selectedItem.getCustomer_id());
            cmbShipmentId.setValue(selectedItem.getShipment_id());
            cmbStatus.setValue(selectedItem.getStatus());

            //save button(id) -> disable
            btnSave.setDisable(true);
            //update button(id) -> enable
            btnUpdate.setDisable(false);
            //delete button(id) -> enable
            btnDelete.setDisable(false);
        }
    }

    private void navigateTo(String path) {
        try {
            ancOrdersViewContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancOrdersViewContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancOrdersViewContainer.heightProperty());

            ancOrdersViewContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    private void resetPage() {
        try {
            loadOrdersTableData();
            loadNextId();

            //save button(id) -> enable
            btnSave.setDisable(false);
            //update button(id) -> disable
            btnUpdate.setDisable(true);
            //delete button(id) -> disable
            btnDelete.setDisable(true);

            //txtOrderDate.setText("");
            //cmbCustomerName.setText("");
            //lbTrackingNumber.setText("");
            ordersDatePicker.setValue(null);
            cmbCustomerId.getSelectionModel().clearSelection();
            cmbShipmentId.getSelectionModel().clearSelection();
            cmbStatus.getSelectionModel().clearSelection();


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Reset").show();
        }

    }

    private void loadOrdersTableData() throws Exception {
        tblOrders.setItems(FXCollections.observableArrayList(
                ordersBO.loadAll()
                        .stream()
                        .map(ordersDto -> new OrdersTM(
                                ordersDto.getOrder_id(),
                                ordersDto.getOrder_date(),
                                ordersDto.getCustomer_id(),
                                ordersDto.getShipment_id(),
                                ordersDto.getStatus()
                        ))
                        .toList()
        ));

    }

    private void loadNextId() {
        try {
            String nextId = ordersBO.generateNewId();
            ordersIdLabel.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load next Employee ID").show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        colShipmentId.setCellValueFactory(new PropertyValueFactory<>("shipment_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        cmbStatus.setItems(FXCollections.observableArrayList("Pending", "Shipped", "Delivered", "Cancelled"));
        try {
            cmbCustomerId.setItems(FXCollections.observableArrayList(ordersBO.getAllCustomerIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers").show();
        }

        try {
            cmbShipmentId.setItems(FXCollections.observableArrayList(ordersBO.getAllShipmentIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load shipments").show();
        }

        try {
            loadOrdersTableData();
            loadNextId();
            loadCustomerIds();
            loadShipmentIds();
            setOrdersDateTimePicker();
            /*cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    displayCustomerName((String) newValue);
                }
            });
            cmbShipmentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    displayTrackingNumber((String) newValue);
                }
            });*/

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers").show();
        }

    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        cmbCustomerId.setItems(FXCollections.observableArrayList(ordersBO.getAllCustomerIds()));
    }

    private void loadShipmentIds() throws SQLException, ClassNotFoundException {
        cmbShipmentId.setItems(FXCollections.observableArrayList(ordersBO.getAllShipmentIds()));
    }

    /*private void displayCustomerName(String customerId) {
        try {
            String customerName = ordersModel.getCustomerNameById(customerId);
            cmbCustomerName.setText(customerName);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customer name").show();
        }
    }

    private void displayTrackingNumber(String shipmentId) {
        try {
            String trackingNumber = ordersModel.getTrackingNumberById(shipmentId);
            lbTrackingNumber.setText(trackingNumber);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load tracking number").show();
        }
    }*/

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            try {
                loadOrdersTableData();
            }catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load orders").show();
            }
        }else {
            try {
                ArrayList<OrdersDto> ordersList = ordersBO.search(searchText);
                tblOrders.setItems(FXCollections.observableArrayList(
                        ordersList.stream()
                                .map(ordersDto -> new OrdersTM(
                                        ordersDto.getOrder_id(),
                                        ordersDto.getOrder_date(),
                                        ordersDto.getCustomer_id(),
                                        ordersDto.getShipment_id(),
                                        ordersDto.getStatus()
                                ))
                                .toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load orders").show();
            }
        }
    }

    public void goToAddCustomersLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/CustomerView.fxml");
    }

    public void goToAddShipmentLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/ShipmentView.fxml");
    }

    private void setOrdersDateTimePicker() {
        // Set prompt text to today's date
        ordersDatePicker.setPromptText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        // Set up row click to load date into DatePicker
        tblOrders.setRowFactory(tv -> {
            TableRow<OrdersTM> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    OrdersTM rowData = row.getItem();
                    // Assuming OrdersTM has a getDate() method returning String in yyyy-MM-dd or similar format
                    try {
                        LocalDate date = rowData.getOrder_date();
                        ordersDatePicker.setValue(date);
                    } catch (Exception e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to set order date").show();
                        ordersDatePicker.setValue(null);
                    }
                }
            });
            return row;
        });
    }
}
