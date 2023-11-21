package lk.ijse.project.model;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierPlaceOrderModel {
    private final supplierOrderDetailModel suporderdetailmodel=new supplierOrderDetailModel();
    private final ProductModel promodel=new ProductModel();
    private final SupplierOrderModel supordermodel=new SupplierOrderModel();

    public boolean SavesupplierplaceOrder(SupplyOderDto dto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = FpConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = SupplierOrderModel.saveOrder(dto);

            if (isOrderSaved) {

                boolean isUpdated = ProductModel.update(dto.getTmlist());

                if(isUpdated) {
                    boolean isOrderDetailSaved = supplierOrderDetailModel.save(dto);

                    if(isOrderDetailSaved) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
