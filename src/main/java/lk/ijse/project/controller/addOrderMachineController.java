package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.machineDto;
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

public class addOrderMachineController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnplaceorder;

    @FXML
    private ComboBox<String> comboxcustomerid;

    @FXML
    private ComboBox<String > comboxmachineid;

    @FXML
    private DatePicker datepickdate;

    @FXML
    private TextField txtcusname;

    @FXML
    private TextField txtdaysofkeep;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtorderid;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtrentprice;

    @FXML
    private TextField txttask;

    @FXML
    private TextField txttotalamount;
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
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerMachineOrderForm.fxml",event);
    }

    @FXML
    void cusidcmbonaction(ActionEvent event) {
        String  sid = comboxcustomerid.getSelectionModel().getSelectedItem().toString();
        try{
            customerDto dto=customerModel.searchCustomer(sid);
            txtcusname.setText(dto.getFirst_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void dateonaction(ActionEvent event) {
        Date date= Date.valueOf(datepickdate.getValue());
    }

    @FXML
    void machidcmbonaction(ActionEvent event) {
        String  mid = comboxcustomerid.getSelectionModel().getSelectedItem().toString();
        try {
            machineDto dto = machineModel.searchMachine(mid);

            txtname.setText(dto.getM_name());
            txtrentprice.setText(String.valueOf(dto.getMachine_per_day_amount()));
            txtqty.setText(String.valueOf(dto.getMachine_qty()));
            txttask.setText(dto.getM_task());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) throws SQLException {
        CustomerOrderDto cusOrderDto = new CustomerOrderDto();

        cusOrderDto.setCus_order_id(txtorderid.getText());
        cusOrderDto.setCus_id(comboxcustomerid.getSelectionModel().getSelectedItem());
        cusOrderDto.setM_id(comboxmachineid.getSelectionModel().getSelectedItem());
        cusOrderDto.setDate(Date.valueOf(datepickdate.getValue()));
        cusOrderDto.setTmlist(productList);

        boolean isSaved = placeCustomerOrder.SaveCustomerplaceOrder(cusOrderDto);

        if (isSaved) {
            //Navigation.close(event);
            customerOrderMachineController.getInstance().getAllIds();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the ORDER!!!").show();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllMachineIds();
        loadAllCustomerIds();
        txtorderid.setText(NewId.newId(list,NewId.GetType.CUSTOMERRENTID));
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

    private void loadAllMachineIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<machineDto> machineList = machineModel.loadAllMachine();

            for (machineDto mDto : machineList) {
                obList.add(mDto.getM_id());
            }

            comboxmachineid.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
