package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class addOrderProductController {

    @FXML
    private Button btnaddtocart;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnplaceorder;

    @FXML
    private ComboBox<?> comboxcustomerid;

    @FXML
    private ComboBox<?> comboxproductid;

    @FXML
    private DatePicker datepickdate;

    @FXML
    private Text description;

    @FXML
    private Text txtAction;

    @FXML
    private Text txtProductId;

    @FXML
    private Text txtQty1;

    @FXML
    private Text txtUnitPrice;

    @FXML
    private TextField txtcusnme;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtqtyofbuy;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private Text txttotal;

    @FXML
    private TextField txtunitprice;

    @FXML
    private VBox vBoxproductorderbar;

    @FXML
    void addtocartbtnonaction(ActionEvent event) {

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) {

    }

    @FXML
    void customeridonaction(ActionEvent event) {

    }

    @FXML
    void dateonaction(ActionEvent event) {

    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) {

    }

    @FXML
    void productidonaction(ActionEvent event) {

    }

}
