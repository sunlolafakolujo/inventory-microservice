package com.inventory.appuserservice.userverificationtoken.service;



import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.repository.AppUserRepository;
import com.inventory.appuserservice.userverificationtoken.exception.UserVerificationTokenNotFoundException;
import com.inventory.appuserservice.userverificationtoken.entity.UserVerification;
import com.inventory.appuserservice.userverificationtoken.repository.UserVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class UserVerificationServiceImpl implements UserVerificationService {
    @Autowired
    private AppUserRepository userRepository;
    @Autowired
    private UserVerificationRepository userVerificationRepository;

    @Override
    public Optional<AppUser> findAppUserByToken(String token) {
        return Optional.ofNullable(userVerificationRepository.findByToken(token).getAppUser());
    }

    @Override
    public UserVerification generateNewVerificationToken(String token) {
        UserVerification userVerification=userVerificationRepository.findByToken(token);
        if (userVerification==null){
            throw new UserVerificationTokenNotFoundException("Token Not Found");
        }
        userVerification.setToken(String.valueOf(100000+new Random().nextInt(900000)));
        return userVerificationRepository.save(userVerification);
    }

    @Override
    public void saveVerificationTokenToUser(AppUser appUser, String token) {
        UserVerification userVerification=new UserVerification(token,appUser);
        userVerificationRepository.save(userVerification);
    }

    @Override
    public String validateVerificationToken(String token) {
        UserVerification userVerification=userVerificationRepository.findByToken(token);
        if (userVerification==null){
            throw new UserVerificationTokenNotFoundException("Invalid token");
        }
        AppUser appUser=userVerification.getAppUser();
        Calendar calendar=Calendar.getInstance();
        if ((userVerification.getExpectedExpirationTime().getTime()-calendar.getTime().getTime())<=0){
            userVerificationRepository.delete(userVerification);
            throw new UserVerificationTokenNotFoundException("Token expired");
        }
        appUser.setEnabled(true);
        userRepository.save(appUser);

        return "Valid token";
    }
}
