package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.model.CustomerOrderModel;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.model.machineModel;
import lk.ijse.project.util.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button btncustomer;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btnemployee;

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnmachine;

    @FXML
    private Button btnorders;

    @FXML
    private Button btnproducts;

    @FXML
    private Button btnsupplier;

    @FXML
    private Label txtemployee;

    @FXML
    private Label txtmachine;

    @FXML
    private Label txtorder;

    @FXML
    private Label txtproduct;

    @FXML
    private Label txtdate;

    @FXML
    private Label txttime;


employeeModel empmodel =new employeeModel();
ProductModel productModel=new ProductModel();
machineModel machmodel=new machineModel();

CustomerOrderModel cusomodel=new CustomerOrderModel();
    @FXML
    void customerbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void dashboardonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("dashboardForm.fxml",event);
    }

    @FXML
    void employeebtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @FXML
    void logoutbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void machinebtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("machineForm.fxml",event);
    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);
    }


    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtdate.setText(DateTimeUtil.dateNow());
        txttime.setText(DateTimeUtil.timeNow());
        showDashboardEmployeeCount(empmodel);
        showDashboardProductCount(productModel);
        showDashboardmachineCount(machmodel);
        showDashboardorderCount(cusomodel);



    }

    private void showDashboardorderCount(CustomerOrderModel cusomodel) throws SQLException {
        int countofcusorder= cusomodel.dashboardOrderCount();
        txtorder.setText(String.valueOf(countofcusorder));
    }

    private void showDashboardmachineCount(machineModel machmodel) throws SQLException {
        int countofmachine= machmodel.dashboardMachineCount();
        txtmachine.setText(String.valueOf(countofmachine));
    }

    private void showDashboardProductCount(ProductModel productModel) throws SQLException {
        int countofproduct= ProductModel.dashboardProductCount();
       txtproduct.setText(String.valueOf(countofproduct));
    }

    private void showDashboardEmployeeCount(employeeModel empmodel) throws SQLException {
       int countofemployee= empmodel.dashboardEmployeeCount();
       txtemployee.setText(String.valueOf(countofemployee));
        System.out.println(countofemployee);
    }

}
