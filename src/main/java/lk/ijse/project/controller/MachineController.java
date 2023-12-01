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
import lk.ijse.project.model.MachineModel;
import lk.ijse.project.util.*;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MachineController implements Initializable {

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
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtqty;

    @FXML
    private TextField txtsearch;

    @FXML
    private Text txtxrentfee;

    @FXML
    private Label lbldate;

    @FXML
    private Label lbltime;

    @FXML
    private VBox vBoxMachineManage;

    @FXML
    void addcustomer(MouseEvent event) {

    }

    @FXML
    void btnaddmachineonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.popupNavigation("addMachineForm.fxml");
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
    void searchmachine(MouseEvent event) throws SQLException, IOException {
        MachineModel mmodel=new MachineModel();
        ArrayList<String> allmacid=mmodel.getAllMachineId();
        for(int i=0;i<allmacid.size();i++){
            if(txtsearch.getText().equals(allmacid.get(i))){
                ViewMachineFormController.id= txtsearch.getText();
                Navigation.switchNavigation("ViewMachineForm.fxml",event);
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
            lbldate.setText(DateTimeUtil.dateNow());
            lbltime.setText(DateTimeUtil.timeNow());
            getAllIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getAllIds() throws SQLException {
        vBoxMachineManage.getChildren().clear();

        ArrayList<String> list=null;
        MachineModel machmodel=new MachineModel();
        list=machmodel.getAllMachineId();

        for(int i=0;i<list.size();i++){
            loadTableData(list.get(i));
        }


    }

    private void loadTableData(String id) {
        try{
            FXMLLoader loader=new FXMLLoader(CustomerController.class.getResource("/view/MachineBarForm.fxml"));
            Parent root=loader.load();
            MachineBarFormController controller=loader.getController();
            controller.setData(id);
            vBoxMachineManage.getChildren().add(root);
        }catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
