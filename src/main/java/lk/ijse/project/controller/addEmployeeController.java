package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.util.Navigation;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class addEmployeeController {
    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

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
    private TextField txtnicno;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtrole;

    @FXML
    private TextField txtstreet;

    @FXML
    private TextField txtusername;




    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        String city= txtcity.getText();
        String firstname = txtfirstname.getText();
        String lastname = txtlastname.getText();
        String street = txtstreet.getText();
        int houseno= Integer.parseInt(txthouseno.getText());
        String role=txtrole.getText();
        int contactno = Integer.parseInt(txtcontactno.getText());
        int nic= Integer.parseInt(txtnicno.getText());
        String email=txtemail.getText();
        String password=txtpassword.getText();
        String usename=txtusername.getText();
        String id=txtid.getText();




        var model = new employeeDto(id,city,street,houseno,contactno,role,usename,password,email,firstname,lastname,nic);
        try {
            boolean isSaved;
            isSaved = employeeModel.saveEmployee(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saveddd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm.fxml",event);
    }
}
