package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiscountEntity {

    private  String discount_id;
    private  String payment_id;
    private  String discount_type;
    private  double discount_percentage;

}
