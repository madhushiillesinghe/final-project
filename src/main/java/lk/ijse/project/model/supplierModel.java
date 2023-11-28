package lk.ijse.project.model;

import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project.dto.tm.supplierTm;

public class supplierModel {
    public static boolean saveSupplier(supplierDto supDto) throws SQLException {
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
        pstm.setString(8, supDto.getNic());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateSupplier(supplierDto supDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET  contact_no = ?, emp_id = ?, supplier_product_type = ?, email = ?, first_name = ?, last_name = ?, nic = ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setInt(1, supDto.getContact_no());
        pstm.setString(2, supDto.getEmp_id());
        pstm.setString(3, supDto.getSupplier_product_type());
        pstm.setString(4, supDto.getEmail());
        pstm.setString(5, supDto.getFirst_name());
        pstm.setString(6, supDto.getLast_name());
        pstm.setString(7, supDto.getNic());
        pstm.setString(8, supDto.getSup_id());

        return pstm.executeUpdate() > 0;
    }

    public static supplierDto searchSupplier(String nicno) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE nic = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, nicno);

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
                    resultSet.getString(8)
            );
        }
        return dto;
    }

    public static boolean deleteSupplier(String sup_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, sup_id);

        return pstm.executeUpdate() > 0;
    }

    public static List<supplierDto> loadAllSupplier() throws SQLException {
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
                    resultSet.getString(8)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }

    public static supplierTm getSupplier(String supid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,supid);

        ResultSet resultSet = pstm.executeQuery();

        supplierTm tm = null;

        if(resultSet.next()) {
            tm = new supplierTm(
                    resultSet.getString(1),
                    resultSet.getString(6),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return tm;


    }
    public static supplierDto getSupplierDto(String supid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,supid);

        ResultSet resultSet = pstm.executeQuery();

        supplierDto dto = null;

        if(resultSet.next()) {
            dto = new supplierDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return dto;
    }

    public static supplierDto getData(String supid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,supid);

        ResultSet resultSet = pstm.executeQuery();

        supplierDto dto = null;

        if(resultSet.next()) {
            dto = new supplierDto(
                    resultSet.getString(1),
                    resultSet.getInt(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return dto;
    }


    public ArrayList<String> getAllSupplierId() throws SQLException{
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT sup_id FROM supplier ORDER BY LENGTH(sup_id),sup_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public ArrayList<String> getAllSupplierNic() throws SQLException {

        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT nic FROM supplier ORDER BY LENGTH(nic),nic";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
