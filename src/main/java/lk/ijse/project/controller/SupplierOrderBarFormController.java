
package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import lk.ijse.project.dto.SupplyOrderDto;
import lk.ijse.project.dto.SupplierDto;
import lk.ijse.project.model.SupplierOrderModel;
import lk.ijse.project.model.SupplierModel;
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
    SupplierModel supplierModel = new SupplierModel();

    public void setData(String id) {
        try {
            SupplyOrderDto supplyOderDto = supplierOrderModel.getData(id);
            SupplierDto supplierDto = supplierModel.getData(supplyOderDto.getSup_id());

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