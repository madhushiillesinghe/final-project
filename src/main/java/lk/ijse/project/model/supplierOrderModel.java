package lk.ijse.project.model;

import lk.ijse.project.fp.FpConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class supplierOrderModel {
        public static boolean saveOrder(String sup_order_id,String sup_id) throws SQLException {
            Connection connection= FpConnection.getInstance().getConnection();
            String sql="INSERT INTO supplier_order VALUES(?, ?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1,sup_order_id);
            pstm.setString(2,sup_id);

            return pstm.executeUpdate()>0;
        }
    }

