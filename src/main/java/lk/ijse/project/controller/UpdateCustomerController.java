package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtaccounttype;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtemail;

    @FXML
    private ComboBox<String> txtempid;

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txthouseno;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlastname;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txtstreet;

    public static String id;

    customerModel cusmodel = new customerModel();

    public static void setId(String id) {
        UpdateCustomerController.id = id;

    }
    @FXML
    void comboxempidaction(ActionEvent event) {
        String  empid= txtempid.getSelectionModel().getSelectedItem().toString();
    }



    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        customerDto cusdto = new customerDto();


        cusdto.setCus_id(UpdateCustomerController.id);
        cusdto.setFirst_name(txtfirstname.getText());
        cusdto.setEmp_id(txtempid.getSelectionModel().getSelectedItem().toString());
        cusdto.setLast_name(txtlastname.getText());
        cusdto.setCity(txtcity.getText());
        cusdto.setStreet(txtstreet.getText());
        cusdto.setHouse_no(Integer.parseInt(txthouseno.getText()));
        cusdto.setNic(Integer.parseInt(txtnic.getText()));
        cusdto.setEmail(txtemail.getText());
        cusdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
        cusdto.setAccount_type(txtaccounttype.getText());
        try {
            boolean updated =customerModel.updateCustomer(cusdto);
            var model=new customerModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setData(){
        try{
           customerDto cusdto= customerModel.getCustomerdto(id);
            txtcontactno.setText(String.valueOf(cusdto.getContact_no()));
            txtemail.setText(cusdto.getEmail());
            txthouseno.setText(String.valueOf(cusdto.getHouse_no()));
            txtcity.setText(cusdto.getCity());
            txtstreet.setText(cusdto.getStreet());
            txtlastname.setText(cusdto.getLast_name());
            txtfirstname.setText(cusdto.getFirst_name());
            txtnic.setText(String.valueOf(cusdto.getNic()));
            txtaccounttype.setText(cusdto.getAccount_type());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loademployeeids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        txtempid.setItems(obList);
        try {
            List<employeeDto> empidList = employeeModel.loadAllEmployee();

            for (employeeDto dto : empidList) {
                obList.add(dto.getEmp_id());
            }

            // cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
        loademployeeids();
    }
}

