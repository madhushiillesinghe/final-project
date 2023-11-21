package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class OrderProductController {

    @FXML
    private ImageView addimg;

    @FXML
    private Button btncustomer;

    @FXML
    private Button btncustomerorder;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btndeliveer;

    @FXML
    private Button btnemployee;

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnmachine;

    @FXML
    private Button btnmachineorder;

    @FXML
    private Button btnorders;

    @FXML
    private Button btnproductorder;

    @FXML
    private Button btnproducts;

    @FXML
    private Button btnsupplier;

    @FXML
    private Button btnsupplierorder;

    @FXML
    private ImageView searchimg;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtdate;

    @FXML
    private Text txtqty;

    @FXML
    private TextField txtsearch;

    @FXML
    private Text txtunitPrice;

    @FXML
    private Text txtxAmount;

    @FXML
    private VBox vBoxCusProductOrderManage;

    @FXML
    void addcustomer(MouseEvent event) {

    }

    @FXML
    void btnaddcustomeronaction(ActionEvent event) {

    }

    @FXML
    void customerbtnonaction(ActionEvent event) {

    }

    @FXML
    void customerorderonaction(ActionEvent event) {

    }

    @FXML
    void dashboardonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("dashboardForm.fxml",event);

    }

    @FXML
    void deliverbtnonaction(ActionEvent event) {

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
    void machineorderbtnonaction(ActionEvent event) throws IOException {
    Navigation.switchNavigation("customerMachineOrderForm.fxml",event);
    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);

    }

    @FXML
    void productorderbtnonaction(ActionEvent event) throws IOException {
    Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);

    }

    @FXML
    void searchemployee(MouseEvent event) {

    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);

    }

    @FXML
    void supplierorderbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierOrderForm.fxml",event);
    }

}
