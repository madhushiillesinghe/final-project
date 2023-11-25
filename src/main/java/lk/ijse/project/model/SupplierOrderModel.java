package lk.ijse.project.model;

import lk.ijse.project.dto.SupplyOderDto;

import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.fp.FpConnection;

import java.sql.*;
import java.util.ArrayList;

public class SupplierOrderModel {
    public static boolean saveOrder(SupplyOderDto dto) throws SQLException {
        Connection connection= FpConnection.getInstance().getConnection();
        String sql="INSERT INTO supplier_order VALUES(?, ?, ?)";
        PreparedStatement pstm=connection.prepareStatement(sql);
        pstm.setString(1,dto.getSup_order_id());
        pstm.setString(2,dto.getSup_id());
        pstm.setString(3, dto.getDate());

        return pstm.executeUpdate()>0;
    }

    public static ArrayList<String> getAllOrderIds() throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT sup_order_id FROM supplier_order ORDER BY LENGTH(sup_order_id),sup_order_id";
        PreparedStatement pstm=connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public static SupplyOderDto getData(String id) throws SQLException {

        Connection connection = FpConnection.getInstance().getConnection();
        String sql="SELECT * FROM supplier_order WHERE sup_order_id=?";
        PreparedStatement pstm=connection.prepareStatement(sql);

        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        SupplyOderDto supplyOderDto = new SupplyOderDto();

        if (resultSet.next()) {
            supplyOderDto.setSup_order_id(resultSet.getString(1));
            supplyOderDto.setSup_id(resultSet.getString(2));
            supplyOderDto.setDate(resultSet.getString(3));
        }
        return supplyOderDto;
    }

    /*public static ProductOrderTm getOrder(String order_id) throws SQLException {
        Connection connection = FpConnection.getInstance().getConnection();
        String sql = "SELECT * FROM supplier_order WHERE sup_order_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,order_id);

        ResultSet resultSet = pstm.executeQuery();

        ProductOrderTm prootm= null;

        if(resultSet.next()) {
           prootm= new ProductOrderTm();
           resultSet.getString(1);
        }
        return prootm;


    }*/
}