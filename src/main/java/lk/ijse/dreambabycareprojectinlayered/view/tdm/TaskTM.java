package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class TaskTM implements Comparable<TaskTM> {
    private String task_id;
    private String employee_id;
    private String description;
    private String status;

    @Override
    public int compareTo(TaskTM o) {
        return task_id.compareTo(o.getTask_id());
    }
}
