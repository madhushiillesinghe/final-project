package lk.ijse.project.controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class forget_passwordController {

    @FXML
    private Button btnreset;

    @FXML
    private TextField txtusername;
    private AnchorPane root;

    @FXML
    void btnOnAction(MouseEvent event) throws IOException {
        System.out.println("stn on action success but not navigate");

    }

}