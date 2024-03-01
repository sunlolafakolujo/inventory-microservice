package com.inventory.appuserservice.userrole.service;

import com.inventory.appuserservice.userrole.entity.Role;

import java.util.List;

public interface RoleService {
    Role saveRole(Role role);

    Role fetchByIdOrRoleName(String searchKey);

    List<Role> fetchRoles(int pageNumber, int pageSize);

    Role editRole(Role role, String searchKey);

    void deleteRole(String searchKey);

}
