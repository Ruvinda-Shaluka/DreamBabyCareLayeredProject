package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ShipmentTM implements Comparable<ShipmentTM> {
    private String shipment_id;
    private String tracking_number;
    private LocalDate shipment_date;

    @Override
    public int compareTo(ShipmentTM o) {
        return shipment_id.compareTo(o.getShipment_id());
    }
}
