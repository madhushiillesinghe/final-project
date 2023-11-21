package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.ExpireProductDto;
import lk.ijse.project.model.ExpireProductModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddExpireProductFormController  {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

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

    ExpireProductModel exproModel=new ExpireProductModel();


    ArrayList<String> list;

    {
        try {
            list = exproModel.getAllExpireProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addbtnonaction(ActionEvent event) {
        ExpireProductDto exprodto=new ExpireProductDto();

        exprodto.setDescription(txtxDescription.getText());
        exprodto.setCount(Integer.parseInt(txtcount.getText()));
        exprodto.setP_code(txtid.getText());

        try {
            boolean isSaved;
            isSaved = ExpireProductModel.saveExpireProduct(exprodto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Expire product add sucuss!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("ExpireProductForm.fxml",event);
    }

}
