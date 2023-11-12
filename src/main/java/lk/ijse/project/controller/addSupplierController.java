package lk.ijse.project.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.employeeDto;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.model.supplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
public class addSupplierController {

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

    public void initialize(){
        loademployeeids();
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

    @FXML
    void addbtnonaction(ActionEvent event) {
        String firstname = txtfirstname.getText();
        String lastname = txtlastname.getText();
        int contactno = Integer.parseInt(txtcontactno.getText());
        int nic= Integer.parseInt(txtnic.getText());
        String email=txtemail.getText();
        String sid=txtid.getText();
        String type=txttype.getText();

        var model = new supplierDto(sid,contactno,cmbempid.getValue(),type,email,firstname,lastname,nic);
        try {
            boolean isSaved;
            isSaved = supplierModel.saveSupplier(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier saveddd!").show();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);
    }

    @FXML
    void empididonaction(ActionEvent event) {
        String  id = cmbempid.getSelectionModel().getSelectedItem().toString();
    }

}