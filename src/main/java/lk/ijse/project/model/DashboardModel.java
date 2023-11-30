package lk.ijse.project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.project.fp.FpConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardModel {
    public static ObservableList<PieChart.Data> getProductDataForPieChart() throws SQLException {

        Connection connection= FpConnection.getInstance().getConnection();

        ObservableList<PieChart.Data> productdata= FXCollections.observableArrayList();

        Statement subscriptionStatement=connection.createStatement();

        String subsql="SELECT COUNT(p_code) AS total_Product_count FROM agri_product";

        ResultSet subscriptionResult=subscriptionStatement.executeQuery(subsql);
        if(subscriptionResult.next()){
            int subscriptiontotal=subscriptionResult.getInt(1);
            productdata.add(new PieChart.Data("product count",subscriptiontotal));
        }
        Statement expireStatement=connection.createStatement();
        String exsql="SELECT COUNT(m_id) AS total_machine_count FROM machine";
        ResultSet expireresult=expireStatement.executeQuery(exsql);
        if(expireresult.next()){
            int exproductTotal=expireresult.getInt(1);
            productdata.add(new PieChart.Data("machine  count",exproductTotal));
        }
        return productdata;
    }
}
