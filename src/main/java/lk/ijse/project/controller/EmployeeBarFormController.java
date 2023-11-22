package lk.ijse.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.project.dto.tm.employeeTm;
import lk.ijse.project.model.employeeModel;
import lk.ijse.project.util.Navigation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import lk.ijse.project.fp.FpConnection;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class EmployeeBarFormController {

    @FXML
    private ImageView deleteImg;

    @FXML
    private ImageView reportimg;

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

    @FXML
    void reportOnMouseClick(MouseEvent event) {
     try{
    JasperDesign jasperDesign= JRXmlLoader.load("src/main/resources/Report/EmployeeDetailReport.jrxml");
    JRDesignQuery query=new JRDesignQuery();
    query.setText("SELECT emp_id, email, first_name,  role, contact_no FROM employee; ");
    jasperDesign.setQuery(query);
    JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
    JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,null,FpConnection.getInstance().getConnection());
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
