package com.inventory.appuserservice.passwordtoken.service;


import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.repository.AppUserRepository;
import com.inventory.appuserservice.passwordtoken.exception.UserPasswordVerificationNotFoundException;
import com.inventory.appuserservice.passwordtoken.entity.UserPasswordVerification;
import com.inventory.appuserservice.passwordtoken.repository.UserPasswordVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;

@Service
@Transactional
public class UserPasswordVerificationServiceImpl implements UserPasswordVerificationService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserPasswordVerificationRepository userPasswordVerificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserPasswordVerification findByToken(String token) {
        UserPasswordVerification userPasswordVerification=userPasswordVerificationRepository.findByToken(token);
        if (userPasswordVerification==null){
            throw new UserPasswordVerificationNotFoundException("Password token Not Found");
        }
        return userPasswordVerification;
    }

    @Override
    public Optional<AppUser> findUserByPasswordToken(String token) {
        return Optional.ofNullable(userPasswordVerificationRepository.findByToken(token).getAppUser());
    }

    @Override
    public UserPasswordVerification createPasswordRestTokenForUser(String token, AppUser user) {
        UserPasswordVerification passwordToken=new UserPasswordVerification(token, user);
        return userPasswordVerificationRepository.save(passwordToken);
    }

    @Override
    public String validatePasswordToken(String token) {
        UserPasswordVerification passwordToken=userPasswordVerificationRepository.findByToken(token);
        if (passwordToken==null){
            throw new UserPasswordVerificationNotFoundException("Invalid token");
        }
        Calendar calendar=Calendar.getInstance();
        if ((passwordToken.getExpectedExpirationTime().getTime()-calendar.getTime().getTime())<=0){
            userPasswordVerificationRepository.delete(passwordToken);
            throw new UserPasswordVerificationNotFoundException("Password token expired");
        }else return "Valid token";
    }

    @Override
    public void changeUserPassword(AppUser user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword)); //to work on this-done
        appUserRepository.save(user);
    }

    @Override
    public Boolean checkIfOldPasswordExist(AppUser user, String oldPassword) {
        return passwordEncoder.matches(user.getPassword(), oldPassword);

    }
}
