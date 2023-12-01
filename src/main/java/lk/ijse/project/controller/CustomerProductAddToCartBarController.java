package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.model.ProductModel;

import java.sql.SQLException;

public class CustomerProductAddToCartBarController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text description;

    @FXML
    private Text txtProductId;

    @FXML
    private Text txtQty1;

    @FXML
    private Text txtUnitPrice;

    @FXML
    private Text txttotal;
    ProductModel productModel = new ProductModel();


    @FXML
    void deleteOnMouseClick(MouseEvent event) {

    }

    public void setData(String[] id) {

        try {
            String[] descriptionAndUnitPrice = productModel.descAndUnitPriceGet(id[0]);

            this.txtProductId.setText(id[0]);
            description.setText(descriptionAndUnitPrice[0]);
            txtUnitPrice.setText(descriptionAndUnitPrice[1]);
            txtQty1.setText(id[1]);
            txttotal.setText(String.valueOf(Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtQty1.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

