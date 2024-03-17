package com.inventory.appuserservice.userverificationtoken.service;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.userverificationtoken.entity.UserVerification;

import java.util.Optional;

public interface UserVerificationService {
    Optional<AppUser> findAppUserByToken(String token);
    UserVerification generateNewVerificationToken(String token);
    void saveVerificationTokenToUser(AppUser appUser, String token);
    String validateVerificationToken(String token);
}
