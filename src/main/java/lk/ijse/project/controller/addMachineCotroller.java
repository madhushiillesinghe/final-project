package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.dto.machineDto;
import lk.ijse.project.model.machineModel;
import lk.ijse.project.util.Navigation;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class addMachineCotroller {

    @FXML
    private Button btnadd;

    @FXML
    private Button btncancel;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtrentid;

    @FXML
    private TextField txtmachinename;

    @FXML
    private TextField txtmachinetask;

    @FXML
    private TextField txtperdayrent;

    @FXML
    private TextField txtquantity;

    @FXML
    void addbtnonaction(ActionEvent event) throws IOException {
        String m_id= txtid.getText();
        String m_name=txtmachinename.getText();
        String m_task=txtmachinetask.getText();
        int qty = Integer.parseInt(txtquantity.getText());
        int amount= Integer.parseInt(txtperdayrent.getText());

        var model = new machineDto(m_id,m_name,m_task,qty,amount);
        try {
            boolean isSaved;
            isSaved = machineModel.saveMachine(model);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "machine saveddd!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cancelbtnonaction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("machineForm.fxml",event);
    }

}
