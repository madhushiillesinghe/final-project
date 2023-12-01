package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.project.dto.*;
import lk.ijse.project.model.*;
import lk.ijse.project.util.DateTimeUtil;

import java.sql.SQLException;

public class CustomerProductOrderBarFormController {

    @FXML
    private Text txtCustomerId;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtProductName;

    @FXML
    private Text txtTime;
    CustomerOrderModel cusOrderModel = new CustomerOrderModel();
    CustomerModel cusModel = new CustomerModel();

    public void setData(String id) {
        try {
            CustomerOrderDto cusOrderDto =CustomerOrderModel.getData(id);
           CustomerDto cusdto = CustomerModel.getData(cusOrderDto.getCus_id());

            this.txtId.setText(cusOrderDto.getCus_order_id());
            txtDate.setText(DateTimeUtil.dateNow());
            txtTime.setText(DateTimeUtil.timeNow());
            txtCustomerId.setText(cusOrderDto.getCus_id());
            txtProductName.setText(cusdto.getFirst_name());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

