package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.ExpireProductTm;
import lk.ijse.project.model.ExpireProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class ExpireProductBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txxtCount;

    @FXML
    private ImageView updateImg;

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String id=txtId.getText();

        var expromodel=new ExpireProductModel();
        try{
            boolean isDeleted=ExpireProductModel.deleteExpireProductProduct(id);
            if(isDeleted){
                new Alert( Alert.AlertType.CONFIRMATION,"Expire product deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        UpdateExpireProductFormController.setId(txtId.getText());
        Navigation.switchNavigation("UpdateExpireProductForm.fxml",event);
    }

    public void setData(String id) {
        ExpireProductTm exprotm= null;
        try {
            exprotm= ExpireProductModel.getExpireProduct(id);
            this.txtId.setText(exprotm.getP_code());
            txtName.setText(exprotm.getDescription());
            txxtCount.setText(String.valueOf(exprotm.getCount()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
