package lk.ijse.project.model;
import lk.ijse.project.fp.FpConnection;
import java.sql.*;
public class CustomerOrderModel {
    public static boolean saveOrder(String order_id,String cus_id) throws SQLException {
        Connection connection=FpConnection.getInstance().getConnection();
        String sql="INSERT INTO customer_order VALUES(?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,order_id);
        pstm.setString(2,cus_id);

        return pstm.executeUpdate()>0;
    }
}
