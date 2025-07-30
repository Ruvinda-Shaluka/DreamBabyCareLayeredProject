package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaymentEntity {

    private String payment_id;
    private String order_id;
    private double amount;
    private String payment_method;

}
