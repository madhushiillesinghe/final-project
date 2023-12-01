package lk.ijse.project.model;

import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerOrderDetailModel {

    public static boolean save(CustomerOrderDto dto) throws SQLException {
        String sql = "INSERT INTO order_payment VALUES (?,?)";

        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for (int i = 0; i < dto.getTmlist().size(); i++) {
            statement.setString(1,dto.getTmlist().get(i)[0]);
            statement.setString(2, dto.getCus_order_id());

            int values = statement.executeUpdate();
            System.out.println(values+ " values");

            if (values == 0) {
                return false;
            }
        }
        return true;
    }

}
