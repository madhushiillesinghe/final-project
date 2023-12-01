package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPasswordController implements Initializable {


    @FXML
    private Button btnback;

    @FXML
    private Button btnverify;

    @FXML
    private TextField txtotp;

    private  int otp;

    EmployeeModel employeeModel=new EmployeeModel();
    @FXML
    void backonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("forgetPasswordForm.fxml",event);
    }

    @FXML
    void verifyonaction(ActionEvent event) throws IOException, SQLException {
        System.out.println(ForgetPasswordController.otp);
        //System.out.println(ForgetPasswordController.username);
        boolean verify=String.valueOf((ForgetPasswordController.otp)).equals(txtotp.getText());
        boolean isValidate =employeeModel.checkUsernameForForget(ForgetPasswordController.username).equals("owner");
        boolean isValide =employeeModel.checkUsernameForForget(ForgetPasswordController.username).equals("manager");
        System.out.println(isValidate);
        if(verify){
            if(isValidate){
            Navigation.switchNavigation("dashboardForm.fxml",event);
        }else if(isValide){
                Navigation.switchNavigation("DashboardManager.fxml",event);
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Invalid OTP");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.otp=ForgetPasswordController.otp;
    }
}
