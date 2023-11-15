package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.Navigation.*;

import java.io.IOException;

public class supplierController {

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
    private ImageView searchimg;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtMobile;

    @FXML
    private Text txtName;

    @FXML
    private Text txtproductname;

    @FXML
    private TextField txtsearch;



    @FXML
    void addsupplier(MouseEvent event) throws IOException {
        Navigation.switchNavigation("addSupplierForm.fxml",event);
    }

    @FXML
    void customerbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void dashboardonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("dashboardForm.fxml",event);
    }


    @FXML
    void employeebtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @FXML
    void logoutbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void machinebtnonaction(ActionEvent event) throws IOException {
    Navigation.switchNavigation("machineForm.fxml",event);
    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void searchsupplier(MouseEvent event) {

    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);
    }



}
