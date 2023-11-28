package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.project.dto.machineOrderDto;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.model.MachineRentModel;
import lk.ijse.project.util.DateTimeUtil;
import lk.ijse.project.model.machineModel;

import java.sql.SQLException;

public class CustomerMachineOrderBarFormController {

    @FXML
    private Text txtCustomerId;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtmachineName;


    public void setData(String id) {
        try {
            machineOrderDto morderdto = MachineRentModel.getData(id);
            machineDto mdto= machineModel.getData(morderdto.getM_id());

            this.txtId.setText(morderdto.getCus_rent_id());
            txtDate.setText(DateTimeUtil.dateNow());
            txtTime.setText(DateTimeUtil.timeNow());
            txtCustomerId.setText(morderdto.getCus_id());
            txtmachineName.setText(mdto.getM_name());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
