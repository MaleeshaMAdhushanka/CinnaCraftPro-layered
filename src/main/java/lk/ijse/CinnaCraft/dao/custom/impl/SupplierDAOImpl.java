package lk.ijse.CinnaCraft.dao.custom.impl;


import lk.ijse.CinnaCraft.Dto.SupplierDto;
import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.SupplierDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAOImpl implements SupplierDAO {

  @Override
    public ArrayList<SupplierDto> getAll() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM Cinnamon_Supplier");

         ArrayList<SupplierDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            SupplierDto dto = new SupplierDto(
             resultSet.getString("SupId"),
             resultSet.getString("firstName"),
             resultSet.getString("lastName"),
             resultSet.getString("address"),
             resultSet.getString("bank"),
              resultSet.getString("bankNo"),
              resultSet.getString("mobileNo")
            );
          dtoList.add(dto);

        }

        return dtoList;
    }

     @Override
    public boolean save(SupplierDto dto) throws SQLException {


       return  SQLUtil.crudUtil("INSERT INTO Cinnamon_Supplier VALUES(?, ?, ?, ?, ?, ?, ?)",
               dto.getSupId(),
               dto.getFirstName(),
               dto.getLastName(),
               dto.getAddress(),
               dto.getBank(),
               dto.getBankNo(),
               dto.getMobileNo());


    }
    @Override
    public boolean update(SupplierDto dto) throws SQLException {


        return SQLUtil.crudUtil("UPDATE Cinnamon_Supplier SET SupID = ? , firstName = ? , lastName = ?, address = ?, bank = ?, bankNo = ?, mobileNo = ?  WHERE CusId = ?",

                dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getBank(), dto.getBankNo(), dto.getMobileNo(), dto.getSupId());



    }

    @Override
    public boolean exit(String id) throws SQLException {
        return false;
    }
    @Override
    public  boolean delete(String id) throws SQLException {

      return  SQLUtil.crudUtil("DELETE FROM Cinnamon_Supplier WHERE SupId = ?", id);

    }

    @Override
    public String generateId() throws SQLException {

        ResultSet resultSet = SQLUtil.crudUtil("SELECT SupId FROM Cinnamon_Supplier  ORDER BY SupId DESC LIMIT 1");

        String currentSupplierId = null;

        if (resultSet.next()) {
            currentSupplierId = resultSet.getString(1);
            return splitSupplierId(currentSupplierId);
        }

        return splitSupplierId(currentSupplierId);

    }


    private String splitSupplierId(String currentSupplierId) {

        if (currentSupplierId != null) {
            String[] split = currentSupplierId.split("S");
            int selectedId = Integer.parseInt(split[1]);

            if (selectedId < 9) {
                selectedId++;
                return "S00" + selectedId;
            } else if (selectedId < 99) {
                selectedId++;
                return "S0" + selectedId;
            } else {
                selectedId++;
                return "S" + selectedId;
            }
        }
        return "S001";
    }





    @Override
    public SupplierDto search(String supId) throws SQLException {


       ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM Cinnamon_Supplier WHERE SupId = ?", supId);
        SupplierDto dto = null;

        if (resultSet.next()) {
            dto = new SupplierDto(
             resultSet.getString("supID"),
             resultSet.getString("firstName"),
             resultSet.getString("lastName"),
             resultSet.getString("address"),
             resultSet.getString("bank"),
             resultSet.getString("bankNo"),
             resultSet.getString("mobileNo")
            );


        }

        return dto;
    }
    @Override
    public String getSupplierName(String supId) throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil( "SELECT firstName FROM Cinnamon_Supplier WHERE SupId = ?", supId);

        String name = null;

        if (resultSet.next()) {
            name = resultSet.getString(1);
        }

        return name;
    }


}
