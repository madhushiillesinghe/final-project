package lk.ijse.project.model;

import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.dto.tm.supplierorderTm;
import lk.ijse.project.fp.FpConnection;
import lk.ijse.project.dto.supplierorderdetailDto;

import java.sql.*;
import java.util.List;

public class supplierOrderDetailModel {
    public boolean savesupplierOrderDetail(String orderId, List<supplierorderTm> tmList) throws SQLException {
        for (supplierorderTm carttm : tmList) {
            if(!savesupplierOrderDetail(orderId, (List<supplierorderTm>) carttm)) {
                return false;
            }
        }
        return true;
    }
    public static boolean saveSupplierOrder(String orderId,supplierorderTm supotm) throws SQLException {
        Connection connection= FpConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier_order_detail VALUES(?, ?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,supotm.getCode());
        pstm.setString(2,orderId);
        pstm.setInt(3,supotm.getQty());
        pstm.setDouble(4,supotm.getPrice());

        return pstm.executeUpdate()>0;
    }

}
