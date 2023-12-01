package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.project.dto.AddExpireProductDto;
import lk.ijse.project.dto.ProductDto;
import lk.ijse.project.model.*;
import lk.ijse.project.util.DateTimeUtil;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddExpireProductFormController implements Initializable {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private TextField txttota;

    @FXML
    private TextField txtxDescription;

    @FXML
    private TextField txtxdate;

    @FXML
    private TextField txtxunitprice;


    @FXML
    private Label lbldate;
    @FXML
    private ComboBox<String>cmbproduct;
    ExpireProductModel exproModel=new ExpireProductModel();

    ArrayList<String[]> productlist = new ArrayList<>();


    ArrayList<String> list;

    {
        try {
            list = exproModel.getAllExpireProductId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void productidonaction(ActionEvent event) {
        String  pid = cmbproduct.getSelectionModel().getSelectedItem().toString();
        try{
            ProductDto dto= ProductModel.searchProduct(pid);
            txtxDescription.setText(dto.getDescription());
            txtxunitprice.setText(String.valueOf(dto.getUnit_price()));
            txtxdate.setText(dto.getExpire_date());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void addbtnonaction(ActionEvent event) throws SQLException {

        String[] products = {String.valueOf(cmbproduct.getSelectionModel().getSelectedItem()),txttota.getText()};
        productlist.add(products);

        AddExpireProductDto exprodto=new AddExpireProductDto();

        exprodto.setP_code(cmbproduct.getSelectionModel().getSelectedItem());
        exprodto.setDescription(txtxDescription.getText());
        exprodto.setCount(Integer.parseInt(txttota.getText()));
        exprodto.setTmlist(productlist);

            boolean isSaved;
            isSaved = AddExpireProductModel.SaveAddExpireProduct(exprodto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Expire product add sucuss!").show();
            }
            else {
                new Alert(Alert.AlertType.ERROR, "Unable to Save expire product").show();
            }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("ExpireProductForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(DateTimeUtil.dateNow());
    loadAllProductIds();
    }

    private void loadAllProductIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ProductDto> productlist = ProductModel.loadAllProduct();

            for (ProductDto pDto :productlist) {
                obList.add(pDto.getP_code());
            }

            cmbproduct.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
