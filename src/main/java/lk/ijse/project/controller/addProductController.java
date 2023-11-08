package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class addProductController {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private TextField txtexpiredate;

    @FXML
    private TextField txtproductname;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private TextField txtusername;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);
    }

}
