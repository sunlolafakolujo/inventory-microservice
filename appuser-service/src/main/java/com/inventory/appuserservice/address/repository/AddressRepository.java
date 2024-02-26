package com.inventory.appuserservice.address.repository;

import com.inventory.appuserservice.address.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
