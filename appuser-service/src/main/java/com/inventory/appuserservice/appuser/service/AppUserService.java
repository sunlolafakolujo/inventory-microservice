package com.inventory.appuserservice.appuser.service;

import com.inventory.appuserservice.appuser.entity.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser createUser(AppUser user);
    void addRoleToUser(String usernameOrEmailOrMobile, String roleId);
    AppUser fetchById(Long id);
    AppUser fetchByUsernameOrEmailOrMobile(String searchKey);
    List<AppUser> fetchAllUsers(int pageNumber, int pageSize);
    AppUser editUser(AppUser user, Long id);
    void deleteUser(Long id);
}
