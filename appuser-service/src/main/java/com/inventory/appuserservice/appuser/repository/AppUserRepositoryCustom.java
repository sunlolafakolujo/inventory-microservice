package com.inventory.appuserservice.appuser.repository;

import com.inventory.appuserservice.appuser.entity.AppUser;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppUserRepositoryCustom {
    @Query("FROM AppUser a WHERE a.username=?1 OR a.email=?2 OR a.mobile=?3")
    Optional<AppUser> findByUsernameOrEmailOrMobile(String username, String email, String mobile);
}
