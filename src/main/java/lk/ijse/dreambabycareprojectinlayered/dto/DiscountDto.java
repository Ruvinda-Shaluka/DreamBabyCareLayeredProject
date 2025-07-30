package lk.ijse.dreambabycareprojectinlayered.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiscountDto {

    private  String discount_id;
    private  String payment_id;
    private  String discount_type;
    private  double discount_percentage;

}
