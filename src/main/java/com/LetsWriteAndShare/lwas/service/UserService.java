package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Exception.ActivationNotificationException;
import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.entity.User;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.UUID;


@Service
public class UserService {


    private final UserRepository userRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Transactional(rollbackOn = MailException.class )
    public void save(User user) {
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setActivationToken(UUID.randomUUID().toString());
            //  user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user); //save metodunda Transactional dolayı NotUniqueEmailException metodu dönmüyordu. DataIntegrityViolationException dönüyordu.
            //Transactional ProxyUserServiceten döndürüyor böylece save metoduna gelmeden cathten DataIntegrityViolationException dönüyordu. saveAndFlush yazarak o catch bu servise dönüyor
            sendActivationEmail(user);
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }

    }

    private void sendActivationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("furkan@this-app.com");
        message.setTo(user.getEmail());
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activaiton/" + user.getActivationToken());

        getJavaMailSender().send(message);


    }
        public JavaMailSender getJavaMailSender(){
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.ethereal.email");
            mailSender.setPort(	587);
            mailSender.setUsername("abdul.howe28@ethereal.email");
            mailSender.setPassword("74pT5AdyKZDqjxeE6v");


            Properties properties = mailSender.getJavaMailProperties();
            properties.put("mail.smtp.starttls.enable" , "true");
            return mailSender;




        }














}
