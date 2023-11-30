package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.project.dto.MachineDto;
import lk.ijse.project.model.MachineModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewMachineFormController implements Initializable {

    @FXML
    private Button btncancel;

    @FXML
    private Label lblid;

    @FXML
    private Label lblname;

    @FXML
    private Label lblqty;

    @FXML
    private Label lblrent;

    @FXML
    private Label lbltask;

    public static String id;

    public static void setId(String id) {
        ViewMachineFormController.id = id;

    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.close(event);
        Navigation.switchNavigation("machineForm.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try{
           MachineDto mdto= MachineModel.searchMachine(id);

            lblid.setText(mdto.getM_id());
            lblname.setText(mdto.getM_name());
            lbltask.setText(mdto.getM_task());
            lblqty.setText(String.valueOf(mdto.getMachine_qty()));
            lblrent.setText(String.valueOf(mdto.getMachine_per_day_amount()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
