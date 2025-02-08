package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Exception.ActivationNotificationException;
import com.LetsWriteAndShare.lwas.Exception.InvalidTokenException;
import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.email.EmailService;
import com.LetsWriteAndShare.lwas.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final EmailService emailService;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        }catch (MailException ex){
            throw new ActivationNotificationException();
        }

    }


    public void activateUser(String token) {
        User inDb = userRepository.findByActivationToken(token);
        if (inDb ==null ){
            throw  new InvalidTokenException();
        }else{
        inDb.setActive(true);
        inDb.setActivationToken(null);
        userRepository.save(inDb);
        }
    }

    public Page<User> getUsers(int page , int size) {

        return  userRepository.findAll(PageRequest.of(page,size));
    }
}
