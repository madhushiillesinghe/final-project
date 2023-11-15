package lk.ijse.project.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.controller.addEmployeeController;
import javafx.scene.control.TableView;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class employeeController {
    private final employeeModel empmodel=new employeeModel();
    private final ObservableList<employeeTm> oblist= FXCollections.observableArrayList();

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
    private Button btnreload;

    @FXML
    private Button btnsupplier;

    @FXML
    private TableColumn<employeeTm, String > coemail;

    @FXML
    private TableColumn<employeeTm, String> colAddress;


    @FXML
    private TableColumn<employeeTm, String> colid;

    @FXML
    private TableColumn<employeeTm, Integer> colmobile;

    @FXML
    private TableColumn<employeeTm, String> colname;


    @FXML
    private TableColumn<employeeTm, String> corole;



    @FXML
    private TableView<employeeTm> employee;

    @FXML
    private ImageView searchimg;

    @FXML
    private TextField txtsearch;

    public void initialize(){
        setCellValueFactory();
        loadAllEmployee();

    }

    private void loadAllEmployee() {
        var model=new employeeModel();
        ObservableList<employeeTm> oblist=FXCollections.observableArrayList();
        try{
          List<employeeDto> dtoList=model.getAllEmployee();
            for(employeeDto dto:dtoList){
                oblist.add(
                        new employeeTm(
                                dto.getEmp_id(),
                                dto.getFirst_name(),
                                dto.getCity(),
                                dto.getRole(),
                                dto.getContact_no(),
                                dto.getEmail()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        corole.setCellValueFactory(new PropertyValueFactory<>("Role"));
        colmobile.setCellValueFactory(new PropertyValueFactory<>("Mobile"));
        coemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    @FXML
    void addcustomer(MouseEvent event) throws IOException {
    Navigation.switchNavigation("addEmployeeForm.fxml",event);
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
    void searchemployee(MouseEvent event) {
        String id=txtsearch.getText();
        var model=new employeeModel();
        try {
        employeeDto dto=model.searchEmployee(id);
        if(dto !=null){

        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
    Navigation.switchNavigation("supplierForm.fxml",event);
    }


}
