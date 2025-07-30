package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MaterialTM implements Comparable<MaterialTM> {
    private  String material_id;
    private  String name;
    private  String color_type;
    private int quantity;

    @Override
    public int compareTo(MaterialTM o) {
        return material_id.compareTo(o.getMaterial_id());
    }
}
