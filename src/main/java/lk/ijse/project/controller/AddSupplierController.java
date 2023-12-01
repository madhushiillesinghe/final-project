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
import lk.ijse.project.dto.EmployeeDto;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.dto.SupplierDto;
import lk.ijse.project.model.SupplierModel;
import lk.ijse.project.util.NewId;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddSupplierController implements Initializable {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private ComboBox<String> cmbempid;

    @FXML
    private TextField txtcontactno;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtfirstname;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlastname;

    @FXML
    private TextField txtnic;

    @FXML
    private TextField txttype;
    SupplierModel supModel=new SupplierModel();


    ArrayList<String> list;

    {
        try {
            list = supModel.getAllSupplierId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loademployeeids() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        cmbempid.setItems(obList);
        try {
            List<EmployeeDto> empidList = EmployeeModel.loadAllEmployee();

            for (EmployeeDto dto : empidList) {
                obList.add(dto.getEmp_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addbtnonaction(ActionEvent event) {
        boolean isValidate=validateSupplier();
        if (isValidate) {
            String firstname = txtfirstname.getText();
            String lastname = txtlastname.getText();
            int contactno = Integer.parseInt(txtcontactno.getText());
            String  nic =txtnic.getText();
            String email = txtemail.getText();
            String sid = txtid.getText();
            String type = txttype.getText();

            var model = new SupplierDto(sid, contactno, cmbempid.getValue(), type, email, firstname, lastname, nic);
            try {
                boolean isSaved;
                isSaved = SupplierModel.saveSupplier(model);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier saveddd!").show();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);
    }

    @FXML
    void empididonaction(ActionEvent event) {
        String  id = cmbempid.getSelectionModel().getSelectedItem().toString();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loademployeeids();
        txtid.setText(NewId.newId(list,NewId.GetType.SUPPLIER));
    }

    private boolean validateSupplier() {
        String nameText=txtfirstname.getText();
        String contxt=txtcontactno.getText();
        String emailtext=txtemail.getText();
        String lastnameText=txtlastname.getText();
        String nictext=txtnic.getText();


        boolean supnameValidated= Pattern.matches("[A-z]{3,}",nameText);
        boolean suplastnameValidated=Pattern.matches("[A-z]{3,}",lastnameText);
        boolean supcontactValidated=Pattern.matches("[0-9]{10}",contxt);
        boolean supemailValidated=Pattern.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailtext);
        boolean supnicValidate=Pattern.matches("^\\d{9}[vVxX]$|^\\d{12}$",nictext);


        if (!supnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier firstname").show();
            return false;
        }
        if(!supcontactValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier tele no").show();
            return false;
        }
        if(!supemailValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier email").show();
            return false;
        }
        if(!suplastnameValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier last name").show();
            return false;
        }
        if(!supnicValidate) {
            new Alert(Alert.AlertType.ERROR, "Invalid supplier NIC").show();
            return false;
        }
        return true;
    }
}