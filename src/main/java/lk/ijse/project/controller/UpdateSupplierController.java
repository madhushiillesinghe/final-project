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
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.model.supplierModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

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

    supplierModel supmodel = new supplierModel();

    public static void setId(String id) {
        UpdateSupplierController.id = id;

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);

    }

    @FXML
    void empididonaction(ActionEvent event) {
        String  empid= cmbempid.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        supplierDto supdto = new supplierDto();


        supdto.setSup_id(UpdateSupplierController.id);
        supdto.setFirst_name(txtfirstname.getText());
        supdto.setEmp_id(cmbempid.getSelectionModel().getSelectedItem().toString());
        supdto.setLast_name(txtlastname.getText());
        supdto.setNic(Integer.parseInt(txtnic.getText()));
        supdto.setEmail(txtemail.getText());
        supdto.setContact_no(Integer.parseInt(txtcontactno.getText()));
        supdto.setSupplier_product_type(txttype.getText());
        try {
            boolean updated = supplierModel.updateSupplier(supdto);
            var model=new supplierModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void setData(){
        try{
           supplierDto supdto = supplierModel.getSupplierDto(id);
            txtcontactno.setText(String.valueOf(supdto.getContact_no()));
            txtemail.setText(supdto.getEmail());
            txtlastname.setText(supdto.getLast_name());
            txtfirstname.setText(supdto.getFirst_name());
            txtnic.setText(String.valueOf(supdto.getNic()));
            txttype.setText(supdto.getSupplier_product_type());



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        setData();
        loademployeeids();
    }
}
