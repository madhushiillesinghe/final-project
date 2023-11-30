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
import lk.ijse.project.dto.SupplierDto;
import lk.ijse.project.dto.EmployeeDto;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.model.SupplierModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class UpdateSupplierController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

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
    public static String id;

    SupplierModel supmodel = new SupplierModel();

    public static void setId(String id) {
        UpdateSupplierController.id = id;

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("supplierForm.fxml",event);

    }

    @FXML
    void empididonaction(ActionEvent event) {
        String  empid= cmbempid.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        boolean isValidate=validateSupplier();
        if (isValidate) {
            SupplierDto supdto = new SupplierDto();


            supdto.setSup_id(UpdateSupplierController.id);
            supdto.setFirst_name(txtfirstname.getText());
            supdto.setEmp_id(cmbempid.getSelectionModel().getSelectedItem().toString());
            supdto.setLast_name(txtlastname.getText());
            supdto.setNic(txtnic.getText());
            supdto.setEmail(txtemail.getText());
            supdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
            supdto.setSupplier_product_type(txttype.getText());
            try {
                boolean updated = SupplierModel.updateSupplier(supdto);
                var model = new SupplierModel();
                if (updated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier updatedd!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    public void setData() {
            try {
                SupplierDto supdto = SupplierModel.getSupplierDto(id);
                txtcontactno.setText(String.valueOf(supdto.getContact_no()));
                txtemail.setText(supdto.getEmail());
                txtlastname.setText(supdto.getLast_name());
                txtfirstname.setText(supdto.getFirst_name());
                txtnic.setText(supdto.getNic());
                txttype.setText(supdto.getSupplier_product_type());


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
