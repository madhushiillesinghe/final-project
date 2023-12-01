package lk.ijse.project.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.dto.EmployeeDto;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddEmployeeController implements Initializable {
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
    private ComboBox<String >rolecmb;

    @FXML
    private TextField txtstreet;

    @FXML
    private TextField txtusername;
    EmployeeModel empModel=new EmployeeModel();


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
            int contactno = Integer.parseInt(txtcontactno.getText());
            String nic = txtnicno.getText();
            String email = txtemail.getText();
            String password = txtpassword.getText();
            String usename = txtusername.getText();
            String id = txtid.getText();

            var dto = new EmployeeDto(id, city, street, houseno, contactno,rolecmb.getSelectionModel().getSelectedItem() , usename, password, email, firstname, lastname, nic);
            var model = new EmployeeModel();
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

    @FXML
    void comboboxonaction(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            txtid.setText(NewId.newId(list,NewId.GetType.EMPLOYEE));
            setRoleCombobox();
        }

    private void setRoleCombobox() {
        ArrayList<String> roles=new ArrayList<>();
        roles.add("owner");
        roles.add("manager");
        rolecmb.getItems().addAll(roles);
    }

    private boolean validateEmployee() {
        String nameText=txtfirstname.getText();
        String contxt=txtcontactno.getText();
        String emailtext=txtemail.getText();
        String lastnameText=txtlastname.getText();
        String cityText=txtcity.getText();
        String nictext=txtnicno.getText();

        boolean empnameValidated= Pattern.matches("[A-z]{3,}",nameText);
        boolean emplastnameValidated=Pattern.matches("[A-z]{3,}",lastnameText);
        boolean empcityValidated=Pattern.matches("[A-z]{3,}",cityText);
        boolean empcontactValidated=Pattern.matches("[0-9]{10}",contxt);
       boolean empemailValidated=Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailtext);
        boolean empnicValidate=Pattern.matches("^\\d{9}[vVxX]$|^\\d{12}$",nictext);


        if (!empnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee firstname").show();
            return false;
        }
        if(!empcontactValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee tele no").show();
            return false;
        }
        if(!empemailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee email").show();
            return false;
        }
        if(!emplastnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee last name").show();
            return false;
        }
        if(!empcityValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee city").show();
            return false;
        }
        if(!empnicValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid employee NIC").show();
            return false;
        }
        return true;
    }
}

