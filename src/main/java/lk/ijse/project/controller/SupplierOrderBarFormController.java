
package lk.ijse.project.controller;

        import javafx.fxml.FXML;
        import javafx.scene.text.Text;
        import lk.ijse.project.dto.tm.ProductOrderTm;
        import lk.ijse.project.dto.tm.employeeTm;
        import lk.ijse.project.model.employeeModel;

        import java.sql.SQLException;

public class SupplierOrderBarFormController {

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtqty;

    @FXML
    private Text txtunitPrice;

    @FXML
    private Text txtxAmount;
    /*public void setData(String id) {
        ProductOrderTm prootm= null;
        try {
            prootm= employeeModel.getEmployee(id);
            this.txtId.setText(emptm.getId());
            txtName.setText(emptm.getName());
            txtRole.setText(emptm.getRole());
            txtEmail.setText(emptm.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

}
