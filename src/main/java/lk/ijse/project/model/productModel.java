package lk.ijse.project.model;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.tm.productTm;
import lk.ijse.project.fp.FpConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class productModel {
    public static boolean saveProduct(productDto prodto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "INSERT INTO agri_product VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, prodto.getP_code());
        pstm.setDouble(2, prodto.getUnit_price());
        pstm.setString(3, prodto.getDescription());
        pstm.setInt(4, prodto.getQty_on_stock());
        pstm.setDate(5, (Date) prodto.getExpire_date());

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateProduct(productDto prodto) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "UPDATE agri_product SET  unit_price = ?, description = ?, qty_on_stock = ?, expire_date = ? WHERE p_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setDouble(1, prodto.getUnit_price());
        pstm.setString(2, prodto.getDescription());
        pstm.setInt(3, prodto.getQty_on_stock());
        pstm.setDate(4, (Date) prodto.getExpire_date());
        pstm.setString(5, prodto.getP_code());


        return pstm.executeUpdate() > 0;
    }


    public productDto searchProduct(String p_code) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM agri_product WHERE p_code = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, p_code);

        ResultSet resultSet = pstm.executeQuery();

       productDto dto = null;

        if (resultSet.next()) {
            dto = new productDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5)
            );
        }
        return dto;
    }

    public static boolean deleteProduct(String p_code) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "DELETE FROM agri_product WHERE p_code = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, p_code);

        return pstm.executeUpdate() > 0;
    }

    public List<productDto> loadAllProduct() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();

        String sql = "SELECT * FROM agri_product";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<productDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            var dto = new productDto(
                    resultSet.getString(1),
                    resultSet.getDouble(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getDate(5)
            );

            dtoList.add(dto);
        }

        return dtoList;
    }
    public static productTm getProduct(String pCode) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM agri_product WHERE p_code = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,pCode);

        ResultSet resultSet = pstm.executeQuery();

       productTm protm = null;

        if(resultSet.next()) {
            protm = new productTm(
                    resultSet.getString(1),
                    resultSet.getString(3),
                    resultSet.getDouble(2),
                    resultSet.getInt(4),
                    resultSet.getDate(5)
            );
        }
        return protm;
    }
    public ArrayList<String> getAllProductId() throws SQLException{
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT p_code FROM agri_product ORDER BY LENGTH(p_code),p_code";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

}
