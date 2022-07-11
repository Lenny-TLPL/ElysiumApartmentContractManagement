/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Phi Long
 */
public class MailUtils {

    public static void sendMail(String to, String sub, String msg, final String user, final String pass) {
        //create an instance of Properties Class   
        Properties props = new Properties();

        /* Specifies the IP address of your default mail server
     	   for e.g if you are using gmail server as an email sever
           you will pass smtp.gmail.com as value of mail.smtp host. 
           As shown here in the code. 
           Change accordingly, if your email id is not a gmail id
         */
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
         */
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {

            /* Create an instance of MimeMessage, 
 	      it accept MIME types and headers 
             */
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");

            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
//    public static void main(String[] args) {
//        String subject = "Thank for purchase our product.";
//        String message = "<!DOCTYPE html>\n"
//                + "<html lang=\"en\">\n"
//                + "\n"
//                + "<head>\n"
//                + "</head>\n"
//                + "\n"
//                + "<body>\n"
//                + "    <h3 style=\"color: blue;\">Your order has been processing.</h3>\n"
//                + "    <div>Full Name :Le Hong Quan</div>\n"
//                + "    <div>Phone : 0866823499</div>\n"
//                + "    <div>address : Vinh Hung, Vinh Loc, Thanh Hoa</div>\n"
//                + "    <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
//                + "\n"
//                + "</body>\n"
//                + "\n"
//                + "</html>";
//        ConfirmMail.send("Email đích", subject, message, "Email Nguồn", "Mật khẩu email nguồn");
//        //vd để gửi email tới "dich@gmail.com" bằng email "nguon@gmail.com" pass "123456"
////        SendMail.send("dich@gmail.com", subject, message, "nguon@gmail.com", "123456");
//    }
//    public static void sendEmail(String toEmail, String subject, String body) {
//        try {
//
//            final String fromEmail = "longtlpse160987@fpt.edu.vn"; //requires valid gmail id
//            final String password = "1q2w3e4r5t@@"; // correct password for gmail id
//
//            Authenticator auth = new Authenticator() {
//                //override the getPasswordAuthentication method
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(fromEmail, password);
//                }
//            };
//
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//            props.put("mail.smtp.port", "587"); //TLS Port
//            props.put("mail.smtp.auth", "true"); //enable authentication
//            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//
//            Session session = Session.getInstance(props, auth);
//            MimeMessage msg = new MimeMessage(session);
//
//            //set message headers
//            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//            msg.addHeader("format", "flowed");
//            msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//            msg.setFrom(new InternetAddress("sowmiyanagarajan98@gmail.com", "NoReply-JD"));
//
//            msg.setReplyTo(InternetAddress.parse("sowmiyanagarajan98@gmail.com", false));
//
//            msg.setSubject(subject, "UTF-8");
//
//            msg.setText(body, "UTF-8");
//
//            msg.setSentDate(new Date());
//
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//            Transport.send(msg);
//
//            System.out.println("EMail Sent Successfully!!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void sendEmail() {
//        String to = "tranlephilong27@gmail.com";
//
//        // Sender's email ID needs to be mentioned
//        final String from = "longtlpse106987@fpt.edu.vn";
//        final String psw = "1q2w3e4r5t@@";
//
//        // different mail will have different host name, I have implemented using gmail
//        String host = "smtp.gmail.com";
//        String port = "587";
//
//        Properties props = System.getProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
//        // props.put("mail.smtp.connectiontimeout", timeout);
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.starttls.required", "true");
//
//        // Get the default Session object.
//        //Session session = Session.getDefaultInstance(props);
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, psw);
//            }
//        });
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            // Now set the actual message
//            message.setText("This is actual message");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }

}
