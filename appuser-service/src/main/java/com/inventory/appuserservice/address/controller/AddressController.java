package com.inventory.appuserservice.address.controller;

import com.inventory.appuserservice.address.dto.AddressRequest;
import com.inventory.appuserservice.address.dto.AddressResponse;
import com.inventory.appuserservice.address.dto.AddressUpdate;
import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.address.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/address")
public record AddressController(AddressService addressService, ModelMapper modelMapper) {

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@RequestBody AddressRequest addressRequest) {
        Address address = modelMapper.map(addressRequest, Address.class);
        Address post = addressService.addAddress(address);
        AddressRequest posted = modelMapper.map(post, AddressRequest.class);
        return new ResponseEntity<>("Address added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/addressesId")
    public ResponseEntity<AddressResponse> getAddressById(@RequestParam("id") String id) {
        Address address = addressService.fetchAddressById(id);
        AddressResponse addressResponse = convertAddressToDto(address);
        return new ResponseEntity<>(addressResponse, HttpStatus.OK);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressResponse>> getAllAddress(@RequestParam("pageNumber") int pageNumber,
                                                               @RequestParam("pageSize") int pageSize) {

        return new ResponseEntity<>(addressService.fetchAllAddresses(pageNumber, pageSize)
                .stream().map(this::convertAddressToDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editAddress")
    public ResponseEntity<String> updateAddress(@RequestBody AddressUpdate addressUpdate,
                                                @RequestParam("id") String id) {
        Address address = modelMapper.map(addressUpdate, Address.class);
        Address post = addressService.editAddress(address, id);
        AddressUpdate posted = modelMapper.map(post, AddressUpdate.class);
        return new ResponseEntity<>("Address updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress")
    public ResponseEntity<String> deleteAddress(@RequestParam("id") String id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllAddresses")
    public ResponseEntity<String> deleteAllAddresses() {
        addressService.deleteAllAddresses();
        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }

    private AddressResponse convertAddressToDto(Address address) {
        AddressResponse addressResponse = new AddressResponse();
        addressResponse.setId(address.getId());
        addressResponse.setFullAddress(address.getFullAddress());
        addressResponse.setCity(address.getCity());
        addressResponse.setLandmark(address.getLandmark());
        addressResponse.setState(address.getState());
        addressResponse.setCountry(address.getCountry());
        return addressResponse;
    }
}
