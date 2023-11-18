package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.dto.employeeDto;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static lk.ijse.project.model.employeeModel.getEmployee;

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
    employeeModel empmodel = new employeeModel();

    public static void setId(String id) {
        UpdateEmployeeController.id = id;
    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) throws SQLException {
        employeeDto empdto = new employeeDto();

        empdto.setEmp_id(UpdateEmployeeController.id);
        empdto.setFirst_name(txtfirstname.getText());
        empdto.setLast_name(txtlastname.getText());
        empdto.setCity(txtcity.getText());
        empdto.setStreet(txtstreet.getText());
        empdto.setHouse_no(Integer.parseInt(txthouseno.getText()));
        empdto.setRole(txtrole.getText());
        empdto.setNic(Integer.parseInt(txtnicno.getText()));
        empdto.setEmail(txtemail.getText());
        empdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
        empdto.setUser_name(txtusername.getText());
        empdto.setPassword(txtpassword.getText());
        try {
            boolean updated = employeeModel.updateEmployee(empdto);
            var model=new employeeModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setData(){
        try{
          employeeDto empdto= employeeModel.getemployee(id);
            txtcontactno.setText(String.valueOf(empdto.getContact_no()));
            txtemail.setText(empdto.getEmail());
            txthouseno.setText(String.valueOf(empdto.getHouse_no()));
            txtcity.setText(empdto.getCity());
            txtstreet.setText(empdto.getStreet());
            txtlastname.setText(empdto.getLast_name());
            txtfirstname.setText(empdto.getFirst_name());
            txtnicno.setText(String.valueOf(empdto.getNic()));
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
}

