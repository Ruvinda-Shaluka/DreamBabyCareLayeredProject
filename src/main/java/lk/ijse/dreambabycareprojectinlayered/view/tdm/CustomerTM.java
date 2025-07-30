package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CustomerTM implements Comparable<CustomerTM> {
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String orderPlatForm;

    @Override
    public int compareTo(CustomerTM o) {
        return customerId.compareTo(o.getCustomerId());
    }
}
