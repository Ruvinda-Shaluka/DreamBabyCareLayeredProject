package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class SupplyTM implements Comparable<SupplyTM> {
    private String supply_id;
    private String supplier_id;
    private String material_id;
    private double amount;
    private int quantity;

    @Override
    public int compareTo(SupplyTM o) {
        return supply_id.compareTo(o.getSupply_id());
    }
}
