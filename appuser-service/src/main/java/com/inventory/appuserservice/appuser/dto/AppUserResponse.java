package com.inventory.appuserservice.appuser.dto;

import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.staticdata.UserType;
import com.inventory.appuserservice.userrole.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AppUserResponse {
    private Long id;
    private UserType userType;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String confirmPassword;
    private String fullAddress;
    private String city;
    private String landmark;
    private String state;
    private String country;
    private Set<Role> roles = new HashSet<>();
}
