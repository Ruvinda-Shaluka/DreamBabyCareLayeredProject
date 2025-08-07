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
import lk.ijse.dreambabycareprojectinlayered.bo.custom.DiscountBO;
import lk.ijse.dreambabycareprojectinlayered.dto.DiscountDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.DiscountTM;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DiscountController implements Initializable {
    public AnchorPane ancDiscountContainer;
    public TextField searchField;
    public Label discountIdLabel;
    public ComboBox cmbPaymentId1;
    public ComboBox cmbDiscountType1;
    public TextField txtDiscountAmount;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<DiscountTM> tblDiscount;
    public TableColumn<DiscountTM,String> colDiscountId;
    public TableColumn<DiscountTM,String> colPaymentId;
    public TableColumn<DiscountTM,String> colDiscountType;
    public TableColumn<DiscountTM,String> colDiscountAmount;

    private final DiscountBO discountBO =(DiscountBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.DISCOUNT);

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            try {
                loadDiscountTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load Discounts").show();
            }
        } else {
            try {
                tblDiscount.setItems(FXCollections.observableArrayList(
                        discountBO.search(searchText)
                                .stream()
                                .map(discountDto -> new DiscountTM(
                                        discountDto.getDiscount_id(),
                                        discountDto.getPayment_id(),
                                        discountDto.getDiscount_type(),
                                        discountDto.getDiscount_percentage()
                                ))
                                .toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load Discounts").show();
            }
        }
    }

    public void goToAddPaymentLabel(MouseEvent mouseEvent) {
        navigateTo("lk/ijse/dreambabycareprojectinlayered/assets/view/PaymentView.fxml");

    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        String discountId = discountIdLabel.getText();
        String paymentId = cmbPaymentId1.getSelectionModel().getSelectedItem().toString();
        String discountType = cmbDiscountType1.getSelectionModel().getSelectedItem().toString();
        double amount = Double.parseDouble(txtDiscountAmount.getText());

        if (discountId.isEmpty() || paymentId.isEmpty() || discountType.isEmpty() || txtDiscountAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            boolean isSaved = discountBO.save(new DiscountDto(
                    discountId,
                    paymentId,
                    discountType,
                    amount
            ));

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Discount saved successfully").show();
                loadNextId();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save Discount").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to save Discount").show();
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String discountId = discountIdLabel.getText();
        String paymentId = cmbPaymentId1.getSelectionModel().getSelectedItem().toString();
        String discountType = cmbDiscountType1.getSelectionModel().getSelectedItem().toString();
        double amount = Double.parseDouble(txtDiscountAmount.getText());

        if (discountId.isEmpty() || paymentId.isEmpty() || discountType.isEmpty() || txtDiscountAmount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            boolean isUpdated = discountBO.update(new DiscountDto(
                    discountId,
                    paymentId,
                    discountType,
                    amount
            ));

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Discount updated successfully").show();
                loadDiscountTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Discount").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update Discount").show();
        }

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Discount?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Discount");
        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String discountId = discountIdLabel.getText();
            try {
                boolean isDeleted = discountBO.delete(discountId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Discount deleted successfully").show();
                    loadDiscountTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Discount").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete Discount").show();
            }
        }
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        DiscountTM selectedItem = tblDiscount.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            discountIdLabel.setText(selectedItem.getDiscount_id());
            cmbPaymentId1.setValue(selectedItem.getPayment_id());
            cmbDiscountType1.setValue(selectedItem.getDiscount_type());
            txtDiscountAmount.setText(String.valueOf(selectedItem.getDiscount_percentage()));

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private void navigateTo(String path) {
        try {
            ancDiscountContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDiscountContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDiscountContainer.heightProperty());

            ancDiscountContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colDiscountId.setCellValueFactory(new PropertyValueFactory<>("discount_id"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colDiscountType.setCellValueFactory(new PropertyValueFactory<>("discount_type"));
        colDiscountAmount.setCellValueFactory(new PropertyValueFactory<>("discount_percentage"));

        try {
            cmbPaymentId1.setItems(FXCollections.observableArrayList(discountBO.getAllPaymentIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Payment Ids").show();
        }

        try {
            cmbDiscountType1.setItems(FXCollections.observableArrayList("Retail Online", "Retail Local", "Wholesale Online", "Wholesale Local"));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Discount Types").show();
        }

        try {
            loadDiscountTableData();
            loadNextId();
            loadPaymentIds();
            loadDiscountTypes();

            /*cmbPaymentId1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue != null) {
                    try {
                        lblAmount1.setText(String.valueOf(discountModel.getPaymentAmountById(newValue.toString())));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        new Alert(Alert.AlertType.ERROR, "Failed to load Discount Amount").show();
                    }
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Discounts").show();
        }


    }

    private void loadDiscountTypes() {
        cmbDiscountType1.setItems(FXCollections.observableArrayList("Retail Online", "Retail Local", "Wholesale Online", "Wholesale Local"));
    }

    private void loadPaymentIds() throws Exception {
        cmbPaymentId1.setItems(FXCollections.observableArrayList(discountBO.getAllPaymentIds()));

    }

    private void loadNextId() {
        try {
            String nextId = discountBO.generateNewId();
            discountIdLabel.setText(nextId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load next Id").show();
        }
    }

    private void loadDiscountTableData() throws Exception {
        tblDiscount.setItems(FXCollections.observableArrayList(
                discountBO.loadAll()
                        .stream()
                        .map(discountDto -> new DiscountTM(
                                discountDto.getDiscount_id(),
                                discountDto.getPayment_id(),
                                discountDto.getDiscount_type(),
                                discountDto.getDiscount_percentage()
                        ))
                        .toList()
        ));
    }

    private void resetPage(){
        try {
            discountIdLabel.setText("");
            cmbPaymentId1.getSelectionModel().clearSelection();
            cmbDiscountType1.getSelectionModel().clearSelection();
            txtDiscountAmount.setText("");
            searchField.clear();
            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            loadDiscountTableData();
            loadNextId();
            loadPaymentIds();
            loadDiscountTypes();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Reset").show();
        }
    }

}
