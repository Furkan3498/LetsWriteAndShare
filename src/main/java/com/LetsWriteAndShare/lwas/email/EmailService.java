package com.LetsWriteAndShare.lwas.email;



import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    @Autowired
    MessageSource messageSource ;
    @PostConstruct
    // constructor edildikten sonra bu fonksiyonu cağır demek cünkü diğer türlü değerler instace create edildikten sonra spring tarafından set ediliyor.
    //construc edildikten sonra @Value ler asigne ediliyor

    public void initialize() {


        this.mailSender = new JavaMailSenderImpl();


        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);


        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");


    }


    String activationEmail = """
            
            <html>
                <body>
                     <h1> ${title} </h1>
                     <a href= "${url}">${clickHere}</a>
                </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {

        var activationURL = clientHost + "/activation/" + activationToken;

        var title = messageSource.getMessage("LetsWriteAndShare.mail.user.created.title", null, LocaleContextHolder.getLocale());
        var clichere = messageSource.getMessage("LetsWriteAndShare.mail.user.click.here", null, LocaleContextHolder.getLocale());



        var mailBody= activationEmail
                .replace("${url}",activationURL)
                .replace("${title}", title)
                .replace("${clickHere}", clichere);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
       MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

        try {

            message.setFrom(from);
            message.setTo(email);;
            message.setSubject(title);
            message.setText(mailBody,true);
        }catch (MailException | MessagingException e){
            e.printStackTrace();
        }


        this.mailSender.send(mimeMessage);


    }



}
