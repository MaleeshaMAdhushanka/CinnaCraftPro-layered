package lk.ijse.CinnaCraft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class User {
    private String username;
    private String password;
    private String email;
}
