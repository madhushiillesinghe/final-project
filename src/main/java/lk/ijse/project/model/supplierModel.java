
package lk.ijse.project.model;

import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class supplierModel {
    public boolean saveSupplier(supplierDto supDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, supDto.getSup_id());
        pstm.setInt(2, supDto.getContact_no());
        pstm.setString(3, supDto.getEmp_id());
        pstm.setString(4, supDto.getSupplier_product_type());
        pstm.setString(5, supDto.getEmail());
        pstm.setString(6, supDto.getFirst_name());
        pstm.setString(7, supDto.getLast_name());
        pstm.setInt(8, supDto.getNic());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateSupplier(supplierDto supDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET  contact_no = ?, emp_id = ?, supplier_product_type = ?, email = ?, first_name = ?, last_name = ?, nic = ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setInt(1, supDto.getContact_no());
        pstm.setString(2, supDto.getEmp_id());
        pstm.setString(3, supDto.getSupplier_product_type());
        pstm.setString(4, supDto.getEmail());
        pstm.setString(5, supDto.getFirst_name());
        pstm.setString(6, supDto.getLast_name());
        pstm.setInt(7, supDto.getNic());
        pstm.setString(8, supDto.getSup_id());

        return pstm.executeUpdate() > 0;
    }

    public supplierDto searchSupplier(String sup_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sup_id);

        ResultSet resultSet = pstm.executeQuery();

        supplierDto dto = null;

        if (resultSet.next()) {
            dto = new supplierDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8)
            );
        }
        return dto;
    }

    public boolean deleteSupplier(String sup_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sup_id);

        return pstm.executeUpdate() > 0;
    }

    public List<supplierDto> loadAllSupplier() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<supplierDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new supplierDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getInt(8)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }
}


