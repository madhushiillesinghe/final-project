package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.dto.customerDto;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class addCustomerController {


    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private ComboBox<String> cmbempid;

    @FXML
    private TextField txtaccounttype;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txthouseno;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlastname;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txtstreet;

    public void initialize(){
        loademployeeids();
    }
    @FXML
    void empididonaction(ActionEvent actionEvent) throws SQLException {
        String  id = cmbempid.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void addbtnonaction(ActionEvent event){
        String city= txtcity.getText();
        String firstname = txtfirstname.getText();
        String lastname = txtlastname.getText();
        String street = txtstreet.getText();
        int houseno= Integer.parseInt(txthouseno.getText());
        int contactno = Integer.parseInt(txtcontactno.getText());
        int nic= Integer.parseInt(txtnic.getText());
        String email=txtemail.getText();
        String cid=txtid.getText();
        String type=txtaccounttype.getText();
        String id = cmbempid.getValue();

        var model = new customerDto(cid,city,street,houseno,contactno,id,type,email,firstname,lastname,nic);
        try {
            boolean isSaved;
            isSaved = customerModel.saveCustomer(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saveddd!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {

        Navigation.switchNavigation("customerForm.fxml",event);
    }

    private void loademployeeids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        cmbempid.setItems(obList);
        try {
            List<employeeDto> empidList = employeeModel.loadAllEmployee();

            for (employeeDto dto : empidList) {
                obList.add(dto.getEmp_id());
            }

            // cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
