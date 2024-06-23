package lk.ijse.CinnaCraft.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Employee {
    private String empID;
    private String firstName;
    private String lastName;
    private String address;
    private String sex;
    private String mobileNo;


}
