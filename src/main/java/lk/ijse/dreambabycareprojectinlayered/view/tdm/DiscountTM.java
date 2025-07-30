package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiscountTM implements Comparable<DiscountTM> {
    private  String discount_id;
    private  String payment_id;
    private  String discount_type;
    private  double discount_percentage;

    @Override
    public int compareTo(DiscountTM o) {
        return discount_id.compareTo(o.getDiscount_id());
    }
}
