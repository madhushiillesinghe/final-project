package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.model.employeeModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import lk.ijse.project.util.*;

public class employeeController {

    @FXML
    private ImageView addimg;

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
    private ImageView deleteImg;

    @FXML
    private ImageView searchimg;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtMobile;

    @FXML
    private Text txtName;

    @FXML
    private Text txtrole;

    @FXML
    private TextField txtsearch;

    @FXML
    private ImageView updateImg;

    @FXML
    private ImageView viewImg;

    @FXML
    void addcustomer(MouseEvent event) throws IOException {
   Navigation.switchNavigation("addCustomerForm",event);
    }

    @FXML
    void customerbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerForm",event);
    }

    @FXML
    void dashboardonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("dashboardForm",event);
    }

    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }

    @FXML
    void detailsOnMouseClick(MouseEvent event) {

    }

    @FXML
    void employeebtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm",event);
    }

    @FXML
    void logoutbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm",event);
    }

    @FXML
    void machinebtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("machineForm",event);
    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerProductOrderForm",event);

    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm",event);

    }

    @FXML
    void searchemployee(MouseEvent event) {

    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm",event);
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int mobile = Integer.parseInt(txtMobile.getText());
        String  email=txtEmail.getText();
        String role=txtrole.getText();

        var dto = new employeeTm(id, name, address,role,mobile,email);

        var model = new employeeModel();
        try {
            boolean isUpdated = model.updateEmployee(dto);
            System.out.println(isUpdated);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

}
