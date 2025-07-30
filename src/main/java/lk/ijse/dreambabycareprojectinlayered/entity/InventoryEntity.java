package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class InventoryEntity {

    private String inventory_id;
    private String item_name;
    private String printed_embroidered;
    private String size;
    private double unit_price;
    private int quantity_available;
    private String stored_location;


}
