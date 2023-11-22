package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.project.dto.CustomerOrderDto;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.model.CustomerOrderModel;
import lk.ijse.project.model.SupplierOrderModel;
import lk.ijse.project.model.supplierModel;
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
    CustomerOrderModel cusorderMOdel = new CustomerOrderModel();

    public void setData(String id) {
        try {
            CustomerOrderDto cusorderdto = CustomerOrderModel.getData(id);
            machineDto mdto= machineModel.getData(cusorderdto.getM_id());

            this.txtId.setText(cusorderdto.getCus_order_id());
            txtDate.setText(DateTimeUtil.dateNow());
            txtTime.setText(DateTimeUtil.timeNow());
            txtCustomerId.setText(cusorderdto.getCus_id());
            txtmachineName.setText(mdto.getM_name());
            //System.out.println(mdto.getM_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}