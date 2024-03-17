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
public class AppUserRequest {
    private UserType userType;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String confirmPassword;
    private Address address;
    private Set<Role> roles = new HashSet<>();
}
