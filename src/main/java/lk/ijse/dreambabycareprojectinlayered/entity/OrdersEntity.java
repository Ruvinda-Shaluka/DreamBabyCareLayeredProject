package lk.ijse.dreambabycareprojectinlayered.entity;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrdersEntity {

    private String order_id;
    private LocalDate order_date;
    private String customer_id;
    private String shipment_id;
    private String status;

}
