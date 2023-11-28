package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewEmployeeFormController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Label lblcity;

    @FXML
    private Label lblcontactno;

    @FXML
    private Label lblemail;

    @FXML
    private Label lblempid;

    @FXML
    private Label lblfirstname;

    @FXML
    private Label lblhouseno;

    @FXML
    private Label lbllastname;

    @FXML
    private Label lblnicno;

    @FXML
    private Label lblpassword;

    @FXML
    private Label lblrole;

    @FXML
    private Label lblusername;

    @FXML
    private Label lblstreet;

    public static String nic;


    public static void setId(String  nic) {
        ViewEmployeeFormController.nic = nic;

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();

    }
    public void setData(){
        try{
            employeeDto empdto= employeeModel.searchEmployee(nic);
            lblcontactno.setText(String.valueOf(empdto.getContact_no()));
            lblemail.setText(empdto.getEmail());
            lblhouseno.setText(String.valueOf(empdto.getHouse_no()));
            lblcity.setText(empdto.getCity());
            lblstreet.setText(empdto.getStreet());
            lbllastname.setText(empdto.getLast_name());
            lblfirstname.setText(empdto.getFirst_name());
            lblnicno.setText(empdto.getNic());
            lblrole.setText(empdto.getRole());
            lblpassword.setText(empdto.getPassword());
            lblusername.setText(empdto.getUser_name());
            lblempid.setText(empdto.getEmp_id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
