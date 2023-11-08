package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class SignupController {

    @FXML
    private Button btnsignup;

    @FXML
    private PasswordField psfconfirmpassword;

    @FXML
    private PasswordField pwfpassword;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtusername;

    @FXML
    void signuponaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml",event);
    }

}
