
package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.project.dto.SupplyOderDto;
import lk.ijse.project.dto.supplierDto;
import lk.ijse.project.dto.tm.ProductOrderTm;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.model.SupplierOrderModel;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.model.supplierModel;
import lk.ijse.project.util.DateTimeUtil;

import java.sql.SQLException;

public class SupplierOrderBarFormController {

    @FXML
    private Text txtDate;

    @FXML
    private Text txtId;

    @FXML
    private Text txtProductType;

    @FXML
    private Text txtSupplierId;

    @FXML
    private Text txtTime;

    SupplierOrderModel supplierOrderModel = new SupplierOrderModel();
    supplierModel supplierModel = new supplierModel();

    public void setData(String id) {
        try {
            SupplyOderDto supplyOderDto = supplierOrderModel.getData(id);
            supplierDto supplierDto = supplierModel.getData(supplyOderDto.getSup_id());

            this.txtId.setText(supplyOderDto.getSup_order_id());
            txtDate.setText(DateTimeUtil.dateNow());
            txtTime.setText(DateTimeUtil.timeNow());
            txtSupplierId.setText(supplyOderDto.getSup_id());
            txtProductType.setText(supplierDto.getSupplier_product_type());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}