package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;

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
    private TextField txtcusname;

    @FXML
    private TextField txtdaysofkeep;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtrentprice;

    @FXML
    private TextField txttask;

    @FXML
    private TextField txttotalamount;

    @FXML
    void cancelbtnonaction(ActionEvent event) {
        Navigation.close(event);
    }

    @FXML
    void cusidcmbonaction(ActionEvent event) {

    }

    @FXML
    void dateonaction(ActionEvent event) {

    }

    @FXML
    void machidcmbonaction(ActionEvent event) {

    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) {

    }

}
