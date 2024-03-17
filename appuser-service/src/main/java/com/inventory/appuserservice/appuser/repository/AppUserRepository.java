package com.inventory.appuserservice.appuser.repository;

import com.inventory.appuserservice.appuser.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>, AppUserRepositoryCustom {
}
