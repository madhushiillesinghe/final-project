package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.tm.productTm;
import lk.ijse.project.model.productModel;

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

    }

    @FXML
    void updateOnMouseClick(MouseEvent event) {

    }
    productModel promodel=new productModel();
    public void setData(String id) throws SQLException {
       productTm protm= null;
       try{
        protm=productModel.getProduct(id);

        this.txtId.setText(protm.getId());
        txtName.setText(protm.getName());
        txtunitprice.setText(String.valueOf(protm.getUnitPrice()));
        txtqty.setText(String.valueOf(protm.getQty()));
        txtExpiredate.setText(String.valueOf(protm.getExpireDate()));
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }
}
