package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.*;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.ProductionCartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceProductionBO extends SuperBO {

    boolean increaseInventoryQty(String inventoryId, int productQty);

    boolean reduceMaterialQty(String materialId, int materialUsageQty);

    boolean saveMaterialUsage(MaterialUsageDto materialUsageDto) throws Exception;

    boolean saveProductionTasks(ProductionTaskDto productionTaskDto) throws Exception;

    boolean saveTasks(TaskDto taskDto) throws Exception;

    boolean saveProductions(ProductionDto productionDto) throws Exception;

    String getNextProductionTaskId() throws Exception;

    String getNextTaskId() throws Exception;

    String getNextMaterialUsageId() throws Exception;

    void placeProduction(String text, String selectedItem, String text1, String selectedItem1, String text2, String selectedItem2, int i, int i1, TableView<ProductionCartTM> tblProductionPlacement);

    String generateNewProductionId() throws Exception;

    String getMaterialNameById(String newVal);

    String getMaterialColorTypeById(String newVal);

    String getMaterialQtyById(String newVal);

    InventoryDto getItemNameById(String newVal);

    String getEmployeeRoleById(String newVal);

    String getEmployeeNameById(String newVal);

    ArrayList<String> getAllMaterialIds();

    ArrayList<String> getAllInventoryIds() throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllEmployeeIds() throws SQLException, ClassNotFoundException;

    void addToCart(ComboBox cmbEmployeeId, ComboBox cmbInventoryId, ComboBox cmbMaterialId, TextField txtProductionDescription, TextField txtProductQty, TextField txtTaskDescription, TextField txtMaterialUsageQty, Label lblMaterialQty, Label lblEmployeeName, Label lblItemName, Label lblMaterialName, Label lblMaterialColorType, TableView<ProductionCartTM> tblProductionPlacement);
}
