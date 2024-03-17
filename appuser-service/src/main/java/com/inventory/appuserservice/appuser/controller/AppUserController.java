package com.inventory.appuserservice.appuser.controller;

import com.inventory.appuserservice.appuser.dto.AddRoleToUser;
import com.inventory.appuserservice.appuser.dto.AppUserRequest;
import com.inventory.appuserservice.appuser.dto.AppUserResponse;
import com.inventory.appuserservice.appuser.dto.AppUserUpdate;
import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.service.AppUserService;
import com.inventory.appuserservice.userrole.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user")
public record AppUserController(AppUserService appUserService, ModelMapper modelMapper) {
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody AppUserRequest appUserRequest) {
        AppUser appUser = modelMapper.map(appUserRequest, AppUser.class);
        AppUser post = appUserService.createUser(appUser);
        AppUserRequest posted = modelMapper.map(post, AppUserRequest.class);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<String> addRoleToUser(@RequestBody AddRoleToUser addRoleToUser){
        appUserService.addRoleToUser(addRoleToUser.getRoleName(), addRoleToUser.getRoleName());
        return new ResponseEntity<>("Role added to user successfully", HttpStatus.CREATED);
    }

    @GetMapping("/userId")
    public ResponseEntity<AppUserResponse> getUserById(@RequestParam("id") Long id) {
        AppUser appUser = appUserService.fetchById(id);
        AppUserResponse appUserResponse = convertAppUserToDto(appUser);
        return new ResponseEntity<>(appUserResponse, HttpStatus.OK);
    }

    @GetMapping("/byEmailOrUsernameOrMobile")
    public ResponseEntity<AppUserResponse> getUserByEmailOrUsernameOrMobile(@RequestParam("searchKey") String searchKey) {
        AppUser appUser = appUserService.fetchByUsernameOrEmailOrMobile(searchKey);
        AppUserResponse appUserResponse = convertAppUserToDto(appUser);
        return new ResponseEntity<>(appUserResponse, HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<AppUserResponse>> getAllUsers(@RequestParam("pageNumber") int pageNumber,
                                                             @RequestParam("pageSize") int pageSize) {
        List<AppUser> appUsers = appUserService.fetchAllUsers(pageNumber, pageSize);
        return new ResponseEntity<>(appUsers.stream().map(this::convertAppUserToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editUser")
    public ResponseEntity<String> updateUser(@RequestBody AppUserUpdate appUserUpdate,
                                             @RequestParam("id") Long id) {
        AppUser appUser = modelMapper.map(appUserUpdate, AppUser.class);
        AppUser post = appUserService.editUser(appUser, id);
        AppUserUpdate posted = modelMapper.map(post, AppUserUpdate.class);
        return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    private ResponseEntity<String> deleteUser(@RequestParam("id") Long id) {
        appUserService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }


    private AppUserResponse convertAppUserToDto(AppUser appUser) {
        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setId(appUser.getId());
        appUserResponse.setUserType(appUser.getUserType());
        appUserResponse.setUsername(appUser.getUsername());
        appUserResponse.setEmail(appUser.getEmail());
        appUserResponse.setMobile(appUser.getMobile());
        appUserResponse.setFullAddress(appUser.getAddress().getFullAddress());
        appUserResponse.setCity(appUser.getAddress().getCity());
        appUserResponse.setLandmark(appUser.getAddress().getLandmark());
        appUserResponse.setState(appUser.getAddress().getState());
        appUserResponse.setCountry(appUser.getAddress().getCountry());
        appUserResponse.setRoles(appUser.getRoles().stream()
                .map(a -> {
                    Role role = new Role();
                    role.setRoleName(a.getRoleName());
                    return role;
                }).collect(Collectors.toSet()));

        return appUserResponse;
    }
}
