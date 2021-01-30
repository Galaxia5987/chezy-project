package com.example.galacticmarket;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderSender extends AsyncTask<String, Void, String> {
    boolean isDelivery;
    String orderText;
    Context context;
    String email;

    public OrderSender(Context ctx, boolean isDelivery, String orderText, String email) {
        context = ctx;
        this.isDelivery = isDelivery;
        this.orderText = orderText;
        this.email = email;
    }

    @Override
    protected String doInBackground(String... strings) {
        final String username = "israelacc2021@gmail.com";
        final String password = "adsfadsf";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("israelacc2021@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Testing Gmail TLS");
            message.setText(isDelivery ? "ORDER: " + orderText + "\n" + "DELIVERY" : "ORDER: " + orderText + "\n" + "TAKEAWAY");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
