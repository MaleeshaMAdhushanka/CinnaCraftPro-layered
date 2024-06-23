package lk.ijse.CinnaCraft.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String cusId;
    private String firstName;
    private  String lastName;
    private  String address;
    private String email;
    private  String mobileNo;

    public Customer(String fertilizerID, String brand, String description, String size, double price, int qty) {
    }
}