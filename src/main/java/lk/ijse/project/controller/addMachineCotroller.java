package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class addMachineCotroller {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtrentid;

    @FXML
    private TextField txtmachinename;

    @FXML
    private TextField txtmachinetask;

    @FXML
    private TextField txtperdayrent;

    @FXML
    private TextField txtquantity;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("machineForm.fxml",event);
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("machineForm.fxml",event);
    }

}
