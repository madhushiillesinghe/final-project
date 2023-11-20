package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.project.dto.PlaceOrderDto;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.dto.tm.supplierorderTm;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.productModel;
import lk.ijse.project.model.supplierModel;
import lk.ijse.project.model.supplierOrderModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;
import lk.ijse.project.model.SupplierPlaceOrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addSupplierOrderController {
    private final supplierModel supmodel = new supplierModel();
    private final productModel itemModel = new productModel();
    private final supplierOrderModel orderModel = new supplierOrderModel();
    private final ObservableList<supplierorderTm> obList = FXCollections.observableArrayList();
    @FXML
    private Button btncancel;

    @FXML
    private Button btnplaceorder;

    @FXML
    private ComboBox<String> comboxproductid;

    @FXML
    private ComboBox<String> comboxsupplierid;

    @FXML
    private DatePicker datepickdate;

    @FXML
    private TextField txtDiscountfee;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtqtyofbuy;

    @FXML
    private TextField txttotalamount;

    @FXML
    private TextField txtunitprice;

    @FXML
    private TextField txtxExpireDtae;

    supplierOrderModel supModel=new supplierOrderModel();


    ArrayList<String> list;

    {
        try {
            list = supModel.getAllOrderIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierOrderForm.fxml",event);

    }

    @FXML
    void customeridonaction(ActionEvent event) {
        String supid = comboxsupplierid.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void dateonaction(ActionEvent event) {
        Date date= Date.valueOf(datepickdate.getValue());
    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) {
        String orderId = txtorderid.getText();
        String supId = comboxsupplierid.getSelectionModel().getSelectedItem().toString();
        Date date =Date.valueOf(datepickdate.getValue());

        List<supplierorderTm> tmList = new ArrayList<>();

        for (supplierorderTm cartTm : obList) {
            tmList.add(cartTm);
        }

        var placeOrderDto = new PlaceOrderDto(
                orderId,
                supId,
                date,
                tmList
        );

        try {
            boolean isSuccess = SupplierPlaceOrderModel.SavesupplierplaceOrder(placeOrderDto);
            if(isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void productidonaction(ActionEvent event) {
        String  id =comboxproductid.getSelectionModel().getSelectedItem().toString();
        try {
            productDto dto = productModel.searchProduct(id);

            txtdescription.setText(dto.getDescription());
            txtunitprice.setText(String.valueOf(dto.getUnit_price()));
            txtQty.setText(String.valueOf(dto.getQty_on_stock()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadsupplierids();
        loadproductids();
        txtorderid.setText(NewId.newId(list,NewId.GetType.SUPPLYORDERID));

    }

    private void loadproductids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        comboxproductid.setItems(obList);
        try {
            List<productDto> proidList =productModel.loadAllProduct();

            for (productDto dto :proidList) {
                obList.add(dto.getP_code());
            }

            // cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadsupplierids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        comboxsupplierid.setItems(obList);
        try {
            List<supplierDto> supidList =supplierModel.loadAllSupplier();

            for (supplierDto dto :supidList) {
                obList.add(dto.getSup_id());
            }

            // cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
