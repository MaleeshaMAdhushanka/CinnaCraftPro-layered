package lk.ijse.CinnaCraft.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String cusId;
    private String firstName;
    private  String lastName;
    private  String address;
    private String email;
    private  String mobileNo;

    public CustomerDto(String fertilizerID, String brand, String description, String size, double price, int qty) {
    }
}