package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.model.productModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class addProductController {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;
    @FXML
    private TextField txtid;

    @FXML
    private DatePicker dateexpire;

    @FXML
    private TextField txtproductname;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private TextField txtusername;

    @FXML
    void datepickeronaction(ActionEvent event) {
        Date date= Date.valueOf(dateexpire.getValue());
    }

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        String p_code= txtid.getText();
        Double price= Double.valueOf(txtusername.getText());
        String description=txtproductname.getText();
        int qty = Integer.parseInt(txtqtyonstock.getText());

        var model = new productDto(p_code,price,description,qty,Date.valueOf(dateexpire.getValue()));
        try {
            boolean isSaved;
            isSaved = productModel.saveProduct(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "product saveddd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);
    }

}
