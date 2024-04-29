package lk.ijse.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDto {
    String SupId;
    String firstName;
    String lastName;
    String address;
    String bank;
    String bankNo;
    String mobileNo;


}
