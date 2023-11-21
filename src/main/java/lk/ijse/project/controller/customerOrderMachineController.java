package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.project.model.CustomerOrderModel;
import lk.ijse.project.model.SupplierOrderModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class customerOrderMachineController implements Initializable {

    @FXML
    private ImageView addimg;

    @FXML
    private Button btncustomer;

    @FXML
    private Button btncustomerorder;

    @FXML
    private Button btndashboard;

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
    private Text txtCustomerId;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtMachineName;

    @FXML
    private Text txtTime;

    @FXML
    private TextField txtsearch;

    @FXML
    private VBox vBoxSupOrderManage;

    @FXML
    void addcustomer(MouseEvent event) {

    }

    @FXML
    void btnaddcustomeronaction(ActionEvent event) {

    }

    @FXML
    void customerbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);

    }

    @FXML
    void customerorderonaction(ActionEvent event) {

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
    void machineorderbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerMachineOrderForm.fxml",event);

    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void productorderbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerProductOrderForm.fxml", event);
    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void searchemployee(MouseEvent event) {

    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);
    }

    @FXML
    void supplierorderbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierOrderForm.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getAllIds();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllIds() throws SQLException {
        vBoxSupOrderManage.getChildren().clear();
        ArrayList<String> list = null;
        CustomerOrderModel cusomodel = new CustomerOrderModel();
        list = cusomodel.getAllOrderIds();

        for (int i = 0; i < list.size(); i++) {
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(customerOrderMachineController.class.getResource("/view/CustomerMachineOrderBarForm.fxml"));
            Parent root = loader.load();
            CustomerMachineOrderBarFormController controller = loader.getController();
            controller.setData(id);
            vBoxSupOrderManage.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
