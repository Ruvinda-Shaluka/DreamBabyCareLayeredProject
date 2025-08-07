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
import lk.ijse.dreambabycareprojectinlayered.bo.custom.InventoryBO;
import lk.ijse.dreambabycareprojectinlayered.dto.InventoryDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.InventoryTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {
    public AnchorPane ancInventoryContainer;
    public TextField searchField;
    public Label inventoryIdLabel;
    public TextField txtItemName;
    public ComboBox cmbPrintedEmbroidered;
    public TextField txtSize;
    public TextField txtUnitPrice;
    public TextField txtQuantityAvailable;
    public TextField txtStoredLocation;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnReset;
    public TableView<InventoryTM> tblInventory;
    public TableColumn<InventoryTM,String> colInventoryId;
    public TableColumn<InventoryTM,String> colItemName;
    public TableColumn<InventoryTM,String> colPrinterEmbroidered;
    public TableColumn<InventoryTM,String> colSize;
    public TableColumn<InventoryTM,String> colUnitPrice;
    public TableColumn<InventoryTM,String> colQuantityAvailable;
    public TableColumn<InventoryTM,String> colStoredLocation;

    private final InventoryBO inventoryBO =(InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.INVENTORY);

    public void labelOverViewClickOnAction(MouseEvent mouseEvent) {
        navigateTo("/lk/ijse/dreambabycareprojectinlayered/assets/view/OverView.fxml");
    }

    public void search(KeyEvent keyEvent) {
        String searchText = searchField.getText();
        if (searchText.isEmpty()) {
            try {
                loadInventoryDataTable();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load inventory data.").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                tblInventory.setItems(FXCollections.observableArrayList(
                        inventoryBO.search(searchText)
                                .stream()
                                .map(inventoryDto -> new InventoryTM(
                                        inventoryDto.getInventory_id(),
                                        inventoryDto.getItem_name(),
                                        inventoryDto.getPrinted_embroidered(),
                                        inventoryDto.getSize(),
                                        inventoryDto.getUnit_price(),
                                        inventoryDto.getQuantity_available(),
                                        inventoryDto.getStored_location()
                                ))
                                .toList()
                ));
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to search items.").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void saveBtnOnAction(ActionEvent actionEvent) {
        String inventoryId = inventoryIdLabel.getText();
        String itemName = txtItemName.getText();
        String printedEmbroidered = cmbPrintedEmbroidered.getValue().toString();
        String size = txtSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int quantityAvailable = Integer.parseInt(txtQuantityAvailable.getText());
        String storedLocation = txtStoredLocation.getText();

        if (inventoryId.isEmpty() || itemName.isEmpty() || printedEmbroidered.isEmpty() || size.isEmpty() || txtUnitPrice.getText().isEmpty() || txtQuantityAvailable.getText().isEmpty() || storedLocation.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields.").show();
        } else {
            try {
                boolean isSaved = inventoryBO.save(new InventoryDto(
                        inventoryId,
                        itemName,
                        printedEmbroidered,
                        size,
                        unitPrice,
                        quantityAvailable,
                        storedLocation
                ));

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Item saved successfully.").show();
                    loadInventoryDataTable();
                    loadNextInventoryId();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to save item.").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error occurred while saving item.").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void resetPage() {
        try {
            loadInventoryDataTable();
            loadNextInventoryId();

            btnSave.setDisable(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);

            txtItemName.setText("");
            txtQuantityAvailable.setText("");
            txtUnitPrice.setText("");
            txtSize.setText("");
            txtStoredLocation.setText("");
            cmbPrintedEmbroidered.setValue(null);
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to reset.").show();
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        String inventoryId = inventoryIdLabel.getText();
        String itemName = txtItemName.getText();
        String printedEmbroidered = cmbPrintedEmbroidered.getValue().toString();
        String size = txtSize.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int quantityAvailable = Integer.parseInt(txtQuantityAvailable.getText());
        String storedLocation = txtStoredLocation.getText();

        if (inventoryId.isEmpty() || itemName.isEmpty() || printedEmbroidered.isEmpty() || size.isEmpty() || txtUnitPrice.getText().isEmpty() || txtQuantityAvailable.getText().isEmpty() || storedLocation.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an item to update.").show();
        } else {
            try {
                boolean isUpdated = inventoryBO.update(new InventoryDto(
                        inventoryId,
                        itemName,
                        printedEmbroidered,
                        size,
                        unitPrice,
                        quantityAvailable,
                        storedLocation
                ));

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, "Item updated successfully.").show();
                    loadInventoryDataTable();
                    resetPage();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update item.").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error occurred while updating item.").show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this item?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Delete Confirmation");

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            String inventoryId = inventoryIdLabel.getText();
            if (inventoryId.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please select an item to delete.").show();
            } else {
                try {
                    boolean isDeleted = inventoryBO.delete(inventoryId);
                    if (isDeleted) {
                        new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully.").show();
                        loadInventoryDataTable();
                        resetPage();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Failed to delete item.").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    new Alert(Alert.AlertType.ERROR, "Error occurred while deleting item.").show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void resetBtnOnAction(ActionEvent actionEvent) {
        resetPage();
    }

    public void onClickTable(MouseEvent mouseEvent) {
        InventoryTM selectedItem = tblInventory.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            inventoryIdLabel.setText(selectedItem.getInventory_id());
            txtItemName.setText(selectedItem.getItem_name());
            cmbPrintedEmbroidered.setValue(selectedItem.getPrinted_embroidered());
            txtSize.setText(selectedItem.getSize());
            txtUnitPrice.setText(String.valueOf(selectedItem.getUnit_price()));
            txtQuantityAvailable.setText(String.valueOf(selectedItem.getQuantity_available()));
            txtStoredLocation.setText(selectedItem.getStored_location());

            btnSave.setDisable(true);
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    private void navigateTo(String path) {
        try {
            ancInventoryContainer.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancInventoryContainer.widthProperty());
            anchorPane.prefHeightProperty().bind(ancInventoryContainer.heightProperty());

            ancInventoryContainer.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventory_id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colPrinterEmbroidered.setCellValueFactory(new PropertyValueFactory<>("printed_embroidered"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unit_price"));
        colQuantityAvailable.setCellValueFactory(new PropertyValueFactory<>("quantity_available"));
        colStoredLocation.setCellValueFactory(new PropertyValueFactory<>("stored_location"));

        cmbPrintedEmbroidered.setItems(FXCollections.observableArrayList("Printed", "Embroidered"));
        try{
            loadInventoryDataTable();
            loadNextInventoryId();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load inventory data.").show();

        }
    }

    private void loadNextInventoryId() {
        try {
            String nextInventoryId = inventoryBO.generateNewId();
            inventoryIdLabel.setText(nextInventoryId);
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load next inventory ID.").show();
        }
    }

    private void loadInventoryDataTable() throws Exception {

        tblInventory.setItems(FXCollections.observableArrayList(
                inventoryBO.loadAll()
                        .stream()
                        .map(
                                inventoryDto -> new InventoryTM(
                                        inventoryDto.getInventory_id(),
                                        inventoryDto.getItem_name(),
                                        inventoryDto.getPrinted_embroidered(),
                                        inventoryDto.getSize(),
                                        inventoryDto.getUnit_price(),
                                        inventoryDto.getQuantity_available(),
                                        inventoryDto.getStored_location()
                                ))
                        .toList()
        ));
    }
}
