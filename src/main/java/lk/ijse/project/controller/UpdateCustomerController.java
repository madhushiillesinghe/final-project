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
import lk.ijse.project.dto.CustomerDto;
import lk.ijse.project.dto.EmployeeDto;
import lk.ijse.project.model.CustomerModel;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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

    CustomerModel cusmodel = new CustomerModel();

    public static void setId(String id) {
        UpdateCustomerController.id = id;

    }
    @FXML
    void comboxempidaction(ActionEvent event) {
        String  empid= txtempid.getSelectionModel().getSelectedItem().toString();
    }



    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        boolean isValidate = validateCustomer();
        if (isValidate) {
            CustomerDto cusdto = new CustomerDto();


            cusdto.setCus_id(UpdateCustomerController.id);
            cusdto.setFirst_name(txtfirstname.getText());
            cusdto.setEmp_id(txtempid.getSelectionModel().getSelectedItem().toString());
            cusdto.setLast_name(txtlastname.getText());
            cusdto.setCity(txtcity.getText());
            cusdto.setStreet(txtstreet.getText());
            cusdto.setHouse_no(Integer.parseInt(txthouseno.getText()));
            cusdto.setNic(txtnic.getText());
            cusdto.setEmail(txtemail.getText());
            cusdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
            cusdto.setAccount_type(txtaccounttype.getText());
            try {
                boolean updated = CustomerModel.updateCustomer(cusdto);
                var model = new CustomerModel();
                if (updated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer updatedd!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
    public void setData(){
        try{
           CustomerDto cusdto= CustomerModel.getCustomerdto(id);
            txtcontactno.setText(String.valueOf(cusdto.getContact_no()));
            txtemail.setText(cusdto.getEmail());
            txthouseno.setText(String.valueOf(cusdto.getHouse_no()));
            txtcity.setText(cusdto.getCity());
            txtstreet.setText(cusdto.getStreet());
            txtlastname.setText(cusdto.getLast_name());
            txtfirstname.setText(cusdto.getFirst_name());
            txtnic.setText(cusdto.getNic());
            txtaccounttype.setText(cusdto.getAccount_type());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loademployeeids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        txtempid.setItems(obList);
        try {
            List<EmployeeDto> empidList = EmployeeModel.loadAllEmployee();

            for (EmployeeDto dto : empidList) {
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
    private boolean validateCustomer() {
        String nameText=txtfirstname.getText();
        String contxt=txtcontactno.getText();
        String emailtext=txtemail.getText();
        String cityText=txtcity.getText();
        String nictext=txtnic.getText();

        boolean cusnameValidated= Pattern.matches("[A-z]{3,}",nameText);
        boolean cusaddressValidated=Pattern.matches("[A-z]{3,}",cityText);
        boolean cuscontactValidated=Pattern.matches("[0-9]{10}",contxt);
        boolean cusemailValidated=Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailtext);
        boolean cusnicValidate=Pattern.matches("^\\d{9}[vVxX]$|^\\d{12}$",nictext);


        if (!cusnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer name").show();
            return false;
        }
        if(!cuscontactValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer tele no").show();
            return false;
        }
        if(!cusemailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer email").show();
            return false;
        }
        if(!cusaddressValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer City").show();
            return false;
        }
        if(!cusnicValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid customer NIC").show();
            return false;
        }
        return true;
    }
}

