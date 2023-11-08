package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class reset_passwordController {

    @FXML
    private Button btnback;

    @FXML
    private Button btnverify;

    @FXML
    private TextField txt1;

    @FXML
    private TextField txt2;

    @FXML
    private TextField txt3;

    @FXML
    private TextField txt4;

    @FXML
    void backonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgetPasswordForm.fxml",event);
    }

    @FXML
    void verifyonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("dashboardForm.fxml",event);
    }

}
