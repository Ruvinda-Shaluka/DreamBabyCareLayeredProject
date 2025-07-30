package lk.ijse.dreambabycareprojectinlayered.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class MaterialUsageEntity {

    private String usage_id;
    private String production_id;
    private String material_id;
    private int quantity_used;

}
