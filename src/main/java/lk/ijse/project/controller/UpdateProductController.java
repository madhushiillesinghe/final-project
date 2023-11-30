package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.ProductDto;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateProductController implements Initializable {


    @FXML
    private TextField TXTexpiredate;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

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
        Navigation.close(event);
     Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        boolean isValidate=validateProduct();
        if(isValidate) {
            ProductDto prodto = new ProductDto();


            prodto.setP_code(UpdateProductController.id);
            prodto.setDescription(txtproductname.getText());
            prodto.setQty_on_stock(Integer.parseInt(txtqtyonstock.getText()));
            prodto.setExpire_date(TXTexpiredate.getText());
            prodto.setUnit_price(Double.parseDouble(txtunitprice.getText()));

            try {
                boolean updated = ProductModel.updateProduct(prodto);
                var model = new ProductModel();
                if (updated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Product updatedd!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void setData(){
        try{
            ProductDto prodto= ProductModel.getProductDto(id);
            txtunitprice.setText(String.valueOf(prodto.getUnit_price()));
            txtproductname.setText(prodto.getDescription());
            txtqtyonstock.setText(String.valueOf(prodto.getQty_on_stock()));
            TXTexpiredate.setText(prodto.getExpire_date());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
    private boolean validateProduct() {
        String expiredatetxt = TXTexpiredate.getText();
        boolean prodateValidated = Pattern.matches("^(3[01]|1[0-9]|0[1-9]|2[0-9])/(1[0-2]|0[1-9])/[0-9]{4}",expiredatetxt);

        if (!prodateValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid expire date").show();
            return false;
        }
        return true;
    }
}


