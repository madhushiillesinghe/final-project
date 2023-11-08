package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class sign_upORsign_inController {

    @FXML
    private Button btnsignin;

    @FXML
    private Button btnsignup;

    @FXML
    void signinonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("loginForm.fxml",event);

    }

    @FXML
    void signuponaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("signupForm.fxml",event);
    }

}
