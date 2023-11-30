package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.ProductTm;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ProductBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text txtExpiredate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtqty;

    @FXML
    private Text txtunitprice;

    @FXML
    private ImageView updateImg;

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String id = txtId.getText();

        var promodel = new ProductModel();
        try {
            boolean isDeleted = ProductModel.deleteProduct(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "product deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        UpdateProductController.setId(txtId.getText());
        Navigation.popupNavigation("UpdateProductForm.fxml");
    }
    ProductModel promodel=new ProductModel();
    public void setData(String id) throws SQLException {
       ProductTm protm= null;
       try{
        protm= ProductModel.getProduct(id);

        this.txtId.setText(protm.getId());
        txtName.setText(protm.getName());
        txtunitprice.setText(String.valueOf(protm.getUnitPrice()));
        txtqty.setText(String.valueOf(protm.getQty()));
        txtExpiredate.setText(protm.getExpireDate());
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }
}
