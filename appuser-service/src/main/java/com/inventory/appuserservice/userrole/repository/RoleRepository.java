package com.inventory.appuserservice.userrole.repository;

import com.inventory.appuserservice.userrole.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String>, RoleRepositoryCustom {
}
