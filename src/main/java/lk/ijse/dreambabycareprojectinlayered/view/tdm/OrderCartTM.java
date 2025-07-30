package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderCartTM implements Comparable<OrderCartTM> {
    private String customerId;
    private String itemId;
    private String itemName;
    private int cartQty;
    private double unitPrice;
    private double total;
    private String paymentMethod;
    private Button btnRemove;

    @Override
    public int compareTo(OrderCartTM o) {
        return customerId.compareTo(o.getCustomerId());
    }
}
