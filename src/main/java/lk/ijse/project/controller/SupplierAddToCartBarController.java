package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.model.ProductModel;

import java.sql.SQLException;

public class SupplierAddToCartBarController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text description;

    @FXML
    private Text total;

    @FXML
    private Text txtProductId;

    @FXML
    private Text txtQty;

    @FXML
    private Text txtUnitPrice;

    ProductModel productModel = new ProductModel();

    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }
    public void setData(String[] element) {
        try {
            String[] descriptionAndUnitPrice = productModel.descAndUnitPriceGet(element[0]);

            this.txtProductId.setText(element[0]);
            description.setText(descriptionAndUnitPrice[0]);
            txtUnitPrice.setText(descriptionAndUnitPrice[1]);
            txtQty.setText(element[1]);
            total.setText(String.valueOf(Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtQty.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}