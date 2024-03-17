package com.inventory.appuserservice.appuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventory.appuserservice.AbstractContainerBaseTest;
import com.inventory.appuserservice.address.entity.Address;
import com.inventory.appuserservice.appuser.entity.AppUser;
import com.inventory.appuserservice.appuser.service.AppUserService;
import com.inventory.appuserservice.staticdata.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
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
class AppUserControllerTest extends AbstractContainerBaseTest {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    AppUser appUser;
    Address address;

    @Test
    void addUser() {
        appUser = new AppUser();
        address = new Address();
    }

    @Test
    @Order(1)
    void testThatWhenYouCallSaveMethod_thenUserIsSaved() throws Exception {
        address.setFullAddress("2 Fagunwa Street Alagomeji");
        address.setCity("Yaba");
        address.setLandmark("Off Osanyin Street");
        address.setState("Lagos");
        address.setCountry("Nigeria");

        appUser.setAddress(address);
        appUser.setUserType(UserType.USER);
        appUser.setUsername("JohnD");
        appUser.setMobile("09099999902");
        appUser.setEmail("JohnD@gmail.com");
        appUser.setPassword("password");
        appUser.setConfirmPassword("password");

        this.mockMvc.perform(post("/api/user/addUser")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appUser))
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.mobile", is("09099999902")))
                .andReturn();
        assertEquals("09099999902", appUser.getMobile());

    }

    @Test
    @Order(2)
    void testThatWhenYouCallGetUserByIdMethod_thenUserIsReturned() throws Exception {
        Long id = 2L;
        this.mockMvc.perform(get("/api/user/userId?id=" + id)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("")))
                .andReturn();

        assertEquals("username", appUser.getUsername());
    }

    @Test
    @Order(3)
    void testThatWhenYouCallGetUserByEmailOrUsernameOrMobileMethod_thenUserIsReturned() throws Exception {
        String searchKey = "";
        this.mockMvc.perform(get("/api/user/byEmailOrUsernameOrMobile?searchKy=" + searchKey)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.username", is("")))
                .andReturn();

        assertEquals(UserType.USER, appUser.getUserType());
    }

    @Test
    @Order(4)
    void testThatWhenYouCallGetAllUsersMethod_thenUsersAreReturned() throws Exception {
        this.mockMvc.perform(get("/api/user/allUsers?pageNumber=" + 0 + "&pageSize=" + 6)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(5)))
                .andExpect(jsonPath("$[2].username", is("")))
                .andReturn();
    }

    @Test
    @Order(5)
    void testThatWhenYouCallUpdateUserMethod_thenUserIsUpdated() throws Exception {
        Long id = 1L;
        appUser = appUserService.fetchById(id);
        appUser.setMobile("");
        appUserService.editUser(appUser, id);
        this.mockMvc.perform(put("/api/user/editUser?id=" + id)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appUser))
                ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(appUser.getId()))
                .andExpect(jsonPath("$.mobile", is("")))
                .andReturn();
        assertEquals("", appUser.getMobile());
    }

    @Test
    @Order(6)
    void testThatWhenYouCallDeleteUserMethod_thenUserIsDeleted() throws Exception {
        Long id = 1L;
        this.mockMvc.perform(delete("/api/user/deleteUser?id=" + id)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer ")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        assertNull(appUser.getId());

    }
}