package lk.ijse.project.model;

import lk.ijse.project.dto.SupplyOrderDto;
import lk.ijse.project.dto.tm.SupplierOrderTm;
import lk.ijse.project.DB.DBConnection;

import java.sql.*;
import java.util.List;

public class SupplierOrderDetailModel {
    public boolean savesupplierOrderDetail(String orderId, List<SupplierOrderTm> tmList) throws SQLException {
        for (SupplierOrderTm carttm : tmList) {
            if(!savesupplierOrderDetail(orderId, (List<SupplierOrderTm>) carttm)) {
                return false;
            }
        }
        return true;
    }
    public static boolean saveSupplierOrder(String orderId, SupplierOrderTm supotm) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier_order_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,supotm.getCode());
        pstm.setString(2,orderId);
        pstm.setInt(3,supotm.getQty());
        pstm.setDouble(4,supotm.getPrice());

        return pstm.executeUpdate()>0;
    }

    public static boolean save(SupplyOrderDto dto) throws SQLException {
        String sql = "INSERT INTO supplier_order_detail VALUES (?,?)";

        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < dto.getTmlist().size(); i++) {
            statement.setString(1,dto.getTmlist().get(i)[0]);
            statement.setString(2, dto.getSup_order_id());

            int values = statement.executeUpdate();
            System.out.println(values+ " values");

            if (values == 0) {
                return false;
            }
        }
        return true;
    }

}
