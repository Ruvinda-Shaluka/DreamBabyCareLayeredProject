package lk.ijse.dreambabycareprojectinlayered.view.tdm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserTM implements Comparable<UserTM> {
    private String user_id;
    private  String user_name;
    private  String password;
    private  String email;
    private  String name;
    private  String phone_number;

    @Override
    public int compareTo(UserTM o) {
        return user_id.compareTo(o.getUser_id());
    }
}
