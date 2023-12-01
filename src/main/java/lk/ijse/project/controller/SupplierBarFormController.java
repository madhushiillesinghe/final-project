package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.SupplierTm;
import lk.ijse.project.DB.DBConnection;
import lk.ijse.project.model.SupplierModel;
import lk.ijse.project.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class SupplierBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private ImageView reportImg;

    @FXML
    private Text txtEmail;

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtType;

    @FXML
    private ImageView updateImg;

    @FXML
    void deleteOnMouseClick(MouseEvent event) {
        String id = txtId.getText();

        var supmodel = new SupplierModel();
        try {
            boolean isDeleted = SupplierModel.deleteSupplier(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void updateOnMouseClick(MouseEvent event) throws IOException {
        UpdateSupplierController.setId(txtId.getText());
        Navigation.popupNavigation("UpdateSupplierForm.fxml");
    }

    SupplierModel supmodel = new SupplierModel();

    public void setData(String id) throws SQLException {
        SupplierTm suptm = null;
        try {
            suptm = SupplierModel.getSupplier(id);
            this.txtId.setText(suptm.getId());
            txtName.setText(suptm.getName());
            txtType.setText(suptm.getType());
            txtEmail.setText(suptm.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reportOnMouseClick(MouseEvent event) {
        try{
            JasperDesign jasperDesign= JRXmlLoader.load("src/main/resources/Report/SupplierDetail.jrxml");
            JRDesignQuery query=new JRDesignQuery();
            query.setText("SELECT sup_id, supplier_product_type, first_name,  email FROM supplier; ");
            jasperDesign.setQuery(query);
            JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null, DBConnection.getInstance().getConnection());
            JFrame frame= new JFrame("Jasper Report Viewer");
            JRViewer viewer=new JRViewer(jasperPrint);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.getContentPane().add(viewer);
            frame.setSize(new Dimension(1200,800));
            frame.setVisible(true);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }
}
