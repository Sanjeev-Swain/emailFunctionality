package org.geekster.email;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailTemplate {
    public static void main(String[] args)
    {
        String fromAdd = "swainsanjeev19@gmail.com";
        String toAdd = "swainsanjeev123@gmail.com";
        String ccAdd = "sachitra.m.swain@gmail.com";
        String body = "Welcome to geekster email demo session";
        try
        {
            //sendMailWithoutAttachemnt(fromAdd,toAdd,ccAdd,body);

                sendMailWithAttachment(fromAdd,toAdd,ccAdd,body);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void sendMailWithoutAttachemnt(String fromAddress,String toAddress,String ccAddress,String messageBody) throws MessagingException {

        Session session = getSession();

        MimeMessage m = new MimeMessage(session);
        m.setFrom(fromAddress);
        m.addRecipients(Message.RecipientType.TO,toAddress);
        m.addRecipients(Message.RecipientType.CC,ccAddress);
        m.setSubject("Geekster email demo");

        m.setText(messageBody);

        Transport.send(m);
        System.out.println("Message sent successfully");
    }

    private static Session getSession()
    {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("swainsanjeev19@gmail.com","uedejstelautfykg");
            }
        });
        return session;
    }

    public static void sendMailWithAttachment(String fromAddress,String toAddress,String ccAddress,String messageBody) throws MessagingException, IOException {
        Session session = getSession();

        MimeMessage m = new MimeMessage(session);
        m.setFrom(fromAddress);
        m.addRecipients(Message.RecipientType.TO,toAddress);
        m.addRecipients(Message.RecipientType.CC,ccAddress);
        m.setSubject("Geekster email demo with attachment");


        MimeMultipart mimeMultipart = new MimeMultipart();
        MimeBodyPart bodyText = new MimeBodyPart();
        bodyText.setText(messageBody);

        MimeBodyPart bodyAttachment = new MimeBodyPart();

        String path = "C://Users//LENOVO//Desktop//sanjeev//SIGN_SANJEEV.jpg";
        File file = new File(path);
        bodyAttachment.attachFile(file);
        mimeMultipart.addBodyPart(bodyText);
        mimeMultipart.addBodyPart(bodyAttachment);

        m.setContent(mimeMultipart);

        Transport.send(m);
        System.out.println("Email sent successfully with attachment");
    }

}