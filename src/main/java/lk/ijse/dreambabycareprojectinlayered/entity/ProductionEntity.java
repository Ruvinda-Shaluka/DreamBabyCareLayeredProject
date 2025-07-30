package lk.ijse.dreambabycareprojectinlayered.entity;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductionEntity {

    private String production_id;
    private String inventory_id;
    private String description;
    private String status;

}
