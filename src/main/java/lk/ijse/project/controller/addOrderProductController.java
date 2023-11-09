package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class addOrderProductController {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private ComboBox<?> comboxcustomerid;

    @FXML
    private ComboBox<?> comboxproductid;

    @FXML
    private TextField txtqtyofbuy;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

}
