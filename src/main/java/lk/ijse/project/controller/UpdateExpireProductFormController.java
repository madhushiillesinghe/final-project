package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.ExpireProductDto;
import lk.ijse.project.model.ExpireProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateExpireProductFormController  implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtcount;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtxDescription;

    @FXML
    private TextField txtxdate;

    @FXML
    private TextField txtxunitprice;

    public static String id;

    ExpireProductModel expromodel=new ExpireProductModel();

    public static void setId(String id) {
        UpdateExpireProductFormController.id=id;
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("ExpireProductForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        ExpireProductDto exprodto=new ExpireProductDto();
        exprodto.setP_code(UpdateExpireProductFormController.id);
        exprodto.setDescription(txtxDescription.getText());
        exprodto.setCount(Integer.parseInt(txtcount.getText()));
        try {
            boolean updated = ExpireProductModel.updateexpireProduct(exprodto);
            var model=new ExpireProductModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Expire product detail updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setData(){
        try{
           ExpireProductDto exprodto= ExpireProductModel.getExpireProductDto(id);
            txtid.setText(exprodto.getP_code());
            txtxDescription.setText(exprodto.getDescription());
            txtcount.setText(String.valueOf(exprodto.getCount()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }
}
