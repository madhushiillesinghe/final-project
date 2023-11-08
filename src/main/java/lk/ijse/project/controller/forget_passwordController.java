package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class forget_passwordController {

    @FXML
    private Button btnreset;

    @FXML
    private TextField txtusername;

    @FXML
    void btnOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("resetpasswordForm.fxml",event);
    }

}
