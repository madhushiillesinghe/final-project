package lk.ijse.project.model;

import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerPlaceOrderModel {
    private final customerOrderDetailModel cusorderdetailmodel=new customerOrderDetailModel();
    private final ProductModel promodel=new ProductModel();
    private final CustomerOrderModel cusordermodel=new CustomerOrderModel();

    public boolean SaveCustomerplaceOrder(CustomerOrderDto dto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        boolean isUpdatedproduct=false;
        boolean isUpdaetedMachine=false;
        try {
            connection = FpConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = CustomerOrderModel.saveCustomerOrder(dto);

            if (isOrderSaved) {
                if(dto.getCus_order_id()!=null) {
                    isUpdatedproduct = ProductModel.updateproduct(dto.getTmlist());
                    if(dto.getM_id()==null){
                        isUpdaetedMachine=machineModel.update(dto.getTmlist());
                    }
                }
                isUpdaetedMachine=machineModel.update(dto.getTmlist());

                if(isUpdatedproduct|isUpdaetedMachine) {
                    boolean isOrderDetailSaved = customerOrderDetailModel.save(dto);

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