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
import lk.ijse.dreambabycareprojectinlayered.bo.custom.OrderItemBO;
import lk.ijse.dreambabycareprojectinlayered.dto.OrderItemDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.OrderItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderItemController implements Initializable {
    public AnchorPane ancOrdersItemViewContainer;
    public TextField searchField;
    public Label ordersItemIdLabel;
    public ComboBox cmbOrderId1;
    public ComboBox cmbInventoryId1;
    public TextField txtQuantity;
    public TextField txtAmount;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<OrderItemTM> tblOrderItems;
    public TableColumn<OrderItemTM,String> colOrderItemId;
    public TableColumn<OrderItemTM,String> colOrderId;
    public TableColumn<OrderItemTM,String> colInventoryId;
    public TableColumn<OrderItemTM,String> colQuantity;
    public TableColumn<OrderItemTM,String> colAmount;

    private final String quantityRegex = "^\\d+$";
    private final String amountRegex = "^\\d+(\\.\\d{2})?$";

    private final OrderItemBO orderItemBO = (OrderItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_ITEM);


    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String orderItemId = ordersItemIdLabel.getText();
        String orderId = (String) cmbOrderId1.getSelectionModel().getSelectedItem();
        String inventoryId = (String) cmbInventoryId1.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double amount = Double.parseDouble(txtAmount.getText());

        if(orderId.isEmpty() || inventoryId.isEmpty() || txtQuantity.getText().isEmpty() || txtAmount.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields..!").show();
            return;
        }

        try {
            boolean isUpdated = orderItemBO.update(new OrderItemDto(
                    orderItemId,
                    orderId,
                    inventoryId,
                    quantity,
                    amount
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Order Item Updated Successfully..!").show();
                loadOrderItemTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Order Item..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update Order Item..!").show();
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Order Item?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Confirmation");

        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent() && response.get() == ButtonType.YES) {
            String orderItemId = ordersItemIdLabel.getText();
            try {
                boolean isDeleted = orderItemBO.delete(orderItemId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Order Item Deleted Successfully..!").show();
                    loadOrderItemTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Order Item..!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete Order Item..!").show();
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        String orderItemId = ordersItemIdLabel.getText();
        String orderId = (String) cmbOrderId1.getSelectionModel().getSelectedItem();
        String inventoryId = (String) cmbInventoryId1.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double amount = Double.parseDouble(txtAmount.getText());

        if (orderId.isEmpty() || inventoryId.isEmpty() || txtQuantity.getText().isEmpty() || txtAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields..!").show();
            return;
        }
        txtQuantity.setStyle(txtQuantity.getStyle() + ";-fx-border-color: #7367F0");
        txtAmount.setStyle(txtAmount.getStyle() + ";-fx-border-color: #7367F0");

        boolean isValidQuantity = txtQuantity.getText().matches(quantityRegex);
        boolean isValidAmount = txtAmount.getText().matches(amountRegex);

        if (!isValidQuantity) {
            txtQuantity.setStyle(txtQuantity.getStyle() + ";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity format..!").show();
            return;
        }
        if (!isValidAmount) {
            txtAmount.setStyle(txtAmount.getStyle() + ";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid Amount format..!").show();
            return;
        }


        if (isValidAmount && isValidQuantity) {
            try {
                boolean isSaved = orderItemBO.save(new OrderItemDto(
                        orderItemId,
                        orderId,
                        inventoryId,
                        quantity,
                        amount
                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Order Item Saved Successfully..!").show();
                    loadOrderItemTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Order Item..!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to save Order Item..!").show();
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        OrderItemTM selectedOrderItem = (OrderItemTM) tblOrderItems.getSelectionModel().getSelectedItem();
        if (selectedOrderItem != null) {
            ordersItemIdLabel.setText(selectedOrderItem.getOrder_item_id());
            cmbOrderId1.getSelectionModel().select(selectedOrderItem.getOrder_id());
            cmbInventoryId1.getSelectionModel().select(selectedOrderItem.getInventory_id());
            txtQuantity.setText(String.valueOf(selectedOrderItem.getQuantity()));
            txtAmount.setText(String.valueOf(selectedOrderItem.getAmount()));

            //save button(id) -> disable
            btnSave.setDisable(true);
            //update button(id) -> enable
            btnUpdate.setDisable(false);
            //delete button(id) -> enable
            btnDelete.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colOrderItemId.setCellValueFactory(new PropertyValueFactory<>("order_item_id"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        try {
            cmbOrderId1.setItems(FXCollections.observableArrayList(orderItemBO.getAllOrderIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Order IDs..!").show();
        }

        try {
            cmbInventoryId1.setItems(FXCollections.observableArrayList(orderItemBO.getAllInventoryIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Inventory IDs..!").show();
        }

        try {
            loadOrderItemTableData();
            loadNextId();
            loadOrdersIds();
            loadInventoryIds();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Order Item IDs..!").show();
        }

    }

    private void loadNextId() {
        try {
            String nextOrderItemId = orderItemBO.generateNewId();
            ordersItemIdLabel.setText(nextOrderItemId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Order Item IDs..!").show();
        }
    }

    private void loadOrderItemTableData() throws Exception {
        tblOrderItems.setItems(FXCollections.observableArrayList(
                orderItemBO.loadAll()
                        .stream()
                        .map(orderItemDto -> new OrderItemTM(
                                orderItemDto.getOrder_item_id(),
                                orderItemDto.getOrder_id(),
                                orderItemDto.getInventory_id(),
                                orderItemDto.getQuantity(),
                                orderItemDto.getAmount()
                        ))
                        .toList()
        ));

    }

    private void navigateTo(String path) {
        try {
            ancOrdersItemViewContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancOrdersItemViewContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancOrdersItemViewContainer.heightProperty());

            ancOrdersItemViewContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    private void loadOrdersIds() throws SQLException, ClassNotFoundException {
        cmbOrderId1.setItems(FXCollections.observableArrayList(orderItemBO.getAllOrderIds()));
    }

    private void loadInventoryIds() throws SQLException, ClassNotFoundException {
        cmbInventoryId1.setItems(FXCollections.observableArrayList(orderItemBO.getAllInventoryIds()));
    }

    /*private void displayItemName(String inventoryId) throws SQLException, ClassNotFoundException {
        try {
            String item = orderItemModel.getInventoryItemNameById(inventoryId);
            lblInventoryId1.setText(item);
        }catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Item Names..!").show();
        }
    }*/

    private void resetPage(){
        try {
            loadOrderItemTableData();
            loadNextId();

            //save button(id) -> enable
            btnSave.setDisable(false);
            //update button(id) -> disable
            btnUpdate.setDisable(true);
            //delete button(id) -> disable
            btnDelete.setDisable(true);

            cmbOrderId1.getSelectionModel().clearSelection();
            cmbInventoryId1.getSelectionModel().clearSelection();
            txtQuantity.setText("");
            //lblInventoryId1.setText("");
            //lblOrderId1.setText("");
            txtAmount.setText("");



        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load order item table").show();

        }
    }

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            try{
                loadOrderItemTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load order items").show();
            }
        }else {
            try {
                ArrayList<OrderItemDto> orderItemList = orderItemBO.search(searchText);
                tblOrderItems.setItems(FXCollections.observableArrayList(
                        orderItemList.stream()
                                .map(orderItemDto -> new OrderItemTM(
                                        orderItemDto.getOrder_item_id(),
                                        orderItemDto.getOrder_id(),
                                        orderItemDto.getInventory_id(),
                                        orderItemDto.getQuantity(),
                                        orderItemDto.getAmount()
                                ))
                                .toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load order items").show();
            }
        }
    }

    public void goToAddOrderLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OrdersView.fxml");
    }

    public void goToAddItemsLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/InventoryView.fxml");
    }
}
