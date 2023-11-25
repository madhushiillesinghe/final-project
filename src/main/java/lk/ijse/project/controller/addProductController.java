package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addProductController implements Initializable {


    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private DatePicker datepicker;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtprice;

    @FXML
    private TextField txtproductname;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private TextField txtTime;


    ProductModel proModel=new ProductModel();
    ArrayList<String> list;

    {
        try {
            list = proModel.getAllProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        String p_code= txtid.getText();
        Double price= Double.valueOf(txtprice.getText());
        String description=txtproductname.getText();
        int qty = Integer.parseInt(txtqtyonstock.getText());

        var model = new productDto(p_code,price,description,qty,txtTime.getText());
        try {
            boolean isSaved;
            isSaved = ProductModel.saveProduct(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "product saveddd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtid.setText(NewId.newId(list,NewId.GetType.PRODUCT));
    }
}
