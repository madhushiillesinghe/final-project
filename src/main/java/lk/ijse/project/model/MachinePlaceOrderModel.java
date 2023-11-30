package lk.ijse.project.model;

import lk.ijse.project.dto.MachineOrderDto;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MachinePlaceOrderModel {
    private final MachineModel mmodel=new MachineModel();
    private final MachineRentModel machineRentModel=new MachineRentModel();
    public static boolean SaveCustomerMachineplaceOrder(MachineOrderDto dto) throws SQLException {


            boolean result = false;
            Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = MachineRentModel.saveRent(dto);

                if (isOrderSaved) {

                    boolean isUpdated = MachineModel.update(dto.getTmlist());


                        if(isUpdated) {
                            connection.commit();
                            result = true;
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
