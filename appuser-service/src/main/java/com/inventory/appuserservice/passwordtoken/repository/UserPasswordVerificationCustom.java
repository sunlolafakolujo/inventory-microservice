package com.inventory.appuserservice.passwordtoken.repository;


import com.inventory.appuserservice.passwordtoken.entity.UserPasswordVerification;
import org.springframework.data.jpa.repository.Query;

public interface UserPasswordVerificationCustom {
    @Query("FROM UserPasswordVerification p WHERE p.token=?1")
    UserPasswordVerification findByToken(String token);
}
