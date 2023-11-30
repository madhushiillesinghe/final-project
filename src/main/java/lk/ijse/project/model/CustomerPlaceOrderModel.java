package lk.ijse.project.model;

import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerPlaceOrderModel {
    private final CustomerOrderDetailModel cusorderdetailmodel=new CustomerOrderDetailModel();
    private final ProductModel promodel=new ProductModel();
    private final CustomerOrderModel cusordermodel=new CustomerOrderModel();

    public boolean SaveCustomerplaceOrder(CustomerOrderDto dto) throws SQLException {
        boolean result = false;
        Connection connection = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = CustomerOrderModel.saveCustomerOrder(dto);

            if (isOrderSaved) {
                boolean isUpdated = ProductModel.updateproduct(dto.getTmlist());

                if(isUpdated) {
                    boolean isOrderDetailSaved = CustomerOrderDetailModel.save(dto);

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