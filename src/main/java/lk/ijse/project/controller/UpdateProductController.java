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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

    @FXML
    private DatePicker dateexpire;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtproductname;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private TextField txtunitprice;

    public static String id;

    ProductModel promodel = new ProductModel();

    public static void setId(String id) {
        UpdateProductController.id = id;
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
     Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void datepickeronaction(ActionEvent event) {

    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
       productDto prodto = new productDto();


        prodto.setP_code(UpdateProductController.id);
        prodto.setDescription(txtproductname.getText());
        prodto.setQty_on_stock(Integer.parseInt(txtqtyonstock.getText()));
        prodto.setExpire_date(Date.valueOf(dateexpire.getValue()));
        prodto.setUnit_price(Double.parseDouble(txtunitprice.getText()));

        try {
            boolean updated = ProductModel.updateProduct(prodto);
            var model=new ProductModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setData(){
        try{
            productDto prodto= ProductModel.getProductDto(id);
            txtunitprice.setText(String.valueOf(prodto.getUnit_price()));
            txtproductname.setText(prodto.getDescription());
            txtqtyonstock.setText(String.valueOf(prodto.getQty_on_stock()));
            dateexpire.setValue(prodto.getExpire_date().toLocalDate());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}

