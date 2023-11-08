package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

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
    private TextField txtfirstnatxtstreet;

    @FXML
    private TextField txthouseno;

    @FXML
    private TextField txtlastname;

    @FXML
    private TextField txtnicno;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtrole;

    @FXML
    private TextField txtusername;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm.fxml",event);

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

}
