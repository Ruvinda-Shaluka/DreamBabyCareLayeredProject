package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductionTaskTM implements Comparable<ProductionTaskTM> {
    private String production_task_id;
    private String production_id;
    private String task_id;

    @Override
    public int compareTo(ProductionTaskTM o) {
        return production_task_id.compareTo(o.getProduction_task_id());
    }
}
