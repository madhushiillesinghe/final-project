package lk.ijse.project.model;

import lk.ijse.project.dto.MachineOrderDto;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineRentModel {
    public static boolean saveRent(MachineOrderDto dto) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        String sql="INSERT INTO machine_rent VALUES(?, ?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getCus_rent_id());
        pstm.setString(2,dto.getCus_id());
        pstm.setString(3, dto.getM_id());
        pstm.setString(4,dto.getDate());

        return pstm.executeUpdate()>0;
    }

    public static ArrayList<String> getAllRentIds() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT order_id FROM  machine_rent ORDER BY LENGTH(order_id),order_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static MachineOrderDto getData(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT * FROM machine_rent WHERE order_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        MachineOrderDto mdto = new MachineOrderDto();

        if (resultSet.next()) {
            mdto.setCus_rent_id(resultSet.getString(1));
            mdto.setCus_id(resultSet.getString(2));
            mdto.setM_id(resultSet.getString(3));
            mdto.setDate(resultSet.getString(4));
        }
        return mdto;
    }
}
