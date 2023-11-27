package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.model.supplierModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewProductFormController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Label lbldate;

    @FXML
    private Label lblid;

    @FXML
    private Label lblname;

    @FXML
    private Label lblqty;

    @FXML
    private Label lblunitprice;

    public static String id;

    public static void setId(String id) {
        ViewProductFormController.id = id;

    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try{
           productDto prodto= ProductModel.searchProduct(id);

            lblname.setText(prodto.getDescription());
           lblid.setText(prodto.getP_code());
           lbldate.setText(prodto.getExpire_date());
           lblqty.setText(String.valueOf(prodto.getQty_on_stock()));
           lblunitprice.setText(String.valueOf(prodto.getUnit_price()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("productForm.fxml",event);

    }

}
