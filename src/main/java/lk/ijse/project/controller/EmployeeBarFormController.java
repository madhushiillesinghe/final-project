package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtRole;

    @FXML
    private ImageView updateImg;


    @FXML
    void deleteOnMouseClick(MouseEvent event) {
    String id=txtId.getText();

    var empModel=new employeeModel();
    try{
        boolean isDeleted=employeeModel.deleteEmployee(id);
        if(isDeleted){
            new Alert( Alert.AlertType.CONFIRMATION,"Employee deleted").show();
        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
    }


    }
    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        UpdateEmployeeController.setId(txtId.getText());
        Navigation.popupNavigation("updateEmployee.fxml");
    }
   employeeModel empmodel=new employeeModel();
    public void setData(String id) throws SQLException {
        employeeTm emptm= null;
        try {
            emptm=employeeModel.getEmployee(id);
            this.txtId.setText(emptm.getId());
            txtName.setText(emptm.getName());
            txtRole.setText(emptm.getRole());
            txtEmail.setText(emptm.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
