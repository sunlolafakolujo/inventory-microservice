package com.inventory.appuserservice.passwordtoken.service;





import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.passwordtoken.entity.UserPasswordVerification;

import java.util.Optional;

public interface UserPasswordVerificationService {
    UserPasswordVerification findByToken(String token);
    Optional<AppUser> findUserByPasswordToken(String token);
    UserPasswordVerification createPasswordRestTokenForUser(String token, AppUser appUser);
    String validatePasswordToken(String token);
    void changeUserPassword(AppUser appUser, String password);
    Boolean checkIfOldPasswordExist(AppUser appUser, String oldPassword);
}
