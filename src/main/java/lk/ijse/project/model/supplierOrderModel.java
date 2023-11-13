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
        public String genarateNextOrderId() throws SQLException {
            Connection connection=FpConnection.getInstance().getConnection();
            String sql="SELECT sup_order_id FROM supplier_order ORDER BY sup_order_id DESC LIMIT 1";
            ResultSet resultSet=connection.prepareStatement(sql).executeQuery();
            String currentOrderId=null;
            if(resultSet.next()){
                currentOrderId=resultSet.getString(1);
                return splitOrderId(currentOrderId);
            }
            return splitOrderId(null);
        }

        private String splitOrderId(String currentOrderId) {
            if(currentOrderId !=null){
                String[] split=currentOrderId.split("0");
                int id=Integer.parseInt(split[1]);
                id++;
                return "SO00"+id;
            }
            return"SO001";
        }
    }

