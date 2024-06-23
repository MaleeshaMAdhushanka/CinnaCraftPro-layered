package lk.ijse.CinnaCraft.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class UserDto {
    private String username;
    private String password;
    private String email;
}
