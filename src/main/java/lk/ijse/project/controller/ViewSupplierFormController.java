package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.model.supplierModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewSupplierFormController implements Initializable {
    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNIc;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lblempId;

    @FXML
    private Label lblfname;

    @FXML
    private Label lbllname;

    @FXML
    private Label lblno;

    @FXML
    private Label lblproductType;

    public static int nic;

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("supplierForm.fxml",event);
    }




    public static void setNic(int nic) {
        ViewSupplierFormController.nic = nic;

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try{
            supplierDto supdto= supplierModel.searchSupplier(nic);

            lblEmail.setText(supdto.getEmail());
           lblfname.setText(supdto.getFirst_name());
           lblproductType.setText(supdto.getSupplier_product_type());
           lbllname.setText(supdto.getLast_name());
           lblno.setText(String.valueOf(supdto.getContact_no()));
           lblempId.setText(supdto.getEmp_id());
           lblSupId.setText(supdto.getSup_id());
           lblNIc.setText(String.valueOf(supdto.getNic()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
