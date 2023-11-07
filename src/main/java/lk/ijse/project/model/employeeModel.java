package lk.ijse.project.model;

import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class employeeModel {
    public boolean saveEmployee(employeeDto empDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empDto.getEmp_id());
        pstm.setString(2, empDto.getCity());
        pstm.setString(3,empDto.getStreet());
        pstm.setInt(4, empDto.getHouse_no());
        pstm.setInt(5, empDto.getContact_no());
        pstm.setString(6, empDto.getRole());
        pstm.setString(7, empDto.getUser_name());
        pstm.setString(8, empDto.getPassword());
        pstm.setString(9,empDto.getEmail());
        pstm.setString(10, empDto.getFirst_name());
        pstm.setString(11,empDto.getLast_name());
        pstm.setInt(12, empDto.getNic());

        return pstm.executeUpdate() > 0;
    }

    public boolean updateEmployee(employeeDto empDto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET city = ?, street = ?, house_no = ?, contact_no = ?, role = ?, email = ?, first_name = ?, last_name = ?, nic = ? WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empDto.getCity());
        pstm.setString(2,empDto.getStreet());
        pstm.setInt(3, empDto.getHouse_no());
        pstm.setInt(4, empDto.getContact_no());
        pstm.setString(5, empDto.getRole());
        pstm.setString(6,empDto.getEmail());
        pstm.setString(9, empDto.getFirst_name());
        pstm.setString(10,empDto.getLast_name());
        pstm.setInt(11, empDto.getNic());
        pstm.setString(12, empDto.getEmp_id());

        return pstm.executeUpdate() > 0;
    }

    public employeeDto searchEmployee( String emp_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,emp_id);

        ResultSet resultSet = pstm.executeQuery();

        employeeDto dto = null;

        if(resultSet.next()) {
            dto = new employeeDto();
                    dto.setEmp_id(resultSet.getString(1));
                    dto.setCity(resultSet.getString(2));
                    dto.setStreet(resultSet.getString(3));
                    dto.setHouse_no(resultSet.getInt(4));
                    dto.setContact_no( resultSet.getInt(5));
                    dto.setRole(resultSet.getString(6));
                    dto.setEmail( resultSet.getString(9));
                    dto.setFirst_name(resultSet.getString(10));
                    dto.setLast_name(resultSet.getString(11));
                    dto.setNic(resultSet.getInt(12));
        }
        return dto;
    }

    public boolean deleteEmployee(String emp_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, emp_id);

        return pstm.executeUpdate() > 0;
    }

    public List<employeeDto> loadAllEmployee() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<employeeDto> dtoList = new ArrayList<>();
        var dto = new employeeDto();
        while (resultSet.next()) {
            dto.setEmp_id(resultSet.getString(1));
            dto.setCity(resultSet.getString(2));
            dto.setStreet(resultSet.getString(3));
            dto.setHouse_no(resultSet.getInt(4));
            dto.setContact_no( resultSet.getInt(5));
            dto.setRole(resultSet.getString(6));
            dto.setEmail( resultSet.getString(9));
            dto.setFirst_name(resultSet.getString(10));
            dto.setLast_name(resultSet.getString(11));
            dto.setNic(resultSet.getInt(12));


            dtoList.add(dto);
        }
 dto = new employeeDto();
        return dtoList;
    }

}
