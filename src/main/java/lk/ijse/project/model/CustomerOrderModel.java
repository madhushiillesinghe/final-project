package lk.ijse.project.model;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.fp.FpConnection;
import java.sql.*;
import java.util.ArrayList;

public class CustomerOrderModel {
    public static boolean saveCustomerOrder(SupplyOderDto dto) throws SQLException {
        Connection connection= FpConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier_order VALUES(?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getSup_order_id());
        pstm.setString(2,dto.getSup_id());
        pstm.setDate(3, (Date) dto.getDate());

        return pstm.executeUpdate()>0;
    }

    public static ArrayList<String> getAllOrderIds() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT sup_order_id FROM supplier_order ORDER BY LENGTH(sup_order_id),sup_order_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static SupplyOderDto getData(String id) throws SQLException {

        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT * FROM supplier_order WHERE sup_order_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        SupplyOderDto supplyOderDto = new SupplyOderDto();

        if (resultSet.next()) {
            supplyOderDto.setSup_order_id(resultSet.getString(1));
            supplyOderDto.setSup_id(resultSet.getString(2));
            supplyOderDto.setDate(resultSet.getDate(3));
        }
        return supplyOderDto;
    }

}
