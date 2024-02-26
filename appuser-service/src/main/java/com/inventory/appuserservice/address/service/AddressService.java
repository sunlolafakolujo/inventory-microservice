package com.inventory.appuserservice.address.service;

import com.inventory.appuserservice.address.entity.Address;

import java.util.List;

public interface AddressService {
    Address addAddress(Address address);
    Address fetchAddressById(String id);
    List<Address> fetchAllAddresses(int pageNumber, int pageSize);
    Address editAddress(Address address, String id);
    void deleteAddress(String id);
    void deleteAllAddresses();
}
