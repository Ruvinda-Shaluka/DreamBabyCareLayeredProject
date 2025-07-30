package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductionTM implements Comparable<ProductionTM> {
    private String production_id;
    private String inventory_id;
    private String description;
    private String status;

    @Override
    public int compareTo(ProductionTM o) {
        return production_id.compareTo(o.getProduction_id());
    }
}
