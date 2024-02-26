package com.inventory.appuserservice.address.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressUpdate {
    private String id;
    private String fullAddress;
    private String city;
    private String landmark;
    private String state;
    private String country;
}
