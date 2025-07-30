package lk.ijse.dreambabycareprojectinlayered.view.tdm;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class MaterialUsageTM implements Comparable<MaterialUsageTM> {
    private String usage_id;
    private String production_id;
    private String material_id;
    private int quantity_used;

    @Override
    public int compareTo(MaterialUsageTM o) {
        return usage_id.compareTo(o.getUsage_id());
    }
}
