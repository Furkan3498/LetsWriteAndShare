package com.LetsWriteAndShare.lwas.service;

import com.LetsWriteAndShare.lwas.Exception.ActivationNotificationException;
import com.LetsWriteAndShare.lwas.Exception.InvalidTokenException;
import com.LetsWriteAndShare.lwas.Exception.NotFoundException;
import com.LetsWriteAndShare.lwas.Exception.NotUniqueEmailException;
import com.LetsWriteAndShare.lwas.Repository.UserRepository;
import com.LetsWriteAndShare.lwas.configuration.CurrentUser;
import com.LetsWriteAndShare.lwas.dto.UserUpdate;
import com.LetsWriteAndShare.lwas.email.EmailService;
import com.LetsWriteAndShare.lwas.entity.User;
import com.LetsWriteAndShare.lwas.file.FileService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FileService fileService;

    //@Autowired
    //PasswordEncoder passwordEncoder;

    private  final  PasswordEncoder passwordEncoder ; // if we can private finel can we change again? bcs is a immutable

    public UserService(UserRepository userRepository, EmailService emailService, FileService fileService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.fileService = fileService;
        this.passwordEncoder = passwordEncoder;
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

    public Page<User> getUsers(Pageable page, CurrentUser currentUser) {

        if (currentUser ==null){
            return  userRepository.findAll(page);
        }

            return  userRepository.findByIdNot(currentUser.getId(),page);
    }

    public User getUser(long id) {

        return  userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));


    }

    public User findByUser(String email) {

        return userRepository.findByEmail(email);
    }

    public User updateUser(long id, UserUpdate userUpdate) {

        User inDb = getUser(id);
        inDb.setUsername(userUpdate.username());

        if (userUpdate.image() != null) {

            String fileName = fileService.saveBase64StringAsFile(userUpdate.image());
            fileService.deleteProfilImage(inDb.getImage());
            inDb.setImage(fileName);}
        return userRepository.save(inDb);

        //Mapping işlemi yapılacak
    }
}
