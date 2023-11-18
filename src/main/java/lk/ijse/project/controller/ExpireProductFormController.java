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
import lk.ijse.project.model.ExpireProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExpireProductFormController  implements Initializable {

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
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtaction;

    @FXML
    private TextField txtsearch;

    @FXML
    private Text txxtCount;

    @FXML
    private VBox vBoxExpireProductManage;

    @FXML
    void addcustomer(MouseEvent event) {

    }

    @FXML
    void btnaddexpireProductonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("AddExpireProductForm.fxml",event);

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
    void searchcustomer(MouseEvent event) {

    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            getAllIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllIds() throws SQLException {
       vBoxExpireProductManage.getChildren().clear();

        ArrayList<String> list=null;
        ExpireProductModel expromodel=new ExpireProductModel();
        list=expromodel.getAllExpireProductId();

        for(int i=0;i<list.size();i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try{
            FXMLLoader loader=new FXMLLoader(CustomerController.class.getResource("/view/ExpireProductBarForm.fxml"));
            Parent root=loader.load();
            ExpireProductBarFormController controller=loader.getController();
            controller.setData(id);
            vBoxExpireProductManage.getChildren().add(root);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
