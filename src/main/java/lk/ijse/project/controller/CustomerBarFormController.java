package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.CustomerTm;
import lk.ijse.project.model.CustomerModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerBarFormController {


    @FXML
    private ImageView deleteImg;

    @FXML
    private Text txtAccount;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private ImageView updateImg;

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String id = txtId.getText();

        var cusmodel = new CustomerModel();
        try {
            boolean isDeleted = CustomerModel.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        UpdateCustomerController.setId(txtId.getText());
        Navigation.popupNavigation("updateCustomerForm.fxml");
    }
    CustomerModel cusmodel=new CustomerModel();
    public void setData(String id) throws SQLException {
        CustomerTm custm= null;
        try {
            custm= CustomerModel.getCustomer(id);
            this.txtId.setText(custm.getId());
            txtName.setText(custm.getName());
            txtAccount.setText(custm.getAccount());
            txtEmail.setText(custm.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
