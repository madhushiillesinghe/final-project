package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.model.EmployeeModel;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class ForgetPasswordController {

    @FXML
    private Button btnreset;

    @FXML
    private TextField txtusername;

    static String username;
    static  int otp;

    @FXML
    void btnOnAction(ActionEvent event) throws IOException, MessagingException, SQLException {
            EmployeeModel employeeModel=new EmployeeModel();
        boolean isValidate =employeeModel.checkUsernameForForget(txtusername.getText()).equals("owner");
           if(isValidate){
                Random random=new Random();
                otp=random.nextInt(9000);
                otp +=1000;
                SendEmail sendEmail = new SendEmail();

                String recipient = "madhushiIllesinghe225@gmail.com";
                String subject = "Verification";
                String body = String.valueOf(otp);

                String[] emailData = {recipient, subject, body};
                sendEmail.sendMail(emailData);

                Navigation.switchNavigation("resetpasswordForm.fxml",event);
            } else if(employeeModel.checkUsernameForForget(txtusername.getText()).equals("manager")){
            Random random=new Random();
            otp=random.nextInt(9000);
            otp +=1000;
            SendEmail sendEmail = new SendEmail();

            String recipient = "madhushiIllesinghe225@gmail.com";
            String subject = "Verification";
            String body = String.valueOf(otp);

            String[] emailData = {recipient, subject, body};
            sendEmail.sendMail(emailData);

            Navigation.switchNavigation("resetpasswordForm.fxml",event);
           }else{
               new Alert(Alert.AlertType.ERROR, "Wrong User Name !!").show();
           }
    }
}
