package com.example.siouxmanagementsystem.business.impl;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class SendEmailUseCaseImpl {
    public void createEmail(String toEmail, String body) {
        final String fromEmail = "peterpeters.norep@outlook.com"; //requires valid id
        final String password = "PeterPeters1"; // correct password for id

        Properties props = new Properties();
        props.put("mail.smtp.user", fromEmail);
        props.put("mail.smtp.host", "smtp-mail.outlook.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //SMTP Port
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.socketFactory.port", "587"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class

        try
        {
            Authenticator auth = new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            Session session = Session.getInstance(props, auth);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(body);
            msg.setSubject("A guest has been spotted"); //the subject of the mail
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress("fabienneslb@hotmail.com")); //TODO: change receiver to actual employee/secretary
            //msg.addRecipient(Message.RecipientType.CC, new InternetAddress("fabienneleidekker@gmail.com")); //CC email address, could be employee/secretary

            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
