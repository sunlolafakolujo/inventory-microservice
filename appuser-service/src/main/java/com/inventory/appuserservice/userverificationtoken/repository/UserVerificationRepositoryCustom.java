package com.inventory.appuserservice.userverificationtoken.repository;

import com.inventory.appuserservice.userverificationtoken.entity.UserVerification;
import org.springframework.data.jpa.repository.Query;

public interface UserVerificationRepositoryCustom {
    @Query("From UserVerification u Where u.token=?1")
    UserVerification findByToken(String token);
}
