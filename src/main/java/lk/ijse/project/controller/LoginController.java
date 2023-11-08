package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class LoginController {

    @FXML
    private ImageView btnlogin;

    @FXML
    private PasswordField pwfpassword;

    @FXML
    private TextField txtusername;

    @FXML
    void btnloginonaction(MouseEvent event) throws IOException {
        Navigation.switchNavigation("dashboardForm.fxml",event);
    }

    @FXML
    void forgetpasswordclick(MouseEvent event) throws IOException {
    Navigation.switchNavigation("forgetPasswordForm.fxml",event);

    }

}
