package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class addCustomerController {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

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
    private TextField txtlastname;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txtstreet;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {

        Navigation.switchNavigation("customerForm.fxml",event);
    }

}
