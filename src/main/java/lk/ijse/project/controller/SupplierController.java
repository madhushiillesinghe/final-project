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
import lk.ijse.project.util.*;
import lk.ijse.project.model.SupplierModel;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

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
    private Text txtAction;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtType;

    @FXML
    private TextField txtsearch;
    @FXML
    private Label txttime;

    @FXML
    private Label txtdate;

    @FXML
    private VBox vBoxSupplierManage;
    
    AddSupplierController sup=new AddSupplierController();

    @FXML
    void addcustomer(MouseEvent event) {

    }

    @FXML
    void btnaddsupplieronaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.popupNavigation("addSupplierForm.fxml");

    }


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
    void searchsupplier(MouseEvent event) throws SQLException, IOException {
        SupplierModel supmodel=new SupplierModel();
        ArrayList<String> allsupnic=supmodel.getAllSupplierNic();
        for(int i=0;i<allsupnic.size();i++){
            if(txtsearch.getText().equals(allsupnic.get(i))){
                ViewSupplierFormController.nic=txtsearch.getText();
                Navigation.switchNavigation("ViewSupplierForm.fxml",event);
            }
        }
    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtdate.setText(DateTimeUtil.dateNow());
            txttime.setText(DateTimeUtil.timeNow());
            getAllIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllIds() throws SQLException {
        vBoxSupplierManage.getChildren().clear();

        ArrayList<String> list=null;
        SupplierModel supmodel=new SupplierModel();
        list=supmodel.getAllSupplierId();

        for(int i=0;i<list.size();i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try{
            FXMLLoader loader=new FXMLLoader(CustomerController.class.getResource("/view/SupplierBarForm.fxml"));
            Parent root=loader.load();
          SupplierBarFormController controller=loader.getController();
            controller.setData(id);
           vBoxSupplierManage.getChildren().add(root);
        }catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
