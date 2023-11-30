package lk.ijse.project.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendEmail {

    private Session newSession = null;
    private MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties(), null));

    public void sendMail(String[] theEmailData) throws MessagingException {

        setUpServerProperties();
        draftEmail(theEmailData);
        sendEmail();
    }

    public void setUpServerProperties() {

        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587"); // Use TLS port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        newSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("southernagroofficial@gmail.com", "aooj xnlm uilu wcva");
            }
        });
    }

    public MimeMessage draftEmail(String[] email) throws MessagingException {

        mimeMessage.setFrom(new InternetAddress("southernagroofficial@gmail.com"));
        String recipients = email[0];
        String subject = email[1];
        String body = email[2];

        mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(recipients)));
        mimeMessage.setSubject(subject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");

        MimeMultipart multipart = new MimeMultipart(); //mime msg's body
        multipart.addBodyPart(bodyPart);

        mimeMessage.setContent(multipart);

        return mimeMessage;
    }

    public void sendEmail() throws MessagingException {

        String host = "smtp.gmail.com";
        String userName = "southernagroofficial@gmail.com";
        String password = "aooj xnlm uilu wcva"; // Replace with your actual Gmail password or App Password

        Transport transport = newSession.getTransport("smtp");
        transport.connect(host, userName, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Sent Successfully !");
    }
}

