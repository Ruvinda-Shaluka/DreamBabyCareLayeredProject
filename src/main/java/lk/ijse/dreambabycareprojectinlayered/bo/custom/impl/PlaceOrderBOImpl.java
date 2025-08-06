package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import lk.ijse.dreambabycareprojectinlayered.bo.BOFactory;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.*;
import lk.ijse.dreambabycareprojectinlayered.bo.mailer.OrderReportMailer;
import lk.ijse.dreambabycareprojectinlayered.db.DBConnection;
import lk.ijse.dreambabycareprojectinlayered.dto.*;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.OrderCartTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    ShipmentBO shipmentBO = (ShipmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SHIPMENT);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    OrdersBO ordersBO = (OrdersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDERS);
    OrderItemBO orderItemBO = (OrderItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_ITEM);
    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.INVENTORY);


    @Override
    public String getNextOrderId() throws Exception {
        return ordersBO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return inventoryBO.getAllItemIds();
    }

    @Override
    public InventoryDto getItemsByIds(String itemId) {
        return inventoryBO.getItemNameById(itemId);
    }

    @Override
    public ArrayList<String> getCustomerNamesByPartialPhone(String contact) throws Exception {
        return customerBO.getCustomerIdByPartialContact(contact);
    }

    @Override
    public void placeOrder(ObservableList<OrderCartTM> cartData,
                           Label lblOrderId,
                           Label orderPlacementDate,
                           TextField txtCustomerContact,
                           ComboBox cmbCustomerName,
                           ComboBox cmbItemId,
                           TextField txtShipmentTrackingNumber,
                           ComboBox cmbPaymentMethod) {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            // 1. Prepare IDs and data
            String shipmentId = shipmentBO.generateNewId();
            String shipmentDate = orderPlacementDate.getText();
            String trackingNumber = txtShipmentTrackingNumber.getText();

            String orderId = lblOrderId.getText();
            String orderDate = orderPlacementDate.getText();
            String customerContact = txtCustomerContact.getText();
            String customerId = customerBO.getCustomerIdByContact(customerContact);
            String status = "Shipped";

            String paymentId = paymentBO.generateNewId();
            String paymentMethod = (String) cmbPaymentMethod.getSelectionModel().getSelectedItem();
            double totalAmount = cartData.stream().mapToDouble(OrderCartTM::getTotal).sum();

            // Save shipment
            boolean shipmentSaved = shipmentBO.save(
                    new ShipmentDto(
                            shipmentId,
                            trackingNumber,
                            LocalDate.parse(shipmentDate))
            );
            if (!shipmentSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save shipment!").show();
                return;
            }

            // Save order
            boolean orderSaved = ordersBO.save(new OrdersDto(
                    orderId,
                    LocalDate.parse(orderDate),
                    customerId,
                    shipmentId,
                    status
            ));
            if (!orderSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save order!").show();
                return;
            }

            // Save order items and update inventory
            boolean allItemsSaved = true;
            for (OrderCartTM cartTM : cartData) {
                String orderItemId = orderItemBO.generateNewId();
                boolean itemSaved = orderItemBO.save(new OrderItemDto(
                        orderItemId,
                        orderId,
                        cartTM.getItemId(),
                        cartTM.getCartQty(),
                        cartTM.getUnitPrice() * cartTM.getCartQty()
                ));
                boolean inventoryUpdated = inventoryBO.reduceItemQty(
                        cartTM.getItemId(),
                        cartTM.getCartQty()
                );
                if (!itemSaved || !inventoryUpdated) {
                    allItemsSaved = false;
                    break;
                }
            }
            if (!allItemsSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save order items or update inventory!").show();
                return;
            }

            // Save payment
            boolean paymentSaved = paymentBO.save(
                    new PaymentDto(
                            paymentId,
                            orderId,
                            totalAmount,
                            paymentMethod)
            );
            if (!paymentSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save payment!").show();
                return;
            }


            new Alert(Alert.AlertType.INFORMATION, "Order placed successfully!").show();

            try {
                boolean isEmailSent =new OrderReportMailer().sendLastOrderReport();

                if (isEmailSent) {
                    new Alert(Alert.AlertType.INFORMATION, "Order report sent to email successfully!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to send order report via email!").show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            // Commit transaction
            connection.commit();

        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error placing order!").show();
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void addToCart(ComboBox cmbItemId,
                          TextField txtCartQty,
                          Label txtAddToCartQty,
                          Label lblItemName,
                          Label lblItemPrice,
                          ComboBox cmbCustomerName,
                          ComboBox cmbPaymentMethod,
                          ObservableList<OrderCartTM> cartData,
                          TableView<OrderCartTM> tblOrderPlacement) {

        String selectedItemId = (String) cmbItemId.getSelectionModel().getSelectedItem();
        String cartQtyString = txtCartQty.getText();

        if (selectedItemId == null) {
            new Alert(Alert.AlertType.WARNING, "Please select item..!").show();
            return;
        }

        if (!cartQtyString.matches("^[0-9]+$")) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid quantity..!").show();
            return;
        }

        String itemQtyOnStockString = txtAddToCartQty.getText();
        int cartQty = Integer.parseInt(cartQtyString);
        int itemQtyOnStock = Integer.parseInt(itemQtyOnStockString);

        if (itemQtyOnStock < cartQty) {
            new Alert(Alert.AlertType.WARNING, "Not enough item quantity..!").show();
            return;
        }

        String selectedCustomerId = (String) cmbCustomerName.getValue();
        String itemName = lblItemName.getText();
        String itemUnitPriceString = lblItemPrice.getText();

        double itemUnitPrice = Double.parseDouble(itemUnitPriceString);
        double total = itemUnitPrice * cartQty;
        String paymentMethod = (String) cmbPaymentMethod.getSelectionModel().getSelectedItem();

        if (paymentMethod == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method..!").show();
            return;
        }

        for (OrderCartTM cartTM : cartData) {
            if (cartTM.getItemId().equals(selectedItemId)) {
                int newQty = cartTM.getCartQty() + cartQty;
                if (itemQtyOnStock < newQty) {
                    new Alert(Alert.AlertType.WARNING, "Not enough item quantity..!").show();
                    return;
                }
                cartTM.setCartQty(newQty);
                cartTM.setTotal(newQty * itemUnitPrice);
                tblOrderPlacement.refresh();
                // Update available quantity in UI only
                txtAddToCartQty.setText(String.valueOf(itemQtyOnStock - cartQty));
                return;
            }
        }

        Button removeBtn = new Button("Remove");

        removeBtn.setOnAction(action -> {
            cartData.remove(new OrderCartTM(
                    selectedCustomerId,
                    selectedItemId,
                    itemName,
                    cartQty,
                    itemUnitPrice,
                    total,
                    paymentMethod,
                    removeBtn
            ));
            tblOrderPlacement.refresh();
        });

        cartData.add(new OrderCartTM(
                selectedCustomerId,
                selectedItemId,
                itemName,
                cartQty,
                itemUnitPrice,
                total,
                paymentMethod,
                removeBtn
        ));
        // Update available quantity in UI only
        txtAddToCartQty.setText(String.valueOf(itemQtyOnStock - cartQty));
        // Do not update inventory in the database here
    }
}
