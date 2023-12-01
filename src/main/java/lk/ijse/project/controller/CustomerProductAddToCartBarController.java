package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.model.CustomerModel;
import lk.ijse.project.model.ProductModel;

import java.sql.SQLException;
import java.util.Optional;

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

    private final ObservableList<CustomerOrderDto> obList = FXCollections.observableArrayList();


    AddOrderProductController controller=new AddOrderProductController();


    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Do you want to remove").show();
        controller.allCustomerProductOrderCartIds();
        /*String id = txtProductId.getText();

        //var productModel1= new ProductModel();
        try {
            boolean isremoved = productModel.deleteProduct(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
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

