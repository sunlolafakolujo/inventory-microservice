package com.inventory.appuserservice.events.listener;


import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.configuration.email.EmailConfiguration;
import com.inventory.appuserservice.events.event.PasswordResetEvent;
import com.inventory.appuserservice.passwordtoken.service.UserPasswordVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class UserPasswordVerificationEventListener implements ApplicationListener<PasswordResetEvent> {
    @Autowired
    private UserPasswordVerificationService userPasswordVerificationService;
    @Autowired
    private EmailConfiguration emailConfiguration;

    @Override
    public void onApplicationEvent(PasswordResetEvent event) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfiguration.getHost());
        javaMailSender.setPort(emailConfiguration.getPort());
        javaMailSender.setUsername(emailConfiguration.getUsername());
        javaMailSender.setPassword(emailConfiguration.getPassword());

        String token = String.valueOf(100000 + new Random().nextInt(LocalDateTime.now().getNano()));
        AppUser appUser = event.getAppUser();
        userPasswordVerificationService.createPasswordRestTokenForUser(token, appUser);
        SimpleMailMessage simpleMailMessage = passwordVerificationEmail(event, appUser, token);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage passwordVerificationEmail(PasswordResetEvent event, AppUser user, String token) {
        String to = user.getEmail();
        String from = "fakolujos@gmail.com";
        String subject = "Password Reset";
        String link = event.getApplicationUrl() + "/api/passwordVerificationToken/savePassword?token=" + token;
        String text = "Dear " + user.getUsername() + ",\n\n" + "Click on the link in your inbox to reset your password " + link;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        return simpleMailMessage;
    }
}
