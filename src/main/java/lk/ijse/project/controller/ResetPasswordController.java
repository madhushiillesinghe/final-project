package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {


    @FXML
    private Button btnback;

    @FXML
    private Button btnverify;

    @FXML
    private TextField txtotp;

    private  int otp;
    @FXML
    void backonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgetPasswordForm.fxml",event);
    }

    @FXML
    void verifyonaction(ActionEvent event) throws IOException {
        System.out.println(ForgetPasswordController.otp);
        boolean verify=String.valueOf((ForgetPasswordController.otp)).equals(txtotp.getText());
        if(verify){
            Navigation.switchNavigation("dashboardForm.fxml",event);
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid OTP");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.otp=ForgetPasswordController.otp;
    }
}
