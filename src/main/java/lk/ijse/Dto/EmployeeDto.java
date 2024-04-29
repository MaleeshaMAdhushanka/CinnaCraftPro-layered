package lk.ijse.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeDto {
    private String empID;
    private String firstName;
    private String lastName;
    private String address;
    private String sex;
    private String mobileNo;


}
