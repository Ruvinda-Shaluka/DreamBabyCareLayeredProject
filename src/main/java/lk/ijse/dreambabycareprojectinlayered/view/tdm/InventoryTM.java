package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class InventoryTM implements Comparable<InventoryTM> {
    private String inventory_id;
    private String item_name;
    private String printed_embroidered;
    private String size;
    private double unit_price;
    private int quantity_available;
    private String stored_location;

    @Override
    public int compareTo(InventoryTM o) {
        return inventory_id.compareTo(o.getInventory_id());
    }
}
