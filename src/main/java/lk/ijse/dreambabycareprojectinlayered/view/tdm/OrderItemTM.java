package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class OrderItemTM implements Comparable<OrderItemTM> {

        private String order_item_id;
        private String order_id;
        private String inventory_id;
        private int quantity;
        private double amount;

        @Override
        public int compareTo(OrderItemTM o) {
                return order_item_id.compareTo(o.getOrder_item_id());
        }
}

