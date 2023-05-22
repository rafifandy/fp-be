package com.example.kosproject.service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.InternetHeaders;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {
    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String receiver, String title, String mailMessage){
        try {

            InternetHeaders headers = new InternetHeaders();
            headers.addHeader("Content-type", "text/html; charset=UTF-8");
            MimeBodyPart body = new MimeBodyPart(headers, mailMessage.getBytes("UTF-8"));

            MimeMessage message = javaMailSender.createMimeMessage();

            message.setFrom(new InternetAddress(sender));
            message.setRecipients(MimeMessage.RecipientType.TO, receiver);
            message.setSubject(title);
            message.setText(mailMessage,"UTF-8", "html");

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }



//        SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setFrom(sender);
//        msg.setTo(receiver);
//        msg.setSubject(title);
//        msg.setText(mailMessage, "text/html; charset=utf-8");

//        javaMailSender.send(msg);
    }
}
