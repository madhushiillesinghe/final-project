package lk.ijse.project.model;

import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class customerOrderDetailModel {

    public static boolean saveCustomerOrder(String orderId, ProductOrderTm prootm) throws SQLException {
        Connection connection= FpConnection.getInstance().getConnection();
        String sql="INSERT INTO order_payment VALUES(?, ?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,prootm.getCode());
        pstm.setString(2,orderId);
        pstm.setInt(3,prootm.getQty());
        pstm.setDouble(4,prootm.getPrice());

        return pstm.executeUpdate()>0;
    }

    public static boolean save(CustomerOrderDto dto) throws SQLException {
        String sql = "INSERT INTO order_payment VALUES (?,?)";

        PreparedStatement statement = FpConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < dto.getTmlist().size(); i++) {
            statement.setString(1,dto.getTmlist().get(i)[0]);
            statement.setString(2, dto.getCus_order_id());

            int values = statement.executeUpdate();
            System.out.println(values+ " values");

            if (values == 0) {
                return false;
            }
        }
        return true;
    }

}
