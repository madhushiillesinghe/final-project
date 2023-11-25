package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.util.Navigation;
import javafx.scene.control.Label;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class addEmployeeController  implements Initializable {
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
    employeeModel empModel=new employeeModel();


    ArrayList<String> list;

    {
        try {
            list = empModel.getAllEmployeeId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {

        boolean isValidated=validateEmployee();
        if(isValidated) {
            String city = txtcity.getText();
            String firstname = txtfirstname.getText();
            String lastname = txtlastname.getText();
            String street = txtstreet.getText();
            int houseno = Integer.parseInt(txthouseno.getText());
            String role = txtrole.getText();
            int contactno = Integer.parseInt(txtcontactno.getText());
            int nic = Integer.parseInt(txtnicno.getText());
            String email = txtemail.getText();
            String password = txtpassword.getText();
            String usename = txtusername.getText();
            String id = txtid.getText();


            var dto = new employeeDto(id, city, street, houseno, contactno, role, usename, password, email, firstname, lastname, nic);
            var model = new employeeModel();
            try {
                boolean isSaved;
                isSaved = model.saveEmployee(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee saveddd!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            txtid.setText(NewId.newId(list,NewId.GetType.EMPLOYEE));
        }
    private boolean validateEmployee() {
        String nameText=txtfirstname.getText();
        String contxt=txtcontactno.getText();
        String emailtext=txtemail.getText();
        String lastnameText=txtlastname.getText();
        String cityText=txtcity.getText();

        boolean empnameValidated= Pattern.matches("[A-z]{3,}",nameText);
        boolean emplastnameValidated=Pattern.matches("[A-z]{3,}",lastnameText);
        boolean empcityValidated=Pattern.matches("[A-z]{3,}",lastnameText);
        boolean empcontactValidated=Pattern.matches("[0-9]{10}",contxt);
        boolean empemailValidated=Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailtext);


        if (!empnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier firstname").show();
            return false;
        }
        if(!empcontactValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier tele no").show();
            return false;
        }
        if(!empemailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier email").show();
            return false;
        }
        if(!emplastnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier last name").show();
            return false;
        }
        if(!empcityValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier city").show();
            return false;
        }
        return true;
    }
}

