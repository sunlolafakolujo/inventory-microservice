package com.inventory.appuserservice.events.listener;



import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.configuration.email.EmailConfiguration;
import com.inventory.appuserservice.events.event.RegistrationEvent;
import com.inventory.appuserservice.userverificationtoken.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {
    @Autowired
    private UserVerificationService userVerificationService;
    @Autowired
    private EmailConfiguration emailConfiguration;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
        javaMailSender.setHost(emailConfiguration.getHost());
        javaMailSender.setPort(emailConfiguration.getPort());
        javaMailSender.setUsername(emailConfiguration.getUsername());
        javaMailSender.setPassword(emailConfiguration.getPassword());

        String token= String.valueOf(100000 + new Random().nextInt(900000));
        AppUser appUser=event.getAppUser();
        userVerificationService.saveVerificationTokenToUser(appUser,token);
        SimpleMailMessage simpleMailMessage=registrationVerificationEmail(event, appUser,token);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage registrationVerificationEmail(RegistrationEvent event, AppUser appUser, String token){
        String to= appUser.getEmail();
        String from="fakolujos@gmail.com";
        String subject="Welcome to the Karakata family!";
        String link=event.getApplicationUrl()+"/api/userVerification/verifyRegistration?token="+token;
        String body="Dear "+appUser.getUsername()+",\n\n"+"Thanks for registering an account with Karakata!\n\n" +
                "Before you get started, you need to confirm your registration.\n\n" +
                " To confirm your registration, please click the following link "+link;

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        return simpleMailMessage;
    }
}
