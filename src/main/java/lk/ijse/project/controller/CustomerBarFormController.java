package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.customerTm;
import lk.ijse.project.model.customerModel;

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

    }

    @FXML
    void updateOnMouseClick(MouseEvent event) {

    }
    customerModel cusmodel=new customerModel();
    public void setData(String id) throws SQLException {
        customerTm custm= null;
        try {
            custm=customerModel.getCustomer(id);
            this.txtId.setText(custm.getId());
            txtName.setText(custm.getName());
            txtAccount.setText(custm.getAccount());
            txtEmail.setText(custm.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
