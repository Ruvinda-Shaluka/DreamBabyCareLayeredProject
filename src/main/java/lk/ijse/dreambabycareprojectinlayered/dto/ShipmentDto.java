package lk.ijse.dreambabycareprojectinlayered.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class ShipmentDto{

    private String shipment_id;
    private String tracking_number;
    private LocalDate shipment_date;

}
