package com.LetsWriteAndShare.lwas.email;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;


  @Value("${LetsWriteAndShare.email.host}")
    String host;

    @Value("${LetsWriteAndShare.email.password}")
    String password;


    @Value("${LetsWriteAndShare.email.username}")
    String username;
    @Value("${LetsWriteAndShare.email.port}")
    int port;
    @Value("${LetsWriteAndShare.email.from}")
    String from;
    @Value("${LetsWriteAndShare.client.host}")
    String clientHost;
    @PostConstruct
    // constructor edildikten sonra bu fonksiyonu cağır demek cünkü diğer türlü değerler instace create edildikten sonra spring tarafından set ediliyor.
    //construc edildikten sonra @Value ler asigne ediliyor

    public void initialize(){
     this.mailSender= new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(	port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);


        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable" , "true");





    }
    public void sendActivationEmail(String email, String activationToken) {

        var activationURL = clientHost + "/activation/" + activationToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText(activationURL);

        this.mailSender.send(message);


    }
}
