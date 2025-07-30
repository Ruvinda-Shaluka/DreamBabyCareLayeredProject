package lk.ijse.dreambabycareprojectinlayered.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class OrderItemEntity {

    private String order_item_id;
    private String order_id;
    private String inventory_id;
    private int quantity;
    private double amount;

}
