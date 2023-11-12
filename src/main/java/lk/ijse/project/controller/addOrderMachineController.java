package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

import java.io.IOException;

public class addOrderMachineController {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnplaceorder;

    @FXML
    private ComboBox<?> comboxcustomerid;

    @FXML
    private ComboBox<?> comboxmachineid;

    @FXML
    private DatePicker datepickdate;

    @FXML
    private TextField txtdaysofkeep;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtpayid;

    @FXML
    private TextField txtpaymentmethod;

    @FXML
    private TextField txtqtyq;

    @FXML
    private TextField txtrentprice;

    @FXML
    private TextField txttotalamount;

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
    Navigation.switchNavigation("customerMachineOrderForm",event);
    }

    @FXML
    void dateonaction(ActionEvent event) {

    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) {

    }

}
