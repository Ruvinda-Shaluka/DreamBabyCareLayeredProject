package lk.ijse.dreambabycareprojectinlayered.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dreambabycareprojectinlayered.bo.BOFactory;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.PlaceProductionBO;
import lk.ijse.dreambabycareprojectinlayered.db.DBConnection;
import lk.ijse.dreambabycareprojectinlayered.dto.*;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.ProductionCartTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProductionPlacementController implements Initializable {
    public AnchorPane ancProductionPlacementPage;
    public Label homeLabel;
    public Label lblProductionId;
    public Label productionPlacementDate;
    public ComboBox cmbEmployeeId;
    public Label lblEmployeeName;
    public Label labelPopUpCustomer;
    public Label lblEmployeeRole;
    public ComboBox cmbInventoryId;
    public Label lblItemName;
    public TextField txtProductionDescription;
    public TextField txtProductQty;
    public TextField txtTaskDescription;
    public TextField txtMaterialUsageQty;
    public ComboBox cmbMaterialId;
    public Label lblMaterialName;
    public Label lblMaterialColorType;
    public Label lblMaterialColorType11;
    public Label lblMaterialQty;
    public Button btnAddToCart;
    public TableView<ProductionCartTM> tblProductionPlacement;
    public TableColumn<ProductionCartTM,String> colEmployeeName;
    public TableColumn<ProductionCartTM,String> colItemName;
    public TableColumn<ProductionCartTM,String> colProductDescription;
    public TableColumn<ProductionCartTM,Integer> colQuantity;
    public TableColumn<ProductionCartTM,String> colTaskDescription;
    public TableColumn<ProductionCartTM,String> colMaterialName;
    public TableColumn<ProductionCartTM,Integer> colQtyNeeded;
    public TableColumn<ProductionCartTM,String> colColorType;
    public TableColumn<ProductionCartTM,Button> colAction;

    private final PlaceProductionBO placeProductionBO = (PlaceProductionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACE_PRODUCTION);

    private void setCellValues(){
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colProductDescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTaskDescription.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));
        colMaterialName.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        colQtyNeeded.setCellValueFactory(new PropertyValueFactory<>("qtyNeeded"));
        colColorType.setCellValueFactory(new PropertyValueFactory<>("colorType"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnAction"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValues();
        try {
            refreshPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
        }
        // Load employee IDs
        try {
            cmbEmployeeId.setItems(FXCollections.observableArrayList(placeProductionBO.getAllEmployeeIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load employees..!").show();
        }

        // Load inventory IDs
        try {
            cmbInventoryId.setItems(FXCollections.observableArrayList(placeProductionBO.getAllInventoryIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load inventory items..!").show();
        }

        // Load material IDs
        try {
            cmbMaterialId.setItems(FXCollections.observableArrayList(placeProductionBO.getAllMaterialIds()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load materials..!").show();
        }

        // Employee selection listener
        cmbEmployeeId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    lblEmployeeName.setText(placeProductionBO.getEmployeeNameById((String) newVal));
                    lblEmployeeRole.setText(placeProductionBO.getEmployeeRoleById((String) newVal));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Inventory selection listener
        cmbInventoryId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    InventoryDto dto = placeProductionBO.getItemNameById((String) newVal);
                    lblItemName.setText(dto != null ? dto.getItem_name() : "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Material selection listener
        cmbMaterialId.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    lblMaterialName.setText(placeProductionBO.getMaterialNameById((String) newVal));
                    lblMaterialColorType.setText(placeProductionBO.getMaterialColorTypeById((String) newVal));
                    lblMaterialQty.setText(String.valueOf(placeProductionBO.getMaterialQtyById((String) newVal)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshPage() {
        // Clear all fields
        cmbEmployeeId.getSelectionModel().clearSelection();
        cmbInventoryId.getSelectionModel().clearSelection();
        cmbMaterialId.getSelectionModel().clearSelection();
        lblEmployeeName.setText("");
        lblEmployeeRole.setText("");
        lblItemName.setText("");
        txtProductionDescription.clear();
        txtProductQty.clear();
        txtTaskDescription.clear();
        txtMaterialUsageQty.clear();
        lblMaterialName.setText("");
        lblMaterialColorType.setText("");
        lblMaterialQty.setText("");

        // Clear the table
        tblProductionPlacement.getItems().clear();

        // Set new production ID and date
        try {
            lblProductionId.setText(placeProductionBO.generateNewProductionId());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to generate new production ID..!").show();
        }
        productionPlacementDate.setText(String.valueOf(LocalDate.now()));
    }

    public void goToDashBoardPage(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/DashBoardView.fxml");
    }

    public void btnResetOnAction(ActionEvent actionEvent) {
        refreshPage();
    }

    public void btnPlaceProductionOnAction(ActionEvent actionEvent) {

        placeProductionBO.placeProduction(
                lblProductionId.getText(),
                (String) cmbInventoryId.getSelectionModel().getSelectedItem(),
                txtProductionDescription.getText(),
                (String) cmbEmployeeId.getSelectionModel().getSelectedItem(),
                txtTaskDescription.getText(),
                (String) cmbMaterialId.getSelectionModel().getSelectedItem(),
                Integer.parseInt(txtMaterialUsageQty.getText()),
                Integer.parseInt(txtProductQty.getText()),
                tblProductionPlacement
        );        refreshPage();
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        placeProductionBO.addToCart(
                cmbEmployeeId,
                cmbInventoryId,
                cmbMaterialId,
                txtProductionDescription,
                txtProductQty,
                txtTaskDescription,
                txtMaterialUsageQty,
                lblMaterialQty,
                lblEmployeeName,
                lblItemName,
                lblMaterialName,
                lblMaterialColorType,
                tblProductionPlacement
        );

    }

    private void navigateTo(String path) {
        try {
            ancProductionPlacementPage.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));


            anchorPane.prefWidthProperty().bind(ancProductionPlacementPage.widthProperty());
            anchorPane.prefHeightProperty().bind(ancProductionPlacementPage.heightProperty());

            ancProductionPlacementPage.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }



}
