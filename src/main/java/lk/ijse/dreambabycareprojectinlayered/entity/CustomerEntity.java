package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CustomerEntity {
    private String customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private String orderPlatForm;

}
