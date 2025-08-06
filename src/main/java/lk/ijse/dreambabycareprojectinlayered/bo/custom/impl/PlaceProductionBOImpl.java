package lk.ijse.dreambabycareprojectinlayered.bo.custom.impl;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dreambabycareprojectinlayered.bo.custom.PlaceProductionBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialUsageDto;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionDto;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionTaskDto;
import lk.ijse.dreambabycareprojectinlayered.dto.TaskDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.ProductionCartTM;

import java.util.ArrayList;

public class PlaceProductionBOImpl implements PlaceProductionBO {
    @Override
    public boolean increaseInventoryQty(String inventoryId, int productQty) {
        return false;
    }

    @Override
    public boolean reduceMaterialQty(String materialId, int materialUsageQty) {
        return false;
    }

    @Override
    public boolean saveMaterialUsage(MaterialUsageDto materialUsageDto) {
        return false;
    }

    @Override
    public boolean saveProductionTasks(ProductionTaskDto productionTaskDto) {
        return false;
    }

    @Override
    public boolean saveTasks(TaskDto taskDto) {
        return false;
    }

    @Override
    public boolean saveProductions(ProductionDto productionDto) {
        return false;
    }

    @Override
    public String getNextProductionTaskId() {
        return "";
    }

    @Override
    public String getNextTaskId() {
        return "";
    }

    @Override
    public String getNextMaterialUsageId() {
        return "";
    }

    @Override
    public void placeProduction(String text, String selectedItem, String text1, String selectedItem1, String text2, String selectedItem2, int i, int i1, TableView<ProductionCartTM> tblProductionPlacement) {

    }

    @Override
    public String generateNewProductionId() {
        return "";
    }

    @Override
    public String getMaterialNameById(String newVal) {
        return "";
    }

    @Override
    public String getMaterialColorTypeById(String newVal) {
        return "";
    }

    @Override
    public char[] getMaterialQtyById(String newVal) {
        return new char[0];
    }

    @Override
    public String getItemNameById(String newVal) {
        return "";
    }

    @Override
    public String getEmployeeRoleById(String newVal) {
        return "";
    }

    @Override
    public String getEmployeeNameById(String newVal) {
        return "";
    }

    @Override
    public ArrayList<String> getAllMaterialIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllInventoryIds() {
        return null;
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() {
        return null;
    }

    @Override
    public void addToCart(ComboBox cmbEmployeeId, ComboBox cmbInventoryId, ComboBox cmbMaterialId, TextField txtProductionDescription, TextField txtProductQty, TextField txtTaskDescription, TextField txtMaterialUsageQty, Label lblMaterialQty, Label lblEmployeeName, Label lblItemName, Label lblMaterialName, Label lblMaterialColorType, TableView<ProductionCartTM> tblProductionPlacement) {

    }
}
