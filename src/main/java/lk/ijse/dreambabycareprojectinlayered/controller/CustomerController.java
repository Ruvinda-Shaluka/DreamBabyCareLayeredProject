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
import lk.ijse.dreambabycareprojectinlayered.bo.custom.CustomerBO;
import lk.ijse.dreambabycareprojectinlayered.dto.CustomerDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.CustomerTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    public AnchorPane ancCustomerViewContainer;
    public TextField searchField;
    public Label customerIdLabel;
    public TextField txtName;
    public TextField txtNumber;
    public TextField txtAddress;
    public ComboBox cmbOrderPlatform;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn<CustomerTM,String> colId;
    public TableColumn<CustomerTM,String> colName;
    public TableColumn<CustomerTM,String> colNumber;
    public TableColumn<CustomerTM,String> colAddress;
    public TableColumn<CustomerTM,String> colOrderPlatform;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);

    private final String namePattern = "^[A-Za-z ]+$";
    private final String numberPattern = "^[0-9]{10}$";
    private final String addressPattern = "^[A-Za-z0-9 ,./-]+$";

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            loadCustomerTableData();
        } else {
            try {
                ArrayList<CustomerDto> customerList = customerBO.search(searchText);
                tblCustomer.setItems(FXCollections.observableArrayList(
                        customerList.stream()
                                .map(customerDto -> new CustomerTM(
                                        customerDto.getCustomerId(),
                                        customerDto.getCustomerName(),
                                        customerDto.getCustomerPhone(),
                                        customerDto.getCustomerAddress(),
                                        customerDto.getOrderPlatForm()
                                ))
                                .toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search customers").show();
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String customerId = customerIdLabel.getText();
        String name = txtName.getText();
        String number = txtNumber.getText();
        String address = txtAddress.getText();
        String orderPlatform = (String) cmbOrderPlatform.getValue();

        boolean isValidName = name.matches(namePattern);
        boolean isValidNumber = number.matches(numberPattern);
        boolean isValidAddress = address.matches(addressPattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #7367F0;");
        txtNumber.setStyle(txtNumber.getStyle() + ";-fx-border-color: #7367F0;");
        txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: #7367F0;");

        if (customerId.isEmpty() || name.isEmpty() || number.isEmpty() || address.isEmpty() || orderPlatform.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid name format").show();
            return;
        }
        if (!isValidNumber) {
            txtNumber.setStyle(txtNumber.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid number format").show();
            return;
        }
        if (!isValidAddress) {
            txtAddress.setStyle(txtAddress.getStyle() + ";-fx-border-color: red;");
            new Alert(Alert.AlertType.ERROR, "Invalid address format").show();
            return;
        }
        if (cmbOrderPlatform.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select an order platform").show();
            return;
        }

        // Save the customer
        if (isValidName && isValidNumber && isValidAddress) {
            try {
                boolean isSaved = customerBO.save(new CustomerDto(
                        customerId,
                        name,
                        number,
                        address,
                        orderPlatform
                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully").show();
                    loadCustomerTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save customer").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to save customer").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String customerId = customerIdLabel.getText();
        String name = txtName.getText();
        String number = txtNumber.getText();
        String address = txtAddress.getText();
        String orderPlatform = (String) cmbOrderPlatform.getValue();

        if (customerId.isEmpty() || name.isEmpty() || number.isEmpty() || address.isEmpty() || orderPlatform.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            boolean isUpdated = customerBO.update(new CustomerDto(
                    customerId,
                    name,
                    number,
                    address,
                    orderPlatform
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully").show();
                loadCustomerTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update customer").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update customer"+e.getMessage()).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void resetPage() {
        try {
            loadCustomerTableData();
            loadNextId();

            //save button(id) -> enable
            btnSave.setDisable(false);
            //update button(id) -> disable
            btnUpdate.setDisable(true);
            //delete button(id) -> disable
            btnDelete.setDisable(true);

            txtName.setText("");
            txtNumber.setText("");
            txtAddress.setText("");
            cmbOrderPlatform.getSelectionModel().clearSelection();


        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers").show();

        }
    }

    private void loadNextId() {
        try {
            String nextId = customerBO.generateNewId();
            customerIdLabel.setText(nextId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load next customer ID").show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerTableData() {

        try {
            tblCustomer.setItems(FXCollections.observableArrayList(
                    customerBO.loadAll()
                            .stream()
                            .map(customerDto -> new CustomerTM(
                                    customerDto.getCustomerId(),
                                    customerDto.getCustomerName(),
                                    customerDto.getCustomerPhone(),
                                    customerDto.getCustomerAddress(),
                                    customerDto.getOrderPlatForm()
                            ))
                            .toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this customer?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Confirmation");

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String customerId = customerIdLabel.getText();
            try {
                boolean isDeleted = customerBO.delete(customerId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully").show();
                    loadCustomerTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete customer").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete customer").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            customerIdLabel.setText(selectedItem.getCustomerId());
            txtName.setText(selectedItem.getCustomerName());
            txtNumber.setText(selectedItem.getCustomerPhone());
            txtAddress.setText(selectedItem.getCustomerAddress());
            cmbOrderPlatform.setValue(selectedItem.getOrderPlatForm());

            // save button disable
            btnSave.setDisable(true);

            // update, delete button enable
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private void navigateTo(String path) {
        try {
            ancCustomerViewContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancCustomerViewContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancCustomerViewContainer.heightProperty());

            ancCustomerViewContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colOrderPlatform.setCellValueFactory(new PropertyValueFactory<>("orderPlatForm"));

        cmbOrderPlatform.setItems(FXCollections.observableArrayList("WhatsApp", "Facebook", "Instagram", "Direct Call", "Wholesale", "Online", "Retail Store"));
        try {
            loadCustomerTableData();
            loadNextId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load customers").show();
        }
    }
}
