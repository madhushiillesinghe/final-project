package lk.ijse.project.model;
import lk.ijse.project.dto.PlaceOrderDto;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.tm.supplierorderTm;
import lk.ijse.project.fp.FpConnection;
import lk.ijse.project.dto.supplierorderdetailDto;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierPlaceOrderModel {
    private final supplierOrderDetailModel suporderdetailmodel=new supplierOrderDetailModel();
    private final productModel promodel=new productModel();
    private final supplierOrderModel supordermodel=new supplierOrderModel();
    public  static boolean SavesupplierplaceOrder(PlaceOrderDto prodto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = FpConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = supplierOrderModel.saveOrder(prodto.getOrderId(),prodto.getSupId());
            if (isOrderSaved) {
                boolean isUpdated = productModel.updateProduct((productDto) prodto.getTmlist());
                if(isUpdated) {
                    boolean isOrderDetailSaved = supplierOrderDetailModel.saveSupplierOrder(prodto.getOrderId(), (supplierorderTm) prodto.getTmlist());
                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
