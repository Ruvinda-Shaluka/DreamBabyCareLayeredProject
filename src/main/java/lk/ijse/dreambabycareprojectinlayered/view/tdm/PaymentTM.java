package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PaymentTM implements Comparable<PaymentTM> {
    private String payment_id;
    private String order_id;
    private double amount;
    private String payment_method;

    @Override
    public int compareTo(PaymentTM o) {
        return payment_id.compareTo(o.getPayment_id());
    }
}
