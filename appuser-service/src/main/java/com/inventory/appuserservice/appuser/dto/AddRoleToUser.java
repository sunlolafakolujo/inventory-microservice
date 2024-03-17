package com.inventory.appuserservice.appuser.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddRoleToUser {
    private String usernameOrEmailMobile;
    private String roleName;
}
