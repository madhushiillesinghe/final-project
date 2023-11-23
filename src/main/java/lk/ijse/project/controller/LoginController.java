package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField pwfpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void forgetpasswordonclick(MouseEvent event) throws IOException {
        Navigation.switchNavigation("forgetPasswordForm.fxml", event);
    }

    @FXML
    void loginbtnonaction(ActionEvent event) throws SQLException, IOException {
        employeeModel empmodel = new employeeModel();
        boolean isValidate = empmodel.checkUsernameAndPassword(txtusername.getText(), pwfpassword.getText()).equals("owner");
        if (isValidate) {
            Navigation.switchNavigation("dashboardForm.fxml", event);
        } else if (empmodel.checkUsernameAndPassword(txtusername.getText(), pwfpassword.getText()).equals("manager")) {
                Navigation.switchNavigation("dashboardForm.fxml", event);
            }
         else {
            new Alert(Alert.AlertType.ERROR, "Wrong User Name Or Password!!").show();
            txtusername.clear();
            pwfpassword.clear();
        }
    }
}
