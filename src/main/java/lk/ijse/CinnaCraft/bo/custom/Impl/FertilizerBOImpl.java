package lk.ijse.CinnaCraft.bo.custom.Impl;

import lk.ijse.CinnaCraft.Dto.FertilizerDto;
import lk.ijse.CinnaCraft.Tm.FertilizeSalesCartTm;
import lk.ijse.CinnaCraft.bo.custom.FertilizerBO;
import lk.ijse.CinnaCraft.dao.custom.FertilizerDAO;
import lk.ijse.CinnaCraft.dao.custom.impl.FertilizerDAOImpl;
import lk.ijse.CinnaCraft.entity.Fertilizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerBOImpl implements FertilizerBO {

    FertilizerDAO fertilizerDAO = new FertilizerDAOImpl();

    @Override
    public String generateNextFertilizerId() throws SQLException {
        return fertilizerDAO.generateId();
    }

    @Override
    public boolean saveFertilizer(FertilizerDto dto) throws SQLException {
        return fertilizerDAO.save(new Fertilizer(
                dto.getFertilizerID(),
                dto.getBrand(),
                dto.getDescription(),
                dto.getSize(),
                dto.getPrice(),
                dto.getQty()
        ));
    }

    @Override
    public List<FertilizerDto> getAllFertilizer() throws SQLException {
        List<Fertilizer> fertilizerList = fertilizerDAO.getAll();
        List<FertilizerDto> dtoList = new ArrayList<>();
        for (Fertilizer fertilizer: fertilizerList) {
            dtoList.add(new FertilizerDto(
                    fertilizer.getFertilizerID(),
                    fertilizer.getBrand(),
                    fertilizer.getDescription(),
                    fertilizer.getSize(),
                    fertilizer.getPrice(),
                    fertilizer.getQty()
            ));
        }
        return dtoList;
    }

    @Override
    public FertilizerDto getFertilizer(String fertilizerId) throws SQLException {
      Fertilizer fertilizer = fertilizerDAO.search(fertilizerId);
        if (fertilizer != null) {
            return new FertilizerDto(
                    fertilizer.getFertilizerID(),
                    fertilizer.getBrand(),
                    fertilizer.getDescription(),
                    fertilizer.getSize(),
                    fertilizer.getPrice(),
                    fertilizer.getQty()
            );
        }
        return null;
    }

    @Override
    public boolean deleteFertilizer(String fertilizerId) throws SQLException {
        return fertilizerDAO.delete(fertilizerId);
    }

    @Override
    public boolean updateFertilizer(FertilizerDto dto) throws SQLException {
        return fertilizerDAO.update(new Fertilizer(
                dto.getFertilizerID(),
                dto.getBrand(),
                dto.getDescription(),
                dto.getSize(),
                dto.getPrice(),
                dto.getQty()
        ));
    }

    @Override
    public FertilizerDto searchFertilizer(String fertilizerId) throws SQLException {

       Fertilizer fertilizer =  fertilizerDAO.search(fertilizerId);
        if (fertilizer != null) {
            return new FertilizerDto(
                    fertilizer.getFertilizerID(),
                    fertilizer.getBrand(),
                    fertilizer.getDescription(),
                    fertilizer.getSize(),
                    fertilizer.getPrice(),
                    fertilizer.getQty()
            );
        }
        return  null;
    }

    @Override
    public boolean updateFertilizer(List<FertilizeSalesCartTm> tmList) throws SQLException {
        return fertilizerDAO.updateFertilizer(tmList);
    }

    @Override
    public boolean updateQty(FertilizeSalesCartTm cartTm) throws SQLException {
        return fertilizerDAO.updateQty(cartTm);
    }
}
