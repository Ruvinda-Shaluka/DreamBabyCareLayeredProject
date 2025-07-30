package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SupplierTM implements Comparable<SupplierTM> {
    private String supplier_id;
    private String name;
    private String contact;
    private String account_details;

    @Override
    public int compareTo(SupplierTM o) {
        return supplier_id.compareTo(o.getSupplier_id());
    }
}
