package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.dto.customerDto;
import lk.ijse.project.model.customerModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Label lblaccounttype;

    @FXML
    private Label lblcity;

    @FXML
    private Label lblcontactno;

    @FXML
    private Label lblcusid;

    @FXML
    private Label lblemail;

    @FXML
    private Label lblempid;

    @FXML
    private Label lblfirstname;

    @FXML
    private Label lblhouseno;

    @FXML
    private Label lbllastname;

    @FXML
    private Label lblnic;

    @FXML
    private Label lblstreet;


    public static String  nic;

    public static void setId(String nic) {
        ViewCustomerFormController.nic = nic;

    }


    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("customerForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try{
            customerDto cusdto= customerModel.searchCustomer(nic);

            lblemail.setText(cusdto.getEmail());
            lblhouseno.setText(String.valueOf(cusdto.getHouse_no()));
            lblcity.setText(cusdto.getCity());
            lblstreet.setText(cusdto.getStreet());
            lbllastname.setText(cusdto.getLast_name());
            lblfirstname.setText(cusdto.getFirst_name());
            lblnic.setText(cusdto.getNic());
            lblaccounttype.setText(cusdto.getAccount_type());
            lblempid.setText(cusdto.getEmp_id());
            lblcusid.setText(cusdto.getCus_id());
            lblcontactno.setText(String.valueOf(cusdto.getContact_no()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }
