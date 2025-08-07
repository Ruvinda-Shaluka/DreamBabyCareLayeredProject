package lk.ijse.dreambabycareprojectinlayered.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dreambabycareprojectinlayered.bo.BOFactory;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.PlaceOrderBO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.OrderCartTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderPlacementController implements Initializable {
    public AnchorPane ancOrderPlacementPage;
    public Label homeLabel;
    public Label lblOrderId;
    public Label orderPlacementDate;
    public TextField txtCustomerContact;
    public ComboBox cmbCustomerName;
    public Label labelPopUpCustomer;
    public ComboBox cmbItemId;
    public Label lblItemName;
    public Label lblItemPrice;
    public Label txtAddToCartQty;
    public TextField txtCartQty;
    public ComboBox cmbPaymentMethod;
    public TextField txtShipmentTrackingNumber;
    public TableView<OrderCartTM> tblOrderPlacement;
    public TableColumn<OrderCartTM,String> colCustomerId;
    public TableColumn<OrderCartTM,String> colItemId;
    public TableColumn<OrderCartTM,String> colItemName;
    public TableColumn<OrderCartTM,String> colQuantity;
    public TableColumn<OrderCartTM,String> qtyPrice;
    public TableColumn<OrderCartTM,String> colTotal;
    public TableColumn<OrderCartTM,String> colPaymentMethod;
    public TableColumn<?,?> colAction;

    private final ObservableList<OrderCartTM> cartData = FXCollections.observableArrayList();

    private final PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACE_ORDERS);

    public void goToDashBoardPage(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/DashBoardView.fxml");
    }

    private void setCellValues() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        qtyPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colPaymentMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        tblOrderPlacement.setItems(cartData);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        placeOrderBO.addToCart(
                cmbItemId,
                txtCartQty,
                txtAddToCartQty,
                lblItemName,
                lblItemPrice,
                cmbCustomerName,
                cmbPaymentMethod,
                cartData,
                tblOrderPlacement
        );
        resetWhenAddToCart();
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) {
        placeOrderBO.placeOrder(
                cartData,
                lblOrderId,
                orderPlacementDate,
                txtCustomerContact,
                cmbCustomerName,
                cmbItemId,
                txtShipmentTrackingNumber,
                cmbPaymentMethod
        );

        resetPage();
    }

    public void goToCustomerPopUp(MouseEvent mouseEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/ijse/dreambabycareprojectinlayered/assets/view/CustomerPagePopUp.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            stage.setTitle("Add New Customer");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer pop-up..!").show();
        }

    }

    public void searchCustomerContact(KeyEvent keyEvent) {
        String contact = txtCustomerContact.getText();
        try {
            if (contact.isEmpty()) {
                cmbCustomerName.setItems(FXCollections.observableArrayList());
                cmbCustomerName.getSelectionModel().clearSelection();
                return;
            }
            ArrayList<String> matchingNames = placeOrderBO.getCustomerNamesByPartialPhone(contact);
            if (!matchingNames.isEmpty()) {
                cmbCustomerName.setItems(FXCollections.observableArrayList(matchingNames));
                cmbCustomerName.getSelectionModel().selectFirst();
            } else {
                cmbCustomerName.setItems(FXCollections.observableArrayList("No customer found with this contact"));
                cmbCustomerName.getSelectionModel().selectFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to search customer..!").show();
        }
    }

    private void navigateTo(String path) {
        try {
            ancOrderPlacementPage.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancOrderPlacementPage.widthProperty());
            anchorPane.prefHeightProperty().bind(ancOrderPlacementPage.heightProperty());

            ancOrderPlacementPage.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValues();
        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
        cmbPaymentMethod.setItems(FXCollections.observableArrayList("Online", "Cash on Delivery", "Card Payment"));


        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadItemDetails((String) newValue);
            }
        });


    }

    private void loadItemDetails(String itemId) {
        try {
            InventoryDto item = placeOrderBO.getItemsByIds(itemId);
            if (item != null) {
                lblItemName.setText(item.getItem_name());
                txtAddToCartQty.setText(String.valueOf(item.getQuantity_available()));
                lblItemPrice.setText(String.valueOf(item.getUnit_price()));
            } else {
                lblItemName.setText("");
                txtCartQty.setText("");
                lblItemPrice.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load item details..!").show();
        }
    }

    private void refreshPage() throws Exception {
        lblOrderId.setText(placeOrderBO.getNextOrderId());
        orderPlacementDate.setText(LocalDate.now().toString());

        //loadCustomerIds();
        loadItemIds();
    }

    private void loadItemIds() {
        try {
            ArrayList<String> itemIdsList = itemIdsList = placeOrderBO.getAllItemIds();
            ObservableList<String> itemIds = FXCollections.observableArrayList();
            itemIds.addAll(itemIdsList);
            cmbItemId.setItems(itemIds);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }

    }

/*    private void loadCustomerIds() {
        try {
            ArrayList<String> customerIdsList = customerModel.getAllCustomerIds();
            ObservableList<String> customerIds = FXCollections.observableArrayList();
            customerIds.addAll(customerIdsList);
            cmbItemId.setItems(customerIds);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load data..!").show();
        }
    }*/

    private void resetPage() {
        //lblOrderId.setText("");

        loadNextId();
        //loadCustomerIds();
        loadItemIds();

        //orderPlacementDate.setText("");
        txtCustomerContact.setText("");
        cmbCustomerName.getSelectionModel().clearSelection();
        cmbItemId.getSelectionModel().clearSelection();
        lblItemName.setText("");
        txtCartQty.setText("");
        txtAddToCartQty.setText("");
        lblItemPrice.setText("");
        cartData.clear();
        tblOrderPlacement.refresh();
    }

    private void loadNextId() {
        try {
            String nextId = placeOrderBO.getNextOrderId();
            lblOrderId.setText(nextId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load next order ID..!").show();
        }
    }

    private void resetWhenAddToCart() {
        cmbItemId.getSelectionModel().clearSelection();
        txtCartQty.clear();
        lblItemName.setText("");
        txtAddToCartQty.setText("");
        lblItemPrice.setText("");
        cmbPaymentMethod.getSelectionModel().clearSelection();
    }
}
