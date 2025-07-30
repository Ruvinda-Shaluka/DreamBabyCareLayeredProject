package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeTM implements Comparable<EmployeeTM> {
    private  String employee_id;
    private  String name;
    private String nic;
    private String number;
    private String role;

    @Override
    public int compareTo(EmployeeTM o) {
        return employee_id.compareTo(o.getEmployee_id());
    }
}
