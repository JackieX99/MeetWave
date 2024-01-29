package com.radnoti.meetwave.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Service
public class EmailService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> sendEmail(String target) {
        System.out.println("ide átjött: " + target);
        Map<String, Object> result = new HashMap<>();
        try {
            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "false");
            prop.put("mail.smtp.host", "smtp.rackhost.hu");
            prop.put("mail.smtp.port", "25");
            prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("noreply@meetwave.hu", "Meetwave!2024");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply@meetwave.hu"));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(target));
            message.setSubject("Mail Subject");

            String msg = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            result.put("status", "success");
        } catch (MessagingException e) {
            e.printStackTrace();
            result.put("status", "failed");
            result.put("error", e.getMessage());
        }
        return result;
    }
}
