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
import lk.ijse.dreambabycareprojectinlayered.bo.custom.MaterialBO;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.SupplyBO;
import lk.ijse.dreambabycareprojectinlayered.dto.SupplyDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.SupplyTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplyController implements Initializable {
    public AnchorPane ancSupplyContainer;
    public TextField searchField;
    public Label supplyIdLabel;
    public ComboBox cmbSupplierId1;
    public ComboBox cmbMaterialId1;
    public TextField txtAmount;
    public TextField txtQuantity;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<SupplyTM> tblSupply;
    public TableColumn<SupplyTM,String> colSupplyId;
    public TableColumn<SupplyTM,String> colSupplierId;
    public TableColumn<SupplyTM,String> colMaterialId;
    public TableColumn<SupplyTM,String> colAmount;
    public TableColumn<SupplyTM,String> colQuantity;

    private final SupplyBO supplyBO = (SupplyBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPPLY);
    private final MaterialBO materialBO =(MaterialBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MATERIAL);

    private final String quantityRegex = "^\\d+$";
    private final String amountRegex = "^\\d+(\\.\\d{2})?$";

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String supplyId = supplyIdLabel.getText();
        String supplierId = (String) cmbSupplierId1.getSelectionModel().getSelectedItem();
        String materialId = (String) cmbMaterialId1.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double amount = Double.parseDouble(txtAmount.getText());

        if(supplierId.isEmpty() || materialId.isEmpty() || txtQuantity.getText().isEmpty() || txtAmount.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields..!").show();
            return;
        }

        try {
            boolean isUpdated = supplyBO.update(new SupplyDto(
                    supplyId,
                    supplierId,
                    materialId,
                    amount,
                    quantity
            ));

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Supply updated successfully..!").show();
                loadSupplyTableData();
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update Supply..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to update Supply..!").show();
        }

    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this Supply?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Confirmation");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            String supplyId = supplyIdLabel.getText();
            try {
                boolean isDeleted = supplyBO.delete(supplyId);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Supply deleted successfully..!").show();
                    loadSupplyTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete Supply..!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to delete Supply..!").show();
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {

        String supplyId = supplyIdLabel.getText();
        String supplierId = (String) cmbSupplierId1.getSelectionModel().getSelectedItem();
        String materialId = (String) cmbMaterialId1.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double amount = Double.parseDouble(txtAmount.getText());

        if(supplierId.isEmpty() || materialId.isEmpty() || txtQuantity.getText().isEmpty() || txtAmount.getText().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields..!").show();
            return;
        }

        txtAmount.setStyle(txtAmount.getStyle()+";-fx-border-color: #7367F0");
        txtQuantity.setStyle(txtQuantity.getStyle()+";-fx-border-color: #7367F0");

        boolean isValidAmount = txtAmount.getText().matches(amountRegex);
        boolean isValidQuantity = txtQuantity.getText().matches(quantityRegex);

        if (!isValidAmount) {
            txtAmount.setStyle(txtAmount.getStyle() + ";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid Amount..!").show();
            return;
        }

        if (!isValidQuantity) {
            txtQuantity.setStyle(txtQuantity.getStyle() + ";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity..!").show();
            return;
        }

        if (isValidAmount && isValidQuantity){
            try {
                boolean isSaved = supplyBO.save(new SupplyDto(
                        supplyId,
                        supplierId,
                        materialId,
                        amount,
                        quantity
                ));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Supply saved successfully..!").show();
                    loadSupplyTableData();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save Supply..!").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to save Supply..!").show();
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        SupplyTM selectedItem = tblSupply.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            supplyIdLabel.setText(selectedItem.getSupply_id());
            cmbSupplierId1.setValue(selectedItem.getSupplier_id());
            cmbMaterialId1.setValue(selectedItem.getMaterial_id());
            txtQuantity.setText(String.valueOf(selectedItem.getQuantity()));
            txtAmount.setText(String.valueOf(selectedItem.getAmount()));

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
        colSupplyId.setCellValueFactory(new PropertyValueFactory<>("supply_id"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("material_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        try {
            cmbSupplierId1.setItems(FXCollections.observableArrayList(supplyBO.getAllSupplierIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Supplier IDs..!").show();
        }

        try {
            cmbMaterialId1.setItems(FXCollections.observableArrayList(materialBO.getAllMaterialIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Material IDs..!").show();
        }

        try {
            loadSupplyTableData();
            loadNextId();
            loadSupplierIds();
            loadMaterialIds();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Supply Table Data..!").show();
        }
    }

    private void loadNextId() {
        try {
            String nextSupplyId = supplyBO.generateNewId();
            supplyIdLabel.setText(nextSupplyId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load Order Item IDs..!").show();
        }
    }

    private void loadSupplyTableData() throws Exception {
        tblSupply.setItems(FXCollections.observableArrayList(
                supplyBO.loadAll()
                        .stream()
                        .map(supplyDto -> new SupplyTM(
                                supplyDto.getSupply_id(),
                                supplyDto.getSupplier_id(),
                                supplyDto.getMaterial_id(),
                                supplyDto.getAmount(),
                                supplyDto.getQuantity()
                        ))
                        .toList()
        ));

    }

    private void navigateTo(String path) {
        try {
            ancSupplyContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancSupplyContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancSupplyContainer.heightProperty());

            ancSupplyContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    //load supplier ids (forieng keys)
    private void loadSupplierIds() throws SQLException, ClassNotFoundException {
        cmbSupplierId1.setItems(FXCollections.observableArrayList(supplyBO.getAllSupplierIds()));
    }

    private void loadMaterialIds() throws SQLException, ClassNotFoundException {
        cmbMaterialId1.setItems(FXCollections.observableArrayList(materialBO.getAllMaterialIds()));
    }

    private void resetPage(){
        try {
            loadSupplyTableData();
            loadNextId();

            //save button(id) -> enable
            btnSave.setDisable(false);
            //update button(id) -> disable
            btnUpdate.setDisable(true);
            //delete button(id) -> disable
            btnDelete.setDisable(true);

            cmbSupplierId1.getSelectionModel().clearSelection();
            cmbMaterialId1.getSelectionModel().clearSelection();
            txtQuantity.setText("");
            txtAmount.setText("");



        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to Reset").show();

        }
    }

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            try {
                loadSupplyTableData();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load Supply Table Data..!").show();
            }
        }else {
            try {
                tblSupply.setItems(FXCollections.observableArrayList(
                        supplyBO.search(searchText)
                                .stream()
                                .map(
                                        supplyDto -> new SupplyTM(
                                                supplyDto.getSupply_id(),
                                                supplyDto.getSupplier_id(),
                                                supplyDto.getMaterial_id(),
                                                supplyDto.getAmount(),
                                                supplyDto.getQuantity()
                                        ))
                                .toList()
                ));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load Supplier Name..!").show();
            }
        }
    }

    public void goToAddSupplierLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/SupplierView.fxml");
    }

    public void goToAddMaterialLabel(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/MaterialView.fxml");
    }
}
