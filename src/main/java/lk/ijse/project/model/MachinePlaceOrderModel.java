package lk.ijse.project.model;

import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.dto.machineOrderDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class MachinePlaceOrderModel {
    private final machineModel mmodel=new machineModel();
    private final MachineRentModel machineRentModel=new MachineRentModel();
    public static boolean SaveCustomerMachineplaceOrder(machineOrderDto dto) throws SQLException {


            boolean result = false;
            Connection connection = null;
            try {
                connection = FpConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                boolean isOrderSaved = MachineRentModel.saveRent(dto);

                if (isOrderSaved) {

                    boolean isUpdated = machineModel.update(dto.getTmlist());


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
