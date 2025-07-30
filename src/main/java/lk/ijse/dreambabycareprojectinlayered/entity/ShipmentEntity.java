package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ShipmentEntity {

    private String shipment_id;
    private String tracking_number;
    private LocalDate shipment_date;

}
