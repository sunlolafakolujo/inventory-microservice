package com.inventory.appuserservice.userrole.controller;

import com.inventory.appuserservice.userrole.dto.RoleRequest;
import com.inventory.appuserservice.userrole.dto.RoleResponse;
import com.inventory.appuserservice.userrole.dto.RoleUpdate;
import com.inventory.appuserservice.userrole.entity.Role;
import com.inventory.appuserservice.userrole.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/roles")
public record RoleController(RoleService roleService, ModelMapper modelMapper) {

    @PostMapping("/saveRole")
    public ResponseEntity<String> createRole(@RequestBody RoleRequest roleRequest) {
        Role role = modelMapper.map(roleRequest, Role.class);
        Role post = roleService.saveRole(role);
        RoleRequest posted = modelMapper.map(post, RoleRequest.class);
        return new ResponseEntity<>("Role saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/roleIdOrName")
    public ResponseEntity<RoleResponse> getRoleByNameOrId(@RequestParam("searchKey") String searchKey) {
        Role role = roleService.fetchByIdOrRoleName(searchKey);
        RoleResponse roleResponse = convertRoleToDto(role);
        return new ResponseEntity<>(roleResponse, HttpStatus.OK);
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<RoleResponse>> getAllRoles(@RequestParam("pageNumber") int pageNumber,
                                                          @RequestParam("pageSize") int pageSize) {
        List<Role> roles = roleService.fetchRoles(pageNumber, pageSize);
        return new ResponseEntity<>(roles.stream()
                .map(this::convertRoleToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping("/editRole")
    public ResponseEntity<String> updateRole(@RequestBody RoleUpdate roleUpdate,
                                             @RequestParam("searchKey") String searchKey) {
        Role role = modelMapper.map(roleUpdate, Role.class);
        Role post = roleService.editRole(role, searchKey);
        RoleUpdate posted = modelMapper.map(post, RoleUpdate.class);
        return new ResponseEntity<>("Role updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteRole")
    public ResponseEntity<String> deleteRole(@RequestParam("searchKey") String searchKey) {
        roleService.deleteRole(searchKey);
        return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
    }


    private RoleResponse convertRoleToDto(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setRoleName(role.getRoleName());
        return response;
    }
}
