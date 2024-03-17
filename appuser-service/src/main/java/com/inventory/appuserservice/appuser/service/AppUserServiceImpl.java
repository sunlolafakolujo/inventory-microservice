package com.inventory.appuserservice.appuser.service;

import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.exception.AppUserNotFoundException;
import com.inventory.appuserservice.appuser.repository.AppUserRepository;
import com.inventory.appuserservice.userrole.entity.Role;
import com.inventory.appuserservice.userrole.exception.RoleNotFoundException;
import com.inventory.appuserservice.userrole.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser createUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (existsEmailOrUsernameOrMobile(user.getEmail())) {
            throw new AppUserNotFoundException("There is an existing account with email or username or mobile");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new AppUserNotFoundException("Passwords do not match");
        }
        return appUserRepository.save(user);
    }

    @Override
    public void addRoleToUser(String usernameOrEmailOrMobile, String roleId) {
        AppUser appUser = appUserRepository.findByUsernameOrEmailOrMobile(usernameOrEmailOrMobile,
                        usernameOrEmailOrMobile, usernameOrEmailOrMobile)
                .orElseThrow(() -> new AppUserNotFoundException("User " + usernameOrEmailOrMobile + " not found"));
        Role role = roleRepository.findByRoleNameOrId(roleId, roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role " + roleId + " not found"));
        appUser.getRoles().add(role);
    }

    @Override
    public AppUser fetchById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException("User ID " + id + " not found"));
    }

    @Override
    public AppUser fetchByUsernameOrEmailOrMobile(String searchKey) {
        return appUserRepository.findByUsernameOrEmailOrMobile(searchKey,
                        searchKey, searchKey)
                .orElseThrow(() -> new AppUserNotFoundException("User " + searchKey + " not found"));
    }

    @Override
    public List<AppUser> fetchAllUsers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return appUserRepository.findAll(pageRequest).toList();
    }

    @Override
    public AppUser editUser(AppUser user, Long id) {
        AppUser savedAppUser = appUserRepository.findById(id)
                .orElseThrow(() -> new AppUserNotFoundException("User ID " + id + " not found"));
        if (Objects.nonNull(user.getMobile()) && !"".equalsIgnoreCase(user.getMobile())) {
            savedAppUser.setMobile(user.getMobile());
        }
        if (Objects.nonNull(user.getAddress()) && !"".equals(user.getAddress())) {
            savedAppUser.setAddress(user.getAddress());
        }
        return appUserRepository.save(savedAppUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (appUserRepository.existsById(id)) {
            appUserRepository.deleteById(id);
        }
    }


    private boolean existsEmailOrUsernameOrMobile(String searchKey) {
        if (appUserRepository.findByUsernameOrEmailOrMobile(searchKey,searchKey,searchKey).get()!=null){
            return true;
        }else{
            return false;
        }

    }
}
