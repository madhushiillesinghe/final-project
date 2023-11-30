package lk.ijse.project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.project.util.Navigation;
import lk.ijse.project.util.SendEmail;

import javax.mail.MessagingException;
import java.io.IOException;

public class forget_passwordController {

    @FXML
    private Button btnreset;

    @FXML
    private TextField txtusername;

    @FXML
    void btnOnAction(ActionEvent event) throws IOException, MessagingException {
        SendEmail sendEmail = new SendEmail();

        String recipient = "madhushiIllesinghe225@gmail.com";
        String subject = "Verification";
        String body = "Hellowww";

        String[] emailData = {recipient, subject, body};
        sendEmail.sendMail(emailData);

        Navigation.switchNavigation("resetpasswordForm.fxml",event);
    }

}
