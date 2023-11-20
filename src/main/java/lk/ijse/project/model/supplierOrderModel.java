package lk.ijse.project.model;

import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.fp.FpConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class supplierOrderModel {
        public static boolean saveOrder(String sup_order_id,String sup_id) throws SQLException {
            Connection connection= FpConnection.getInstance().getConnection();
            String sql="INSERT INTO supplier_order VALUES(?, ?)";
            PreparedStatement pstm=connection.prepareStatement(sql);
            pstm.setString(1,sup_order_id);
            pstm.setString(2,sup_id);

            return pstm.executeUpdate()>0;
        }

    public ArrayList<String> getAllOrderIds() throws SQLException {
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


    /*public static ProductOrderTm getOrder(String order_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier_order WHERE sup_order_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,order_id);

        ResultSet resultSet = pstm.executeQuery();

        ProductOrderTm prootm= null;

        if(resultSet.next()) {
           prootm= new ProductOrderTm();
           resultSet.getString(1);
        }
        return prootm;


    }*/
}

