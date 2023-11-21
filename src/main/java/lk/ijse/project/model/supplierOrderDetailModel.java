package lk.ijse.project.model;

import lk.ijse.project.dto.SupplyOderDto;
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

    public static boolean save(SupplyOderDto dto) throws SQLException {
        String sql = "INSERT INTO supplier_order_detail VALUES (?,?)";

        PreparedStatement statement = FpConnection.getInstance().getConnection().prepareStatement(sql);

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
