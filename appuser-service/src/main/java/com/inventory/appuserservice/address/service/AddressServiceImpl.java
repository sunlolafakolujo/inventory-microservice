package com.inventory.appuserservice.address.service;

import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.address.exception.AddressNotFoundException;
import com.inventory.appuserservice.address.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address fetchAddressById(String id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address ID " + id + " not found"));
    }

    @Override
    public List<Address> fetchAllAddresses(int pageNumber, int pageSize) {
        return addressRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    @Override
    public Address editAddress(Address address, String id) {
        Address savedAddress = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address ID " + id + " not found"));
        if (Objects.nonNull(address.getFullAddress()) && !"".equalsIgnoreCase(address.getFullAddress())) {
            savedAddress.setFullAddress(address.getFullAddress());
        }
        if (Objects.nonNull(address.getCity()) && !"".equalsIgnoreCase(address.getCity())) {
            savedAddress.setCity(address.getCity());
        }
        if (Objects.nonNull(address.getLandmark()) && !"".equalsIgnoreCase(address.getLandmark())) {
            savedAddress.setState(address.getLandmark());
        }
        if (Objects.nonNull(address.getState()) && !"".equalsIgnoreCase(address.getState())) {
            savedAddress.setState(address.getState());
        }
        if (Objects.nonNull(address.getCountry()) && !"".equalsIgnoreCase(address.getCountry())) {
            savedAddress.setCountry(address.getCountry());
        }
        return addressRepository.save(savedAddress);
    }

    @Override
    public void deleteAddress(String id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
        }
    }

    @Override
    public void deleteAllAddresses() {
        addressRepository.deleteAll();
    }
}
