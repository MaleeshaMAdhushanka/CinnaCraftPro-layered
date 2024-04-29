package lk.ijse.Tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerTm {

    //for display table and set values in table
    private String cusId;
    private String firstName;
    private  String lastName;
    private  String address;
    private String email;
    private  String mobileNo;
}
