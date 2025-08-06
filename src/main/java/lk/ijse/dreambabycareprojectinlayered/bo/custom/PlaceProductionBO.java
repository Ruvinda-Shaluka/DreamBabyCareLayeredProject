package lk.ijse.dreambabycareprojectinlayered.bo.custom;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.dreambabycareprojectinlayered.bo.SuperBO;
import lk.ijse.dreambabycareprojectinlayered.dto.MaterialUsageDto;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionDto;
import lk.ijse.dreambabycareprojectinlayered.dto.ProductionTaskDto;
import lk.ijse.dreambabycareprojectinlayered.dto.TaskDto;
import lk.ijse.dreambabycareprojectinlayered.view.tdm.ProductionCartTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceProductionBO extends SuperBO {

    boolean increaseInventoryQty(String inventoryId, int productQty);

    boolean reduceMaterialQty(String materialId, int materialUsageQty);

    boolean saveMaterialUsage(MaterialUsageDto materialUsageDto);

    boolean saveProductionTasks(ProductionTaskDto productionTaskDto);

    boolean saveTasks(TaskDto taskDto);

    boolean saveProductions(ProductionDto productionDto);

    String getNextProductionTaskId();

    String getNextTaskId();

    String getNextMaterialUsageId();

    void placeProduction(String text, String selectedItem, String text1, String selectedItem1, String text2, String selectedItem2, int i, int i1, TableView<ProductionCartTM> tblProductionPlacement);

    String generateNewProductionId();

    String getMaterialNameById(String newVal);

    String getMaterialColorTypeById(String newVal);

    char[] getMaterialQtyById(String newVal);

    String getItemNameById(String newVal);

    String getEmployeeRoleById(String newVal);

    String getEmployeeNameById(String newVal);

    ArrayList<String> getAllMaterialIds();

    ArrayList<String> getAllInventoryIds();

    ArrayList<String> getAllEmployeeIds();

    void addToCart(ComboBox cmbEmployeeId, ComboBox cmbInventoryId, ComboBox cmbMaterialId, TextField txtProductionDescription, TextField txtProductQty, TextField txtTaskDescription, TextField txtMaterialUsageQty, Label lblMaterialQty, Label lblEmployeeName, Label lblItemName, Label lblMaterialName, Label lblMaterialColorType, TableView<ProductionCartTM> tblProductionPlacement);
}
