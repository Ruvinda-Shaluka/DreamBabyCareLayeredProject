package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductionCartTM implements Comparable<ProductionCartTM> {
    private String employeeName;
    private String itemName;
    private String productDescription;
    private int quantity;
    private String taskDescription;
    private String materialName;
    private int qtyNeeded;
    private String colorType;
    private Button btnAction;

    @Override
    public int compareTo(ProductionCartTM o) {
        return this.itemName.compareTo(o.itemName);
    }
}
