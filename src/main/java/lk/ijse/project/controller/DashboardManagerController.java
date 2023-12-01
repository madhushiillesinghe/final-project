package lk.ijse.project.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.util.Duration;
import lk.ijse.project.dto.ProductDto;
import lk.ijse.project.model.*;
import lk.ijse.project.util.*;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardManagerController implements Initializable {

    @FXML
    private Button btncustomer;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btnemployee;

    @FXML
    private Button btnlogout;

    @FXML
    private Button btnmachine;

    @FXML
    private Button btnorders;

    @FXML
    private Button btnproducts;

    @FXML
    private Button btnsupplier;

    @FXML
    private Label txtemployee;

    @FXML
    private Label txtmachine;

    @FXML
    private Label txtorder;

    @FXML
    private Label txtproduct;

    @FXML
    private Label txtdate;

    @FXML
    private Label txttime;

    @FXML
    private Text lblExpireDate;

    @FXML
    private Text lblProductName;

    @FXML
    private Text lblProductPrice;

    @FXML
    private Text lblProductQty;

    @FXML
    private ComboBox<String> cmbExpireProducts;

    @FXML
    private Pane panepiechart;

    @FXML
    private AnchorPane expireProductPopUpPane;

    EmployeeModel empmodel =new EmployeeModel();
    ProductModel productModel=new ProductModel();
    MachineModel machmodel=new MachineModel();
    CustomerOrderModel cusomodel=new CustomerOrderModel();

    @FXML
    void customerbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void dashboardonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("dashboardForm.fxml",event);
    }

    @FXML
    void employeebtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("employeeForm.fxml",event);
    }

    @FXML
    void logoutbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("loginForm.fxml",event);
    }

    @FXML
    void machinebtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("machineForm.fxml",event);
    }

    @FXML
    void ordersbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerProductOrderForm.fxml",event);
    }

    @FXML
    void productsbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("productForm.fxml",event);
    }

    @FXML
    void supplierbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);
    }




    private void pieChart() {
        PieChart piechart=new PieChart();
        try{
            ObservableList<PieChart.Data> pieChartData= DashboardModel.getProductDataForPieChart();
            piechart.setData(pieChartData);
            piechart.getData().get(0).getNode().setStyle("-fx-pie-color: #151B8D ");
            piechart.getData().get(1).getNode().setStyle("-fx-pie-color: #046307 ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // stackPane to center the PieChart
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(piechart);

        panepiechart.getChildren().add(stackPane);
        StackPane.setAlignment(stackPane, Pos.CENTER);
    }


    private void showDashboardorderCount(CustomerOrderModel cusomodel) throws SQLException {
        int countofcusorder= cusomodel.dashboardOrderCount();
        txtorder.setText(String.valueOf(countofcusorder));
    }

    private void showDashboardmachineCount(MachineModel machmodel) throws SQLException {
        int countofmachine= machmodel.dashboardMachineCount();
        txtmachine.setText(String.valueOf(countofmachine));
    }

    private void showDashboardProductCount(ProductModel productModel) throws SQLException {
        int countofproduct= productModel.dashboardProductCount();
        txtproduct.setText(String.valueOf(countofproduct));
    }

    private void showDashboardEmployeeCount(EmployeeModel empmodel) throws SQLException {
        int countofemployee= empmodel.dashboardEmployeeCount();
        txtemployee.setText(String.valueOf(countofemployee));
        System.out.println(countofemployee);
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        expireProductPopUpPane.setVisible(false);
    }

    @FXML
    void cmbExpireProductsOnAction(ActionEvent event) throws SQLException {
        setDataInExpiredPopUpPane();
        expireProductPopUpPane.setVisible(true);

        TranslateTransition transition = new TranslateTransition(Duration.seconds(0.2), expireProductPopUpPane);
        transition.setFromX(expireProductPopUpPane.getWidth());
        transition.setToX(0);
        transition.play();
    }

    public void setDataInComboBox() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<ProductDto> itemList = ProductModel.loadAllExpireProduct();

            for (ProductDto itemDto : itemList) {
                obList.add(itemDto.getP_code());
            }

            cmbExpireProducts.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void setDataInExpiredPopUpPane() throws SQLException {
        ProductDto productDto = ProductModel.searchProduct(cmbExpireProducts.getSelectionModel().getSelectedItem());

        lblProductName.setText(productDto.getDescription());
        lblProductPrice.setText(String.valueOf(productDto.getUnit_price()));
        lblProductQty.setText(String.valueOf(productDto.getQty_on_stock()));
        lblExpireDate.setText(productDto.getExpire_date());
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataInComboBox();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> txttime.setText(DateTimeUtil.timeNow())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        txtdate.setText(DateTimeUtil.dateNow());
        showDashboardEmployeeCount(empmodel);
        showDashboardProductCount(productModel);
        showDashboardmachineCount(machmodel);
        showDashboardorderCount(cusomodel);
        pieChart();
    }

    public void closeOnMouseClicked(MouseEvent mouseEvent) {

    }
}
