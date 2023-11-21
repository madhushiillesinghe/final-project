
package lk.ijse.project.model;

import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.fp.FpConnection;
import lk.ijse.project.dto.tm.customerTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class customerModel {
    public static boolean saveCustomer(final customerDto cusDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, cusDto.getCus_id());
        pstm.setString(2, cusDto.getCity());
        pstm.setString(3,cusDto.getStreet());
        pstm.setInt(4, cusDto.getHouse_no());
        pstm.setInt(5, cusDto.getContact_no());
        pstm.setString(6, cusDto.getEmp_id());
        pstm.setString(7, cusDto.getAccount_type());
        pstm.setString(8,cusDto.getEmail());
        pstm.setString(9, cusDto.getFirst_name());
        pstm.setString(10,cusDto.getLast_name());
        pstm.setInt(11, cusDto.getNic());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateCustomer(customerDto cusDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET city = ?, street = ?, house_no = ?, contact_no = ?, emp_id = ?, account_type = ?, email = ?, first_name = ?, last_name = ?, nic = ? WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, cusDto.getCity());
        pstm.setString(2,cusDto.getStreet());
        pstm.setInt(3, cusDto.getHouse_no());
        pstm.setInt(4, cusDto.getContact_no());
        pstm.setString(5, cusDto.getEmp_id());
        pstm.setString(6, cusDto.getAccount_type());
        pstm.setString(7,cusDto.getEmail());
        pstm.setString(8, cusDto.getFirst_name());
        pstm.setString(9,cusDto.getLast_name());
        pstm.setInt(10, cusDto.getNic());
        pstm.setString(11, cusDto.getCus_id());

        return pstm.executeUpdate() > 0;
    }

    public static customerDto searchCustomer(String cusid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cusid);

        ResultSet resultSet = pstm.executeQuery();

        customerDto dto = null;

        if(resultSet.next()) {
            dto = new customerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getInt(11)
            );
        }
        return dto;
    }

    public static boolean deleteCustomer(String cus_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cus_id);

        return pstm.executeUpdate() > 0;
    }

    public static customerDto getData(String cusId) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cusId);

        ResultSet resultSet = pstm.executeQuery();

       customerDto dto = null;

        if(resultSet.next()) {
            dto = new customerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getInt(11)
            );
        }
        return dto;
    }

    public List<customerDto> loadAllCustomer() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<customerDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new customerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getInt(11)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }

    public static customerTm getCustomer(String cusid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,cusid);

        ResultSet resultSet = pstm.executeQuery();

        customerTm tm = null;

        if(resultSet.next()) {
            tm = new customerTm(
                    resultSet.getString(1),
                    resultSet.getString(9),
                    resultSet.getString(7),
                    resultSet.getString(8)
            );
        }
        return tm;
    }
    public static customerDto getCustomerdto(String cusid) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, cusid);

        ResultSet resultSet = pstm.executeQuery();

        customerDto dto = null;

        if (resultSet.next()) {
            dto= new customerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getInt(11)
            );
        }
        return dto;
    }

        public ArrayList<String> getAllCustomerId() throws SQLException{
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT cus_id FROM customer ORDER BY LENGTH(cus_id),cus_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
}