package com.inventory.appuserservice.userrole.repository;

import com.inventory.appuserservice.userrole.entity.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepositoryCustom {
    @Query("SELECT r FROM Role r WHERE r.roleName=?1 OR r.id=?2")
    Optional<Role> findByRoleNameOrId(String id, String roleName);

    @Query("SELECT r FROM Role r WHERE r.roleName=?1 OR r.id=?2")
    boolean existsByRoleNameOrId(String id, String roleName);

    @Modifying
    @Query("DELETE FROM Role r WHERE r.roleName=?1 OR r.id=?2")
    void deleteRoleByRoleNameOrId(String id, String roleName);
}
