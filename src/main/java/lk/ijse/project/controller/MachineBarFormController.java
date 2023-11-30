package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.MachineTm;
import lk.ijse.project.model.MachineModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MachineBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtQty;

    @FXML
    private Text txtRentFee;

    @FXML
    private ImageView updateImg;

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String id = txtId.getText();

        var mmodel = new MachineModel();
        try {
            boolean isDeleted = MachineModel.deleteMachine(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Machine deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
       UpdateMachineFormController.setId(txtId.getText());
        Navigation.popupNavigation("UpdateMachineForm.fxml");
    }
    MachineModel machmodel=new MachineModel();
    public void setData(String id) throws SQLException {
        MachineTm machtm= null;
        try{
           machtm= MachineModel.getMachine(id);

            this.txtId.setText(machtm.getId());
            txtName.setText(machtm.getName());
            txtRentFee.setText(String.valueOf(machtm.getRent_perday()));
            txtQty.setText(String.valueOf(machtm.getQuantity()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
