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
import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.productDto;
import lk.ijse.project.model.*;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class addOrderProductController implements Initializable {

    @FXML
    private Button btnaddtocart;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnplaceorder;

    @FXML
    private ComboBox<String> comboxcustomerid;

    @FXML
    private ComboBox<String> comboxproductid;

    @FXML
    private DatePicker datepickdate;

    @FXML
    private Text description;

    @FXML
    private Text txtAction;

    @FXML
    private Text txtProductId;

    @FXML
    private Text txtQty1;

    @FXML
    private Text txtUnitPrice;

    @FXML
    private TextField txtcusnme;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtqtyofbuy;

    @FXML
    private TextField txtqtyonstock;

    @FXML
    private Text txttotal;

    @FXML
    private TextField txtunitprice;

    @FXML
    private VBox vBoxproductorderbar;

    CustomerOrderModel cusomodel=new CustomerOrderModel();
    CustomerPlaceOrderModel placeCustomerOrder = new CustomerPlaceOrderModel();

    public static ArrayList<String[]> productList = new ArrayList<>();

    ArrayList<String> list;
    {
        try {
            list = cusomodel.getAllOrderIds();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addtocartbtnonaction(ActionEvent event) {
        String[] products = {String.valueOf(comboxproductid.getSelectionModel().getSelectedItem()), txtqtyofbuy.getText()};

        productList.add(products);

        allCustomerProductOrderCartId();

        txtqtyofbuy.clear();

    }

    private void allCustomerProductOrderCartId() {
       vBoxproductorderbar.getChildren().clear();

        for (int i = 0; i < productList.size(); i++) {
            loadDataTable(productList.get(i));
        }
    }

    private void loadDataTable(String[] id) {
        try {
            FXMLLoader loader = new FXMLLoader(addOrderProductController.class.getResource("/view/CustomerProductAddToCartBar.fxml"));
            Parent root = loader.load();
            CustomerProductAddToCartBarController controller = loader.getController();
            controller.setData(id);
            vBoxproductorderbar.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void customeridonaction(ActionEvent event) {
        String  sid = comboxcustomerid.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void dateonaction(ActionEvent event) {
        Date date= Date.valueOf(datepickdate.getValue());
    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) throws SQLException {
        CustomerOrderDto cusOrderDto = new CustomerOrderDto();

        cusOrderDto.setCus_order_id(txtorderid.getText());
        cusOrderDto.setCus_id(comboxcustomerid.getSelectionModel().getSelectedItem());
        cusOrderDto.setDate(Date.valueOf(datepickdate.getValue()));
       cusOrderDto.setTmlist(productList);

        boolean isSaved = placeCustomerOrder.SaveCustomerplaceOrder(cusOrderDto);

        if (isSaved) {
            Navigation.close(event);
            OrderProductController.getInstance().getAllIds();
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
            txtqtyonstock.setText(String.valueOf(dto.getQty_on_stock()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllProductIds();
        loadAllCustomerIds();
        txtorderid.setText(NewId.newId(list,NewId.GetType.CUSTOMERORDERID));
    }

    private void loadAllCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<customerDto> cusList = customerModel.loadAllCustomer();

            for (customerDto cusDto : cusList) {
                obList.add(cusDto.getCus_id());
            }

            comboxcustomerid.setItems(obList);
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
}
