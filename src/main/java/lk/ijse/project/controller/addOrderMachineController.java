
package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.dto.machineOrderDto;
import lk.ijse.project.model.*;
import lk.ijse.project.util.DateTimeUtil;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
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
    private Label lbldate;

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

    public static int netTotal;
    MachineRentModel cusrmodel=new MachineRentModel();
    MachinePlaceOrderModel placeCustomerrent = new MachinePlaceOrderModel();

    ArrayList<String[]> machineList = new ArrayList<>();

    ArrayList<String> list;


        {
            try {
                list =cusrmodel.getAllRentIds();
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
            customerDto dto=customerModel.getData(sid);
            txtcusname.setText(dto.getFirst_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void machidcmbonaction(ActionEvent event) {
        String  mid = comboxmachineid.getSelectionModel().getSelectedItem().toString();
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
    void txtcalculatetotal(ActionEvent event) {

    }

    @FXML
    void placeorderbtnonaction(ActionEvent event) throws SQLException {


        String[] machines = {String.valueOf(comboxmachineid.getSelectionModel().getSelectedItem()), txtdaysofkeep.getText()};
        netTotal += ((Integer.parseInt(txtrentprice.getText())) * (Integer.parseInt(txtdaysofkeep.getText())));
        txttotalamount.setText(String.valueOf(netTotal));

        machineList.add(machines);
        txtdaysofkeep.clear();
        machineOrderDto morderdto = new machineOrderDto();

        morderdto.setCus_rent_id(txtorderid.getText());
        morderdto.setCus_id(comboxcustomerid.getSelectionModel().getSelectedItem());
        morderdto.setM_id(comboxmachineid.getSelectionModel().getSelectedItem());
       morderdto.setDate(lbldate.getText());
       morderdto.setTmlist(machineList);

        boolean isSaved = placeCustomerrent.SaveCustomerMachineplaceOrder(morderdto);
        if (isSaved) {
            customerOrderMachineController.getInstance().getAllIds();
        }
        else {
            new Alert(Alert.AlertType.ERROR, "Unable to Save the Rent").show();
        }



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbldate.setText(DateTimeUtil.dateNow());
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

