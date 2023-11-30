package lk.ijse.project.model;
import lk.ijse.project.dto.SupplyOrderDto;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class SupplierPlaceOrderModel {
    private final SupplierOrderDetailModel suporderdetailmodel=new SupplierOrderDetailModel();
    private final ProductModel promodel=new ProductModel();
    private final SupplierOrderModel supordermodel=new SupplierOrderModel();

    public boolean SavesupplierplaceOrder(SupplyOrderDto dto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = SupplierOrderModel.saveOrder(dto);

            if (isOrderSaved) {

                boolean isUpdated = ProductModel.update(dto.getTmlist());

                if(isUpdated) {
                    boolean isOrderDetailSaved = SupplierOrderDetailModel.save(dto);

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