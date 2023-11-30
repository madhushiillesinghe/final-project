package lk.ijse.project.model;

import lk.ijse.project.dto.AddExpireProductDto;
import lk.ijse.project.dto.ExpireProductDto;
import lk.ijse.project.dto.tm.ExpireProductTm;
import lk.ijse.project.DB.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpireProductModel {
    public static boolean saveExpireProduct(AddExpireProductDto exprodto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO expired_product VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, exprodto.getP_code());
        pstm.setString(2, exprodto.getDescription());
        pstm.setInt(3,exprodto.getCount());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateexpireProduct(ExpireProductDto exprodto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "UPDATE expired_product SET  description = ?, expired_product_count = ? WHERE p_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, exprodto.getDescription());
        pstm.setInt(2, exprodto.getCount());
        pstm.setString(3, exprodto.getP_code());



        return pstm.executeUpdate() > 0;
    }



    public ExpireProductDto searchExpireProduct(String p_code) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM expired_product WHERE p_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, p_code);

        ResultSet resultSet = pstm.executeQuery();

        ExpireProductDto dto = null;

        if (resultSet.next()) {
            dto = new ExpireProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return dto;
    }

    public static boolean deleteExpireProductProduct(String p_code) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "DELETE FROM expired_product WHERE p_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, p_code);

        return pstm.executeUpdate() > 0;
    }

    public List<ExpireProductDto> loadAllExpireProduct() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM expired_product";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ExpireProductDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new ExpireProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }
    public static ExpireProductTm getExpireProduct(String pCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM expired_product WHERE p_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,pCode);

        ResultSet resultSet = pstm.executeQuery();

       ExpireProductTm exprotm = null;

        if(resultSet.next()) {
            exprotm = new ExpireProductTm(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return exprotm;
    }
    public static ExpireProductDto getExpireProductDto(String pCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM expired_product WHERE p_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,pCode);

        ResultSet resultSet = pstm.executeQuery();

        ExpireProductDto exprodto= null;

        if(resultSet.next()) {
            exprodto = new ExpireProductDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return exprodto;
    }
    public ArrayList<String> getAllExpireProductId() throws SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT p_id FROM expired_product ORDER BY LENGTH(p_id),p_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }
    public static AddExpireProductDto getData(String id) throws SQLException {

        Connection connection = DBConnection.getInstance().getConnection();
        String sql="SELECT * FROM expired_product WHERE p_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        AddExpireProductDto dto = new AddExpireProductDto();

        if (resultSet.next()) {
            dto.setP_code(resultSet.getString(1));
            dto.setDescription(resultSet.getString(2));
            dto.setCount(resultSet.getInt(3));
        }
        return dto;
    }
}
