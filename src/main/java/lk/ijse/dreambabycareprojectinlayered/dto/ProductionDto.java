package lk.ijse.dreambabycareprojectinlayered.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductionDto {

    private String production_id;
    private String inventory_id;
    private String description;
    private String status;

}
