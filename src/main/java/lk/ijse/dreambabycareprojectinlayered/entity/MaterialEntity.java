package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MaterialEntity {

    private  String material_id;
    private  String name;
    private  String color_type;
    private int quantity;

}
