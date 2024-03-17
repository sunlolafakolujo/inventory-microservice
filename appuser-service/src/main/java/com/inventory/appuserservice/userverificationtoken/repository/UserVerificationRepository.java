package com.inventory.appuserservice.userverificationtoken.repository;

import com.inventory.appuserservice.userverificationtoken.entity.UserVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerification, Long>, UserVerificationRepositoryCustom {
}
