package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.model.machineModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateMachineFormController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Button btnupdate;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtmachinename;

    @FXML
    private TextField txtmachinetask;

    @FXML
    private TextField txtperdayrent;

    @FXML
    private TextField txtquantity;

    public static String id;

    machineModel machmodel= new machineModel();

    public static void setId(String id) {
        UpdateMachineFormController.id = id;
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("machineForm.fxml",event);
    }

    @FXML
    void updatebtnonaction(ActionEvent event) {
        machineDto machdto = new machineDto();


        machdto.setM_id(UpdateMachineFormController.id);
        machdto.setM_name(txtmachinename.getText());
        machdto.setM_task(txtmachinetask.getText());
        machdto.setMachine_qty(Integer.parseInt(txtquantity.getText()));
        machdto.setMachine_per_day_amount(Integer.parseInt(txtperdayrent.getText()));


        try {
            boolean updated = machineModel.updateMachine(machdto);
            var model=new machineModel();
            if (updated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Machine updatedd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void setData(){
        try{
            machineDto machdto= machineModel.getMachineDto(id);
            txtmachinename.setText(machdto.getM_name());
            txtmachinetask.setText(machdto.getM_task());
            txtperdayrent.setText(String.valueOf(machdto.getMachine_per_day_amount()));
            txtquantity.setText(String.valueOf(machdto.getMachine_qty()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

}
