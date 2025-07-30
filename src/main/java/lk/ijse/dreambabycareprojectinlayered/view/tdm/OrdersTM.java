package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrdersTM implements Comparable<OrdersTM> {
    private String order_id;
    private LocalDate order_date;
    private String customer_id;
    private String shipment_id;
    private String status;

    @Override
    public int compareTo(OrdersTM o) {
        return order_id.compareTo(o.getOrder_id());
    }
}
