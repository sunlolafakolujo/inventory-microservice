package com.inventory.appuserservice.appuser.dto;

import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.staticdata.UserType;
import com.inventory.appuserservice.userrole.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserUpdate {
    private Long id;
    private String mobile;
    private Address address;
}
