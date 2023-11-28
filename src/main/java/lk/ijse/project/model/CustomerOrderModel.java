package lk.ijse.project.model;
import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.fp.FpConnection;
import java.sql.*;
import java.util.ArrayList;

public class CustomerOrderModel {
        public static boolean saveCustomerOrder(CustomerOrderDto dto) throws SQLException {
            Connection connection= FpConnection.getInstance().getConnection();
            String sql="INSERT INTO customer_order VALUES(?, ?, ?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1,dto.getCus_order_id());
            pstm.setString(2,dto.getCus_id());
            pstm.setString(3,  dto.getDate());

            return pstm.executeUpdate()>0;
        }

        public static ArrayList<String> getAllOrderIds() throws SQLException {
            Connection connection = FpConnection.getInstance().getConnection();
            String sql="SELECT order_id FROM customer_order ORDER BY LENGTH(order_id),order_id";
            PreparedStatement pstm=connection.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();
            ArrayList<String> list = new ArrayList<>();

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
            return list;
        }

        public static CustomerOrderDto getData(String id) throws SQLException {

            Connection connection = FpConnection.getInstance().getConnection();
            String sql="SELECT * FROM customer_order WHERE order_id=?";
            PreparedStatement pstm=connection.prepareStatement(sql);

            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

           CustomerOrderDto cusOderDto = new CustomerOrderDto();

            if (resultSet.next()) {
                cusOderDto.setCus_order_id(resultSet.getString(1));
                cusOderDto.setCus_id(resultSet.getString(2));
                cusOderDto.setDate(resultSet.getString(3));
            }
            return cusOderDto;
        }
}
