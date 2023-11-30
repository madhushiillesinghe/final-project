package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.dto.EmployeeDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateEmployeeController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

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

    public static String id;
    EmployeeModel empmodel = new EmployeeModel();

    public static void setId(String id) {
        UpdateEmployeeController.id = id;
    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) throws SQLException {

        boolean isValidated = validateEmployee();
        if (isValidated) {
            EmployeeDto empdto = new EmployeeDto();

            empdto.setEmp_id(UpdateEmployeeController.id);
            empdto.setFirst_name(txtfirstname.getText());
            empdto.setLast_name(txtlastname.getText());
            empdto.setCity(txtcity.getText());
            empdto.setStreet(txtstreet.getText());
            empdto.setHouse_no(Integer.parseInt(txthouseno.getText()));
            empdto.setRole(txtrole.getText());
            empdto.setNic(txtnicno.getText());
            empdto.setEmail(txtemail.getText());
            empdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
            empdto.setUser_name(txtusername.getText());
            empdto.setPassword(txtpassword.getText());
            try {
                boolean updated = EmployeeModel.updateEmployee(empdto);
                var model = new EmployeeModel();
                if (updated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Employee updatedd!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void setData(){
        try{
          EmployeeDto empdto= EmployeeModel.getemployee(id);
            txtcontactno.setText(String.valueOf(empdto.getContact_no()));
            txtemail.setText(empdto.getEmail());
            txthouseno.setText(String.valueOf(empdto.getHouse_no()));
            txtcity.setText(empdto.getCity());
            txtstreet.setText(empdto.getStreet());
            txtlastname.setText(empdto.getLast_name());
            txtfirstname.setText(empdto.getFirst_name());
            txtnicno.setText(empdto.getNic());
            txtrole.setText(empdto.getRole());
            txtpassword.setText(empdto.getPassword());
            txtusername.setText(empdto.getUser_name());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    setData();
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

