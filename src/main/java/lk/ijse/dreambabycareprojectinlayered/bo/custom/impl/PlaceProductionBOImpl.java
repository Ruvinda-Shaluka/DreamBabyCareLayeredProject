package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import javafx.scene.control.*;
import lk.ijse.dreambabycareprojectinlayered.bo.BOFactory;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.*;
import lk.ijse.dreambabycareprojectinlayered.db.DBConnection;
import lk.ijse.dreambabycareprojectinlayered.dto.*;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.ProductionCartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceProductionBOImpl implements PlaceProductionBO {
    InventoryBO inventoryBO = (InventoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.INVENTORY);
    MaterialBO materialBO = (MaterialBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MATERIAL);
    ProductionBO productionBO = (ProductionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PRODUCTION);
    TaskBO taskBO = (TaskBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.TASK);
    MaterialUsageBO materialUsageBO = (MaterialUsageBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.MATERIAL_USAGE);
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    ProductionTaskBO productionTaskBO = (ProductionTaskBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PRODUCTION_TASK);


    @Override
    public boolean increaseInventoryQty(String inventoryId, int productQty) {
        return inventoryBO.increaseInventoryQty(inventoryId, productQty);
    }

    @Override
    public boolean reduceMaterialQty(String materialId, int materialUsageQty) {
        return materialBO.reduceMaterialQty(materialId, materialUsageQty);
    }

    @Override
    public boolean saveMaterialUsage(MaterialUsageDto materialUsageDto) throws Exception {
        return materialUsageBO.save(materialUsageDto);
    }

    @Override
    public boolean saveProductionTasks(ProductionTaskDto productionTaskDto) throws Exception {
        return productionTaskBO.save(productionTaskDto);
    }

    @Override
    public boolean saveTasks(TaskDto taskDto) throws Exception {
        return taskBO.save(taskDto);
    }

    @Override
    public boolean saveProductions(ProductionDto productionDto) throws Exception {
        return productionBO.save(productionDto);
    }

    @Override
    public String getNextProductionTaskId() throws Exception {
        return productionTaskBO.generateNewId();
    }

    @Override
    public String getNextTaskId() throws Exception {
        return taskBO.generateNewId();
    }

    @Override
    public String getNextMaterialUsageId() throws Exception {
        return materialUsageBO.generateNewId();
    }

    @Override
    public void placeProduction(
            String productionId,
            String inventoryId,
            String productionDescription,
            String employeeId,
            String taskDescription,
            String materialId,
            int materialUsageQty,
            int productQty,
            TableView<ProductionCartTM> tblProductionPlacement
    ) {
        java.sql.Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            String productionStatus = "In Production";
            String taskStatus = "In Progress";

            String productionTaskId = getNextProductionTaskId();
            String taskId = getNextTaskId();
            String usageId = getNextMaterialUsageId();

            //Save new production
            boolean productionSaved = saveProductions(
                    new ProductionDto(
                            productionId, inventoryId, productionDescription, productionStatus
                    )
            );
            if (!productionSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save production!").show();
                return;
            }

            //Save new task
            boolean taskSaved = saveTasks(
                    new TaskDto(
                            taskId, employeeId, taskDescription, taskStatus
                    )
            );
            if (!taskSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save task!").show();
                return;
            }

            //Save new production_task
            boolean productionTaskSaved = saveProductionTasks(
                    new ProductionTaskDto(
                            productionTaskId, productionId, taskId
                    )
            );
            if (!productionTaskSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save production task!").show();
                return;
            }

            //Save new material_usage
            boolean materialUsageSaved = saveMaterialUsage(
                    new MaterialUsageDto(
                            usageId, productionId, materialId, materialUsageQty
                    )
            );
            if (!materialUsageSaved) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to save material usage!").show();
                return;
            }

            //Update material quantity (reduce)
            boolean materialUpdated = reduceMaterialQty(materialId, materialUsageQty);
            if (!materialUpdated) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to update material quantity!").show();
                return;
            }

            //Update inventory quantity (increase)
            boolean inventoryUpdated = increaseInventoryQty(inventoryId, productQty);
            if (!inventoryUpdated) {
                connection.rollback();
                new Alert(Alert.AlertType.ERROR, "Failed to update inventory quantity!").show();
                return;
            }

            //Commit transaction
            connection.commit();
            new Alert(Alert.AlertType.INFORMATION, "Production placed successfully!").show();

        } catch (Exception e) {
            try {
                if (connection != null) connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error placing production!").show();
        } finally {
            try {
                if (connection != null) connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String generateNewProductionId() throws Exception {
        return productionBO.generateNewId();
    }

    @Override
    public String getMaterialNameById(String newVal) {
        return materialBO.getMaterialNameById(newVal);
    }

    @Override
    public String getMaterialColorTypeById(String newVal) {
        return materialBO.getMaterialColorTypeById(newVal);
    }

    @Override
    public String getMaterialQtyById(String newVal) {
        return materialBO.getMaterialQtyById(newVal);
    }

    @Override
    public InventoryDto getItemNameById(String newVal) {
        return inventoryBO.getItemNameById(newVal);
    }

    @Override
    public String getEmployeeRoleById(String newVal) {
        return employeeBO.getEmployeeRoleById(newVal);
    }

    @Override
    public String getEmployeeNameById(String newVal) {
        return employeeBO.getEmployeeNameById(newVal);
    }

    @Override
    public ArrayList<String> getAllMaterialIds() {
        return materialBO.getAllMaterialIds();
    }

    @Override
    public ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException {
        return inventoryBO.getAllInventoryIds();
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException {
        return employeeBO.getAllEmployeeIds();
    }

    @Override
    public void addToCart(ComboBox cmbEmployeeId,
                          ComboBox cmbInventoryId,
                          ComboBox cmbMaterialId,
                          TextField txtProductionDescription,
                          TextField txtProductQty,
                          TextField txtTaskDescription,
                          TextField txtMaterialUsageQty,
                          Label lblMaterialQty,
                          Label lblEmployeeName,
                          Label lblItemName,
                          Label lblMaterialName,
                          Label lblMaterialColorType,
                          TableView<ProductionCartTM> tblProductionPlacement
    ) {
        String employeeId = (String) cmbEmployeeId.getSelectionModel().getSelectedItem();
        String inventoryId = (String) cmbInventoryId.getSelectionModel().getSelectedItem();
        String materialId = (String) cmbMaterialId.getSelectionModel().getSelectedItem();
        String productDescription = txtProductionDescription.getText();
        String taskDescription = txtTaskDescription.getText();
        String productQtyStr = txtProductQty.getText();
        String materialUsageQtyStr = txtMaterialUsageQty.getText();

        // Validate selections and inputs
        if (employeeId == null || inventoryId == null || materialId == null ||
                productDescription.isEmpty() || taskDescription.isEmpty() ||
                productQtyStr.isEmpty() || materialUsageQtyStr.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields!").show();
            return;
        }

        if (!productQtyStr.matches("\\d+") || !materialUsageQtyStr.matches("\\d+")) {
            new Alert(Alert.AlertType.WARNING, "Enter valid numeric quantities!").show();
            return;
        }

        int productQty = Integer.parseInt(productQtyStr);
        int materialUsageQty = Integer.parseInt(materialUsageQtyStr);
        int availableMaterialQty = Integer.parseInt(lblMaterialQty.getText());

        if (materialUsageQty > availableMaterialQty) {
            new Alert(Alert.AlertType.WARNING, "Not enough material quantity!").show();
            return;
        }

        String employeeName = lblEmployeeName.getText();
        String itemName = lblItemName.getText();
        String materialName = lblMaterialName.getText();
        String colorType = lblMaterialColorType.getText();

        // Check if this item/material is already in the cart
        for (ProductionCartTM cartTM : tblProductionPlacement.getItems()) {
            if (cartTM.getItemName().equals(itemName) && cartTM.getMaterialName().equals(materialName)) {
                int newProductQty = cartTM.getQuantity() + productQty;
                int newMaterialUsageQty = cartTM.getQtyNeeded() + materialUsageQty;
                if (newMaterialUsageQty > availableMaterialQty) {
                    new Alert(Alert.AlertType.WARNING, "Not enough material quantity!").show();
                    return;
                }
                cartTM.setQuantity(newProductQty);
                cartTM.setQtyNeeded(newMaterialUsageQty);
                tblProductionPlacement.refresh();
                lblMaterialQty.setText(String.valueOf(availableMaterialQty - materialUsageQty));
                return;
            }
        }

        // Add new row to cart
        Button removeBtn = new Button("Remove");


        removeBtn.setOnAction(e -> {
            tblProductionPlacement.getItems().remove(new ProductionCartTM(
                    employeeName,
                    itemName,
                    productDescription,
                    productQty,
                    taskDescription,
                    materialName,
                    materialUsageQty,
                    colorType,
                    removeBtn
            ));
            tblProductionPlacement.refresh();
            // Optionally, restore material quantity in UI here
        });

        tblProductionPlacement.getItems().add(new ProductionCartTM(
                employeeName,
                itemName,
                productDescription,
                productQty,
                taskDescription,
                materialName,
                materialUsageQty,
                colorType,
                removeBtn
        ));
        lblMaterialQty.setText(String.valueOf(availableMaterialQty - materialUsageQty));

    }
}
