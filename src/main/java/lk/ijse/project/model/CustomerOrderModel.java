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
    public String genarateNextOrderId() throws SQLException {
        Connection connection=FpConnection.getInstance().getConnection();
        String sql="SELECT order_id FROM customer_order ORDER BY order_id DESC LIMIT 1";
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
            return "O00"+id;
        }
        return"O001";
    }
}
