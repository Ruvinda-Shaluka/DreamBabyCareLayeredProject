package lk.ijse.dreambabycareprojectinlayered.dto;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrdersDto{

    private String order_id;
    private LocalDate order_date;
    private String customer_id;
    private String shipment_id;
    private String status;

}
