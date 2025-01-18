package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.entity.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserRepository userRepository;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void save(User user) {


        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            //  user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

        } catch (DataIntegrityViolationException ex) {
            throw new NotUniqueEmailException();
        }

    }
}
