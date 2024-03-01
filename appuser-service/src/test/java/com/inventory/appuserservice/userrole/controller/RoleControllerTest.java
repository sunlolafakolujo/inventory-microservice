package com.inventory.appuserservice.userrole.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.appuserservice.AbstractContainerBaseTest;
import com.inventory.appuserservice.userrole.entity.Role;
import com.inventory.appuserservice.userrole.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
    }

    @Test
    void testThatWhenYouCallCreateRoleMethod_thenRoleIsCreated() throws Exception {
        role.setRoleName("ADMIN");
        this.mockMvc.perform(post("/api/roles/saveRole")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .content(objectMapper.writeValueAsBytes(role))
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.roleName", is("ROLE_ADMIN")))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallGetRoleByNameOrIdMethod_thenRoleIsReturned() throws Exception {
        String searchKey = "ADMIN";
        this.mockMvc.perform(get("/api/roles/roleIdOrName?searchKey=" + searchKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is("")))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallGetAllRolesMethod_thenRolesAreReturned() throws Exception {
        this.mockMvc.perform(get("/api/roles/allRoles?pageNumber=0&pageSize=6")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$[1].roleName", is("MANAGER_SUPPLY_CHAIN")))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallUpdateRoleMethod_thenRoleIsUpdated() throws Exception {
        String searchKey = "";
        role = roleService.fetchByIdOrRoleName(searchKey);
        role.setRoleName("");
        roleService.editRole(role, searchKey);
        this.mockMvc.perform(put("/api/roles/editRole?searchKey=" + searchKey)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .content(objectMapper.writeValueAsString(objectMapper))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(role.getId()))
                .andExpect(jsonPath("$.roleName").value(role.getRoleName()))
                .andReturn();
    }

    @Test
    void testThatWhenYouCallDeleteRoleMethod_thenRoleIsDeleted() throws Exception {
        String searchKey = "";
        this.mockMvc.perform(delete("/api/roles/deleteRole?searchKey=" + searchKey)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", nullValue()))
                .andReturn();
        assertNull(role.getRoleName(), searchKey);
    }
}