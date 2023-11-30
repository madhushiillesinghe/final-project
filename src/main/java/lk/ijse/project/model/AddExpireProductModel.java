package lk.ijse.project.model;

import lk.ijse.project.dto.AddExpireProductDto;
import lk.ijse.project.DB.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class AddExpireProductModel {
    private final ProductModel promodel=new ProductModel();
    private final ExpireProductModel expromodel=new ExpireProductModel();
    public static boolean SaveAddExpireProduct(AddExpireProductDto dto) throws SQLException {


        boolean result = false;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isExpireproducrSave= ExpireProductModel.saveExpireProduct(dto);

            if (isExpireproducrSave) {

                boolean isUpdated = ProductModel.updateproduct(dto.getTmlist());
                System.out.println(isUpdated);
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
