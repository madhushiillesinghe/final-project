package lk.ijse.project.model;

import lk.ijse.project.dto.EmployeeDto;
import lk.ijse.project.dto.tm.EmployeeTm;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeModel {


    public static boolean saveEmployee(EmployeeDto empDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empDto.getEmp_id());
        pstm.setString(2, empDto.getCity());
        pstm.setString(3, empDto.getStreet());
        pstm.setInt(4, empDto.getHouse_no());
        pstm.setInt(5, empDto.getContact_no());
        pstm.setString(6, empDto.getRole());
        pstm.setString(7, empDto.getUser_name());
        pstm.setString(8, empDto.getPassword());
        pstm.setString(9, empDto.getEmail());
        pstm.setString(10, empDto.getFirst_name());
        pstm.setString(11, empDto.getLast_name());
        pstm.setString(12, empDto.getNic());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateEmployee(EmployeeDto empDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET city = ?, street = ?, house_no = ?, contact_no = ?, role = ?, user_name = ?, password = ?, email = ?, first_name = ?, last_name = ?, nic = ? WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, empDto.getCity());
        pstm.setString(2, empDto.getStreet());
        pstm.setInt(3, empDto.getHouse_no());
        pstm.setInt(4, empDto.getContact_no());
        pstm.setString(5, empDto.getRole());
        pstm.setString(6, empDto.getUser_name());
        pstm.setString(7, empDto.getPassword());
        pstm.setString(8, empDto.getEmail());
        pstm.setString(9, empDto.getFirst_name());
        pstm.setString(10, empDto.getLast_name());
        pstm.setString(11, empDto.getNic());
        pstm.setString(12, empDto.getEmp_id());

        return pstm.executeUpdate() > 0;
    }

    public static EmployeeDto searchEmployee(String nicno) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE nic = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, nicno);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            String empid = resultSet.getString(1);
            String city = resultSet.getString(2);
            String street = resultSet.getString(3);
            int house_no = resultSet.getInt(4);
            int contact_no = resultSet.getInt(5);
            String role = resultSet.getString(6);
            String username = resultSet.getString(7);
            String password = resultSet.getString(8);
            String email = resultSet.getString(9);
            String fname = resultSet.getString(10);
            String lname = resultSet.getString(11);
            String nic = resultSet.getString(12);
            dto = new EmployeeDto(empid, city, street, house_no, contact_no, role, username, password, email, fname, lname, nic);

        }
        return dto;
    }

    public static boolean deleteEmployee(String emp_id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "DELETE FROM employee WHERE emp_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, emp_id);

        return pstm.executeUpdate() > 0;
    }

    public static EmployeeTm getEmployee(String empid) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empid);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeTm tm = null;

        if (resultSet.next()) {
            tm = new EmployeeTm(
                    resultSet.getString(1),
                    resultSet.getString(10),
                    resultSet.getString(6),
                    resultSet.getString(9)
            );
        }
        return tm;


    }

    public static EmployeeDto getemployee(String empid) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, empid);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto = null;

        if (resultSet.next()) {
            dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(3),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );
        }
        return dto;


    }

    public ArrayList<String> getAllEmployeeId() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT emp_id FROM employee ORDER BY LENGTH(emp_id),emp_id";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
    public ArrayList<String> getAllEmployeeNic() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT nic FROM employee ORDER BY LENGTH(nic),nic";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static List<EmployeeDto> loadAllEmployee() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<EmployeeDto> dtoList = new ArrayList<>();
        while (resultSet.next()) {
            var dto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(3),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12)
            );

            dtoList.add(dto);
        }
        return dtoList;
    }

    public String checkUsernameAndPassword(String userName, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT role FROM employee WHERE user_name=? AND  password=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, userName);
        pstm.setString(2, password);

        ResultSet set = pstm.executeQuery();

        if (set.next()) {
            String role = set.getString(1);
            return role;
        }
        return "no";
    }
    public String checkUsernameForForget(String userName) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT role FROM employee WHERE user_name=?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, userName);


        ResultSet set = pstm.executeQuery();

        if (set.next()) {
            String role= set.getString(1);
            return role;
        }
        return "no";
    }

    public  static int dashboardEmployeeCount() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT COUNT(emp_id) FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet set = pstm.executeQuery();
        if (set.next()) {
            int count = set.getInt(1);
            return count;
        }
        return  0;
    }
}
