package lk.ijse.dreambabycareprojectinlayered.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class TaskEntity {

    private String task_id;
    private String employee_id;
    private String description;
    private String status;

}
