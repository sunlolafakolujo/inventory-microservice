package com.inventory.appuserservice.passwordtoken.repository;


import com.inventory.appuserservice.passwordtoken.entity.UserPasswordVerification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPasswordVerificationRepository extends JpaRepository<UserPasswordVerification, Long>, UserPasswordVerificationCustom {
}
