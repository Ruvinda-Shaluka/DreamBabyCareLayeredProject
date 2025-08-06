package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.OrderCartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {

    String getNextOrderId() throws Exception;

    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;

    InventoryDto getItemsByIds(String itemId);

    ArrayList<String> getCustomerNamesByPartialPhone(String contact) throws Exception;

    void placeOrder(ObservableList<OrderCartTM> cartData, Label lblOrderId, Label orderPlacementDate, TextField txtCustomerContact, ComboBox cmbCustomerName, ComboBox cmbItemId, TextField txtShipmentTrackingNumber, ComboBox cmbPaymentMethod);

    void addToCart(ComboBox cmbItemId, TextField txtCartQty, Label txtAddToCartQty, Label lblItemName, Label lblItemPrice, ComboBox cmbCustomerName, ComboBox cmbPaymentMethod, ObservableList<OrderCartTM> cartData, TableView<OrderCartTM> tblOrderPlacement);
}
