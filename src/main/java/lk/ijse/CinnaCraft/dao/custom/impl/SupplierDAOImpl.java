package lk.ijse.CinnaCraft.dao.custom.impl;


import lk.ijse.CinnaCraft.Util.SQLUtil;
import lk.ijse.CinnaCraft.dao.custom.SupplierDAO;
import lk.ijse.CinnaCraft.entity.Supplier;
import net.sf.jasperreports.engine.util.JsonUtil;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SupplierDAOImpl implements SupplierDAO {

  @Override
    public ArrayList<Supplier> getAll() throws SQLException {

       ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM Cinnamon_Supplier");
         ArrayList<Supplier> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            Supplier dto = new Supplier(
                 resultSet.getString("SupID"),
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
    public boolean save(Supplier entity) throws SQLException {


       return  SQLUtil.crudUtil("INSERT INTO Cinnamon_Supplier VALUES(?, ?, ?, ?, ?, ?, ?)",
               entity.getSupID(),
               entity.getFirstName(),
               entity.getLastName(),
               entity.getAddress(),
               entity.getBank(),
               entity.getBankNo(),
               entity.getMobileNo());


    }
    @Override
    public boolean update(Supplier entity) throws SQLException {


        return SQLUtil.crudUtil("UPDATE Cinnamon_Supplier SET SupID = ? , firstName = ? , lastName = ?, address = ?, bank = ?, bankNo = ?, mobileNo = ?  WHERE CusId = ?",

                entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getBank(), entity.getBankNo(), entity.getMobileNo(), entity.getSupID());



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
    public Supplier search(String SupID) throws SQLException {


       ResultSet resultSet = SQLUtil.crudUtil( "SELECT * FROM Cinnamon_Supplier WHERE SupId = ?", SupID);
        Supplier entity = null;

        if (resultSet.next()) {
            entity = new Supplier(
             resultSet.getString("supID"),
             resultSet.getString("firstName"),
             resultSet.getString("lastName"),
             resultSet.getString("address"),
             resultSet.getString("bank"),
             resultSet.getString("bankNo"),
             resultSet.getString("mobileNo")
            );


        }

        return entity;
    }
    @Override
    public String getSupplierName(String SupID) throws SQLException {


        ResultSet resultSet = SQLUtil.crudUtil( "SELECT firstName FROM Cinnamon_Supplier WHERE SupId = ?", SupID);

        String name = null;

        if (resultSet.next()) {
            name = resultSet.getString(1);
        }

        return name;
    }


}
