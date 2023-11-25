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
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.util.NewId;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class addCustomerController implements Initializable {


    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;
    @FXML
    private ComboBox<String> cmbempid;

    @FXML
    private TextField txtaccounttype;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtemail;

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

    customerModel cusModel=new customerModel();


    ArrayList<String> list;

    {
        try {
            list = cusModel.getAllCustomerId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void empididonaction(ActionEvent actionEvent) throws SQLException {
        String  id = cmbempid.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void addbtnonaction(ActionEvent event) throws SQLException {
        boolean isValidate = validateCustomer();
        if (isValidate) {
            customerDto cusdto = new customerDto();

            cusdto.setCus_id(txtid.getText());
            cusdto.setCity(txtcity.getText());
            cusdto.setFirst_name(txtfirstname.getText());
            cusdto.setLast_name(txtlastname.getText());
            cusdto.setStreet(txtstreet.getText());
            cusdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
            cusdto.setHouse_no(Integer.parseInt(txthouseno.getText()));
            cusdto.setNic(Integer.parseInt(txtnic.getText()));
            cusdto.setEmail(txtemail.getText());
            cusdto.setAccount_type(txtaccounttype.getText());
            cusdto.setEmp_id(cmbempid.getSelectionModel().getSelectedItem());

            customerModel cusodel = new customerModel();
            try {
                boolean isSaved;
                isSaved = cusodel.saveCustomer(cusdto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Customer saved!").show();
                }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    private void loademployeeids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        cmbempid.setItems(obList);
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
        loademployeeids();
        txtid.setText(NewId.newId(list,NewId.GetType.CUSTOMER));

    }
    private boolean validateCustomer() {
        String nameText=txtfirstname.getText();
        String contxt=txtcontactno.getText();
        String emailtext=txtemail.getText();
        String cityText=txtcity.getText();

        boolean cusnameValidated=Pattern.matches("[A-z]{3,}",nameText);
        boolean cusaddressValidated=Pattern.matches("[A-z]{3,}",cityText);
        boolean cuscontactValidated=Pattern.matches("[0-9]{10}",contxt);
        boolean cusemailValidated=Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailtext);


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
        return true;
    }
}
