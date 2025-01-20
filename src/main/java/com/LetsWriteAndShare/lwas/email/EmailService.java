package com.LetsWriteAndShare.lwas.email;

import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    public EmailService() {
        this.initialize();
    }

    public void initialize(){
     this.mailSender= new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(	587);
        mailSender.setUsername("abdul.howe28@ethereal.email");
        mailSender.setPassword("74pT5AdyKZDqjxeE6v");


        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable" , "true");





    }
    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("furkan@this-app.com");
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activaiton/" + activationToken);

        this.mailSender.send(message);


    }
}
