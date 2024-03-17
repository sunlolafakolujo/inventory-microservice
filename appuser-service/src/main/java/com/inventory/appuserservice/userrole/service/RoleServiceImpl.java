package com.inventory.appuserservice.userrole.service;

import com.inventory.appuserservice.userrole.entity.Role;
import com.inventory.appuserservice.userrole.exception.RoleNotFoundException;
import com.inventory.appuserservice.userrole.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        role.setId(String.valueOf(1000+new Random().nextInt(LocalTime.now().getNano())));
        return roleRepository.save(role);
    }

    @Override
    public Role fetchByIdOrRoleName(String searchKey) {
        return roleRepository.findByRoleNameOrId(searchKey, searchKey)
                .orElseThrow(() -> new RoleNotFoundException("Role name or ID " + searchKey + " not found"));
    }

    @Override
    public List<Role> fetchRoles(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return roleRepository.findAll(pageRequest).toList();
    }

    @Override
    public Role editRole(Role role, String searchKey) {
        Role saveRole = roleRepository.findByRoleNameOrId(searchKey, searchKey)
                .orElseThrow(() -> new RoleNotFoundException("Role name or ID " + searchKey + " not found"));
        if (Objects.nonNull(role.getRoleName()) && !"".equalsIgnoreCase(role.getRoleName())) {
            role.setRoleName(role.getRoleName());
        }
        return roleRepository.save(saveRole);
    }

    @Override
    public void deleteRole(String searchKey) {
        if (roleRepository.existsByRoleNameOrId(searchKey, searchKey)) {
            roleRepository.deleteRoleByRoleNameOrId(searchKey, searchKey);
        }
    }
}
