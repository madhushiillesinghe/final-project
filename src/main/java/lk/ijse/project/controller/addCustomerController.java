package lk.ijse.project.controller;

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

import java.io.IOException;
import java.sql.SQLException;

public class addCustomerController {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private ComboBox<?> cmbempid;

    @FXML
    private ComboBox<?> comboxaccounttype;

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

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        String city= txtcity.getText();
        String firstname = txtfirstname.getText();
        String lastname = txtlastname.getText();
        String street = txtstreet.getText();
        int houseno= Integer.parseInt(txthouseno.getText());
        int contactno = Integer.parseInt(txtcontactno.getText());
        int nic= Integer.parseInt(txtnic.getText());
        String email=txtemail.getText();
        String id=txtid.getText();




       /* var model = new employeeDto(id,city,street,houseno,contactno,role,usename,password,email,firstname,lastname,nic);
        try {
            boolean isSaved;
            isSaved = employeeModel.saveEmployee(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saveddd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {

        Navigation.switchNavigation("customerForm.fxml",event);
    }

}
