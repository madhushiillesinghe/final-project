package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.dto.tm.SupplyProductCartTm;
import lk.ijse.project.model.ProductModel;
import lk.ijse.project.model.SupplierPlaceOrderModel;
import lk.ijse.project.model.supplierModel;
import lk.ijse.project.model.SupplierOrderModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addSupplierOrderController implements Initializable {
    private final ObservableList<SupplyProductCartTm> obList = FXCollections.observableArrayList();

    @FXML
    private Button btnaddtocart;

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
    private Text txtAction;

    @FXML
    private TextField txtDiscountfee;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtdescription;

    @FXML
    private Text txtorderbarid;

    @FXML
    private TextField txtorderid;

    @FXML
    private Text txtproductid;

    @FXML
    private Text txtqty;

    @FXML
    private TextField txtqtyofbuy;

    @FXML
    private Text txtsupplierid;

    @FXML
    private Text txttotal;

    @FXML
    private TextField txttotalamount;

    @FXML
    private TextField txtunitprice;

    @FXML
    private TextField txtxExpireDtae;

    @FXML
    private VBox vBoxSupplyOrderBar;
    SupplierOrderModel supomodel=new SupplierOrderModel();
    SupplierPlaceOrderModel placeSupplierOrder = new SupplierPlaceOrderModel();

    public static ArrayList<String[]> productList = new ArrayList<>();

    ArrayList<String> list;
    {
        try {
            list = supomodel.getAllOrderIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addtocartbtnonaction(ActionEvent event) {
        String[] products = {String.valueOf(comboxproductid.getSelectionModel().getSelectedItem()), txtqtyofbuy.getText()};

        productList.add(products);

        allSupplierOrderCartId();

        txtqtyofbuy.clear();
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) {
        Navigation.close(event);
    }

    @FXML
    void supplieridonaction(ActionEvent event) throws SQLException {
        String  sid = comboxsupplierid.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void dateonaction(ActionEvent event) {
        Date date= Date.valueOf(datepickdate.getValue());
    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) throws SQLException {

//        String orderId =txtorderid.getText();
//        String supId = comboxsupplierid.getSelectionModel().getSelectedItem().toString();
//       Date date = Date.valueOf(datepickdate.getValue());
//
//        List<SupplyProductCartTm> tmList = new ArrayList<>();
//
//        for (SupplyProductCartTm cartTm : obList) {
//            tmList.add(cartTm);
//        }
//
//        var dto = new supplyOderDto(
//                orderId,
//                supId,
//                date,
//                tmList
//        );
//
//
//
//        try {
//            boolean isSuccess = SupplierPlaceOrderModel.SavesupplierplaceOrder(dto);
//            if(isSuccess) {
//                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }

        SupplyOderDto supplierOrderDto = new SupplyOderDto();

        supplierOrderDto.setSup_order_id(txtorderid.getText());
        supplierOrderDto.setSup_id(comboxsupplierid.getSelectionModel().getSelectedItem());
        supplierOrderDto.setDate(Date.valueOf(datepickdate.getValue()));
        supplierOrderDto.setTmlist(productList);

        boolean isSaved = placeSupplierOrder.SavesupplierplaceOrder(supplierOrderDto);

        if (isSaved) {
            Navigation.close(event);
            supplierOrderController.getInstance().getAllIds();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the ORDER!!!").show();
        }


    }

    @FXML
    void productidonaction(ActionEvent event) {
        String  pid = comboxproductid.getSelectionModel().getSelectedItem().toString();
        try {
          productDto dto = ProductModel.searchProduct(pid);

           txtdescription.setText(dto.getDescription());
            txtunitprice.setText(String.valueOf(dto.getUnit_price()));
            txtQty.setText(String.valueOf(dto.getQty_on_stock()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void allSupplierOrderCartId() {

        vBoxSupplyOrderBar.getChildren().clear();

        for (int i = 0; i < productList.size(); i++) {
            loadDataTable(productList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(addSupplierOrderController.class.getResource("/view/supplierAddToCartBar.fxml"));
            Parent root = loader.load();
            SupplierAddToCartBarController controller = loader.getController();
            controller.setData(id);
            vBoxSupplyOrderBar.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     loadAllProductIds();
     loadAllSupplierIds();
     txtorderid.setText(NewId.newId(list,NewId.GetType.SUPPLYORDERID));
    }

    private void loadAllSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<supplierDto> supplierList = supplierModel.loadAllSupplier();

            for (supplierDto supDto : supplierList) {
                obList.add(supDto.getSup_id());
            }
           comboxsupplierid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllProductIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<productDto> itemList = ProductModel.loadAllProduct();

            for (productDto itemDto : itemList) {
                obList.add(itemDto.getP_code());
            }

            comboxproductid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void customeridonaction(ActionEvent actionEvent) {
    }
}
